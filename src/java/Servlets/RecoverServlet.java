package Servlets;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RecoverServlet")
public class RecoverServlet extends HttpServlet {
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        String role = request.getParameter("role");
	String email = request.getParameter("email");
	String collegeid = request.getParameter("collegeid");
	response.sendRedirect("otp_set_new_pass.html?role_user="+URLEncoder.encode(role, "UTF-8")+"&email_user="+URLEncoder.encode(email, "UTF-8")+"&collegeid_user="+URLEncoder.encode(collegeid, "UTF-8"));
    }
}