package com.cobalt.edu.twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import com.mysql.jdbc.Driver;

public class DatabaseClient {
	private ResultSet dbResultSet= null;
	private Statement statement = null;
	
	public DatabaseClient(){
		connectToDb();
	}
	
	public void connectToDb(){
		Connection connection = null;
		Config config= new Config();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = config.getDatabaseUrl();
			String connectionUser = "root";
			String connectionPassword = "";
			connection = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			statement = connection.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public ArrayList<String> selectCol(String query, String colSelector){
		try{
		ArrayList<String> returnAL=new ArrayList<String>();
		dbResultSet = statement.executeQuery(query);
		while (dbResultSet.next()) {
			String results = dbResultSet.getString(colSelector);
			returnAL.add(results);
		}
		
		return returnAL;
		} catch (Exception e){
			return null;
		}
	}
	
	public void doQuery(String query){
		try{
		statement.executeUpdate(query);
		}catch(Exception e){}
	}
}
