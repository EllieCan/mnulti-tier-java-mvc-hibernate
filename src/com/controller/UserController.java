package com.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.User;
import com.service.UsersService;

@WebServlet("/DeleteController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		UsersService usersService = new UsersService();		
		String type = request.getParameter("type");
		
		if ("delete".equals(type)) {
			String userid = request.getParameter("userid");
			if (usersService.deleteUser(userid)) {
				request.getRequestDispatcher("/OK").forward(request, response);
			} else {
				request.getRequestDispatcher("/Error").forward(request, response);
			}		
		}else if ("gotoUpdateView".equals(type)) {
			String userid = request.getParameter("userid");
			User user = usersService.getUserById(userid);
			request.setAttribute("userinfo", user);
			request.getRequestDispatcher("/UpdateUserView").forward(request, response);			
		}		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
