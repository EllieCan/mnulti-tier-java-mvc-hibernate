package com.view;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public LoginPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();		
		out.println("<img src = 'Images/Canada1.png'/><hr/>");		
		out.println("<form action = '/UserManagerMVC/LoginController' method = 'post'>");
		out.println("User ID : <input type = 'text' name = 'userid'/></br>");
		out.println("Password : <input type = 'password' name = 'passwd'/></br>");
		out.println("<input type = 'submit' value = 'submit'/></br>");
		out.println("</form>");
		
		String errInfo = (String)request.getAttribute("error");
		if (errInfo != null) {
			out.println("<font color = 'red'>" + errInfo + "</font>");
		}		
		out.println("<hr/><img src = 'Images/Canada150.png'/>");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
