package model;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			
			Connection cn = DriverManager.getConnection(url, "root", "0816");
			System.out.println("JDBC Connection Success");
			return cn;
		} catch (Exception e) {
			System.out.println("JDBC Connection Exception => " + e.toString());
			return null;
		}
	}
}
