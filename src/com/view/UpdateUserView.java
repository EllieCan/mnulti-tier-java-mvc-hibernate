package com.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.User;

@WebServlet("/UpdateUserView")
public class UpdateUserView extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateUserView() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		User user = (User) request.getAttribute("userinfo");		
		//display
		out.println("<table border=1px bordercolor=red cellspacing=0 width=300px>");
		out.println("<tr><td>userid</td><td>"+user.getUserid()+"</td></tr>");
		out.println("<tr><td>username</td><td>"+user.getUsername()+"</td></tr>");
		out.println("<tr><td>passwd</td><td>"+user.getPasswd()+"</td></tr>");
		out.println("<tr><td>email</td><td>"+user.getEmail()+"</td></tr>");
		out.println("<tr><td>tel</td><td>"+user.getTel()+"</td></tr>");
		out.println("<tr><td>grade</td><td>"+user.getGrade()+"</td></tr>");
		out.println("</table>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
