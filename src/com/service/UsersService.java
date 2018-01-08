package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Use;

import com.dao.SqlHelper;
import com.model.User;

public class UsersService {
	public int getPageCount(int pageSize){
		String sql = "select count(*) from users";
		ResultSet resultSet = SqlHelper.executeQuery(sql, null);
		int rowCount = 0;

		try {
			resultSet.next();//move cursor down			
			rowCount = resultSet.getInt(1);//get row count
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlHelper.close(resultSet, SqlHelper.getPs(), SqlHelper.getCs());
		}
		return rowCount%pageSize==0?rowCount/pageSize:(rowCount/pageSize+1);
	}

	public ArrayList<User> getUsersByPage(int pageNow, int pageSize){
		ArrayList< User> arrayList = new ArrayList<User>();
		String sql = "select userid, username, email, grade from users limit " + (pageNow-1)*pageSize + ", " + pageSize;
		ResultSet resultSet = SqlHelper.executeQuery(sql, null);

		try {
			while (resultSet.next()) {
				User user = new User();
				user.setUserid(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));
				user.setGrade(resultSet.getInt(4));
				arrayList.add(user); /*Key step, don't forget add it after set*/
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			SqlHelper.close(resultSet, SqlHelper.getPs(), SqlHelper.getCs());
		}		
		return arrayList;
	}
	
	//check if user is legal
	public boolean checkUser(User user){
		boolean b = false;
		String sql = "select * from users where userid=? and passwd=?";
		String parameters[] = {user.getUserid()+"", user.getPasswd()};
		ResultSet resultSet = SqlHelper.executeQuery(sql, parameters);
		//check if the record exist
		try {
			if (resultSet.next()) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlHelper.close(resultSet, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		return b;
	}

	public boolean deleteUser(String userid){
		boolean b = true;
		String sql = "delete from users where userid=?";
		String parameters[] = {userid};

		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b = false;
		}
		return b;
	}

	public User getUserById(String userid){
		User user = new User();
		String sql = "select * from users where userid=?";
		String parameters[] = {userid};
		ResultSet resultSet = SqlHelper.executeQuery(sql, parameters);

		try {
			if (resultSet.next()) {
				user.setUserid(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setPasswd(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setTel(resultSet.getString(5));
				user.setGrade(resultSet.getInt(6));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlHelper.close(resultSet, SqlHelper.getPs(), SqlHelper.getCs());
		}	
		return user;
	}
}
