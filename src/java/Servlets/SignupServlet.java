package Servlets;

import backend.Register;
import backend.connectToDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request,HttpServletResponse response){
        try{
            PrintWriter out = response.getWriter();
            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            String code;
            
            String role = request.getParameter("role");
            String collegeid = request.getParameter("collegeid");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String conPassword = request.getParameter("confirmpassword");
            if(!password.equals(conPassword)){
                Error.printError(out, "Password and confirm password fields do not match. Please enter your password again.");
                return;
            }
            
            //If the new user is Admin
            if(role.equals("Admin")){
                //if college code was not created, generate one
                if(stmt.execute("select exists (select 1 from college_code);")){
                    code = generateCode();
                    stmt.executeUpdate("insert into college_code values(\""+code+"\");");
                }
                //if college code exists, display the code to new Admin
                else {
                    ResultSet rs = stmt.executeQuery("select code from college_code");
                    code = rs.getString("code");
                }   
                printCode(code,out);
            }
            else{
                code=request.getParameter("college_code");

                if(stmt.execute("select exists (select 1 from college_code);")){
                    Error.printError(out,"No Administrator from your college has registered on AddiaN.<br> You can only register on AddiaN once an administrator has registered and given you the college code");
                    return;
                }
                else{
                    ResultSet rs = stmt.executeQuery("select code from college_code");
                    String new_code = rs.getString("code");
                    if(!code.equals(new_code)){
                        Error.printError(out,"The code is either incorrect or does not exist. Please try again.");
                        return;
                    }
                }
            }
            Register obj1 = new Register();
            obj1.NewUser(collegeid, fullname, email, role, password);
            if(role.equals("Student"))
                response.sendRedirect("master1.html?role="+URLEncoder.encode(role, "UTF-8")+"&collegeid_user="+URLEncoder.encode(collegeid, "UTF-8"));
        
        }
        
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //dummy function that calls doPost()
    protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    //function to generate a random 10 digit code for admin to share
    String generateCode(){
        StringBuilder code = new StringBuilder(10);
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvwxyz";
        for (int i=0; i<10 ;i++){
            int index = (int)(letters.length()*Math.random());
            code.append(letters.charAt(index));
        }
        return code.toString();
    }
    
    //function to print college code for admin
    void printCode(String code,PrintWriter out) throws SQLException, ClassNotFoundException{
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Code for registering new users</title>");            
        out.println("</head>");
        out.println("<body style=\"font-family:Times new roman;\">");
        out.println("<center><h3> This is the auto-generated code that new users must use to register to AddiaN:</h3>");
        out.println("<h3><br>"+code+"</h3>");
        out.println("<h4><i><br>Do not lose this code as it cannot be changed</i></h4>");
        out.println("<br><button type=\"button\"> OK</button></center>");            
    }
    
}