package com.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MainFrame")
public class MainFrame extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public MainFrame() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();		
		out.println("<img src = 'Images/Canada1.png'/><hr/>");		
		out.println("<h1>Welcom Login!!!</h1>");		
		out.println("<a href = '/UserManagerMVC/LoginPage'>Re-Login</a>");		
		out.println("<h3>Select Options</h3>");
		out.println("<a href = '/UserManagerMVC/ManageUsers'>Manage User Account</a></br>");
		out.println("<a href = ''>Add User</a></br>");
		out.println("<a href = ''>Search User</a></br>");
		out.println("<a href = ''>Quit</a></br>");		
		out.println("<hr/><img src = 'Images/Canada150.png'/>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
