package Servlets;

import backend.Login;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
 
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try {
            String role = request.getParameter("role");
            String collegeid = request.getParameter("collegeid");
            String password = request.getParameter("password");
	
            Login obj1 = new Login();
        
            if(obj1.verify(collegeid, password, role) == 1){
                if(role.equals("Student"))
                    response.sendRedirect("master1.html?role_user="+URLEncoder.encode(role, "UTF-8")+"&collegeid_user="+URLEncoder.encode(collegeid, "UTF-8"));
            }
            else
                Error.printError(response.getWriter(),"College ID or password is incorrect. Please try again" );
        
        }
        catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }	
    }
 
}