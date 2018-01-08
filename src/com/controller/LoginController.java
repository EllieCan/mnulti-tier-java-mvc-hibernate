package com.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.service.UsersService;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//accept id and password from client input
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		//create UserService object, check data in database
		UsersService usersCheck = new UsersService();
		User user = new User();
		user.setUserid(Integer.parseInt(userid));
		user.setPasswd(passwd);

		if (usersCheck.checkUser(user)) {
			request.getRequestDispatcher("/MainFrame").forward(request, response);
		} else {
			request.setAttribute("error", "You ID or password incorrect!!");
			request.getRequestDispatcher("/LoginPage").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
