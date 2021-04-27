package Servlets;

/** To be edited */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OTPServlet")
public class OTPServlet extends HttpServlet {
 
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        String role = request.getParameter("role");
	String email = request.getParameter("email");
	String collegeid = request.getParameter("collegeid");
	String otp_enter = request.getParameter("otp_enter"); 
	
    }
 
}