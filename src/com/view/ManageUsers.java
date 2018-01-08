package com.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.User;
import com.service.UsersService;

@WebServlet("/ManageUsers")
public class ManageUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ManageUsers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		//use javaScript to manipulate page jump 
		out.println("<script type='text/javascript' language = 'javascript'>");
		out.println("function gotoPageNow(){var pageNow = document.getElementById('pageNow'); "
				+ " window.open('/UserManagerMVC/ManageUsers?pageNow='+pageNow.value,'_self');}"
				+ " function confirmOperator(){ return window.confirm('Are you sure you want to delete the user?');}");
		out.println("</script>");
		//add image
		out.println("<img src = 'Images/Canada1.png'/><hr/>");
		//jump to main page or quite
		out.println("<a href = '/UserManagerMVC/MainFrame'>Back to Main Page!</a><br/>"
				+ "<a href = '/UserManagerMVC/LoginPage'>Safely Quite!</a><hr/>");
		out.println("<h1>Manage Users!!</h1>");

		int pageNow = 1; //current page
		//accept client page selection
		String page = request.getParameter("pageNow");
		if(page!=null){
			pageNow = Integer.parseInt(page);
		}
		int pageSize = 3; //items in each page
		int pageCount = 1; //this is a derived number
		try {
			UsersService usersService = new UsersService();

			pageCount = usersService.getPageCount(pageSize);

			ArrayList<User> arrayList = usersService.getUsersByPage(pageNow, pageSize);			

			//display database recored in tables
			out.println("<table border=1px bordercolor=red cellspacing=0 width=500px>");
			out.println("<tr>"
					+ "<th>UserID</th>"
					+ "<th>UserName</th>"
					+ "<th>Email</th>"
					+ "<th>Grade</th>"
					+ "<th>Delete Users</th>"
					+ "<th>Modify Users</th>"
					+ "</tr>");

			//display users information in a by using a loop
			for (User user: arrayList) {
				out.println("<tr>"
						+ "<td>"+user.getUserid()+"</td>"
						+ "<td>"+user.getUsername()+"</td>"
						+ "<td>"+user.getEmail()+"</td>"
						+ "<td>"+user.getGrade()+"</td>"
						+ "<td><a onClick = 'return confirmOperator();' href='/UserManagerMVC/UserController?"
						+ "type=delete&userid="+user.getUserid()+"'>Delete Users</a></td>"
						+ "<td><a href='/UserManagerMVC/UserController?"
						+ "type=gotoUpdateView&userid="+user.getUserid()+"'>Add Users</a></td>"
						+ "</tr>");
			}          
			out.println("</table><br/>");
			//display previous page
			if(pageNow != 1){
				out.println("<a href='/UserManagerMVC/ManageUsers?pageNow="+(pageNow-1)+"'>Previous</a>");
			}         
			//display page split 
			for (int i = 1; i <= pageCount; i++) {
				out.println("<a href='/UserManagerMVC/ManageUsers?pageNow="+i+"'><"+i+"></a>");
			}
			//display next page
			if(pageNow != pageCount){
				out.println("<a href='/UserManagerMVC/ManageUsers?pageNow="+(pageNow+1)+"'>Next</a>");
			}
			//display page split information
			out.println("&nbsp;&nbsp;&nbsp;Current page <" +pageNow+ "> / Total page<" + pageCount + "><br/><br/>");
			//jump to selected page
			out.println("Jump to: <input typ='text' id='pageNow' name='pageNow'/> "
					+ "<input type='button' onClick='gotoPageNow()' value='Jump'>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("<hr/><img src = 'Images/Canada150.png'/>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
