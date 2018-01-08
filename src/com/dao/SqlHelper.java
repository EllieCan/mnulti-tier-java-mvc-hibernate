package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;  

public class SqlHelper {  
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	private static CallableStatement callableStatement = null; 
	private static String url = "jdbc:mysql://localhost/users?autoReconnect=true&useSSL=false";
	private static String username = "root";
	private static String password = "8185";
	
	public static ResultSet getRs(){
		return resultSet;
	}
	public static PreparedStatement getPs() {  
		return preparedStatement;  
	}  
	public static Connection getCs(){
		return connection;
	}
	//get connection to database
	public static Connection getConnection(){  
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}  
	//proceed insert, delete and update functions (CRUD) for only one query
	public static void executeUpdate(String sql, String[] parameters){  
		try {  
			connection = getConnection();   
			preparedStatement = connection.prepareStatement(sql);  
			if(parameters != null){  
				for(int i=0; i<parameters.length; i++){  
					preparedStatement.setString(i+1, parameters[i]);  
				}  
			}  
			preparedStatement.executeUpdate();  
		} catch (Exception e) {  
			e.printStackTrace();  
			throw new RuntimeException(e.getMessage());  
		}finally{  
			SqlHelper.close(resultSet, preparedStatement, connection); 
		}  

	}  
	//proceed insert, delete and update functions (CRUD) for multiple query
	public static void executeUpdateMultiple(String sql[], String[][] parameters){  
		try {  
			//get connection
			connection = getConnection();   
			connection.setAutoCommit(false);

			for (int i = 0; i < sql.length; i++) {
				if (parameters[i] != null) {
					preparedStatement = connection.prepareStatement(sql[i]);
					for (int j = 0; j < parameters.length; j++) {
						preparedStatement.setString(j+1, parameters[i][j]);
					}
					preparedStatement.executeUpdate();  
				}

			}	

			connection.commit();

		} catch (Exception e) {  
			e.printStackTrace();  
			throw new RuntimeException(e.getMessage());  
		}finally{  
			SqlHelper.close(resultSet, preparedStatement, connection); 
		}  
	}  
	//execute a query
	public static ResultSet executeQuery(String sql, String[] parameters){  
		try {  
			//get connection to database
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);  
			
			if (parameters != null && !parameters.equals("")) {
				for (int i = 0; i < parameters.length; i++) {
					preparedStatement.setString(i+1, parameters[i]);
				}
			}			
			resultSet = preparedStatement.executeQuery();  
			
		} catch (Exception e) {  
			e.printStackTrace();  
			throw new RuntimeException(e.getMessage());  
		}finally{  
			//SqlHelper.close(resultSet, preparedStatement, connection);  
		}  
		return resultSet;  
	}   
	
	public static void callProl(String sql, String[] parameters){
		try {
			//get connection to database
			connection = getConnection();
			callableStatement = connection.prepareCall(sql);
			
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					callableStatement.setObject(i+1, parameters[i]);
				}
			}		
			callableStatement.execute();			
		} catch (Exception e) {
			e.printStackTrace();  
			throw new RuntimeException(e.getMessage());  
		} finally {
			close(resultSet, callableStatement, connection);
		}
	}
		
	//close database resources
	public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection){  
		if(resultSet!=null){  
			try {  
				resultSet.close();  
			} catch (Exception e) {  
				e.printStackTrace();  
			}  
			resultSet = null;  
		}  

		if(preparedStatement!=null){  
			try {  
				preparedStatement.close();  
			} catch (Exception e) {  
				e.printStackTrace();  
			}  
			preparedStatement = null;  
		}  

		if(connection!=null){  
			try {  
				connection.close();  
			} catch (Exception e) {  
				e.printStackTrace();  
			}  
			connection=null;  
		}  
	} 
}
