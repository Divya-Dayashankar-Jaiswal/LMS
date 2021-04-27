package Servlets;

import backend.Register;
import backend.connectToDB;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
 
    protected void doPost(HttpServletRequest request,HttpServletResponse response){
        try{
            PrintWriter out = response.getWriter();
            String role = request.getParameter("role");
            String collegeid = request.getParameter("collegeid");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String code = null;
            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            
            //If the new user is Admin
            if( "Admin".equals(role)){
                //if college code was not created, generate one
                if(stmt.executeUpdate("select exists (select 1 from college_code);") == 0){
                    code = generateCode();
                    stmt.executeUpdate("insert into college_code values("+code+");");
                    
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Code for registering new users</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h2><center> This is the auto-generated code that new users must use to register to AddiaN:</center></h2>");
                    out.println("<h3><br>"+code+"</h3>");
                    out.println("<h4><i><br>Do not lose this code as it cannot be changed</i></h4>");
                    out.println("<br><button type=\"button\"> OK</button></center>");
                    
                }
                //if college code exists, display the code to new Admin
                else {
                    ResultSet rs = stmt.executeQuery("select code from college_code");
                    code = rs.getString("code");
                    // instead of using html page, send mail instead
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Code for registering new users</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h2><center> This is the auto-generated College Code that new users must use to register to AddiaN.</center></h2>");
                    out.println("<h3><br><center> "+code+" </center></h3>");
                    out.println("<h4><i><center> Do not lose this code as it cannot be changed</center></i></h4>");
                    out.println("<br><form><input type=\"submit\" value=\"OK\"></form></center>"); 
                }   
            }
            else{
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Verification for new users</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<center><form>Enter College Code:<input type=\"text\" name=\"code\" required>");
                out.println("<br><input type=\"submit\" value=\"OK\"></form></center>");
                code = request.getParameter("code");
                ResultSet rs = stmt.executeQuery("select code from college_code");
                String new_code = rs.getString("code");
                if(!code.equals(new_code)){
                    response.sendError(0,"The code is either incorrect or does not exist. Please try again.");
                }
            }
            Register obj1 = new Register();
            obj1.NewUser(collegeid, fullname, email, role, password);
            if(role.equals("Student"))
                response.sendRedirect("master1.html?email_user="+URLEncoder.encode(email, "UTF-8")+"&collegeid_user="+URLEncoder.encode(collegeid, "UTF-8"));
        } catch (Exception ex) {
            Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    String generateCode(){
        StringBuilder code = new StringBuilder(10);
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvwxyz";
        for (int i=0; i<10 ;i++){
            int index = (int)(letters.length()*Math.random());
            code.append(letters.charAt(index));
        }
        return code.toString();
    }
}