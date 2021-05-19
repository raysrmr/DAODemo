package com.rays.conn;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class ConnectionFactory {
	static Connection con;

	public static Connection connMet() {

		try {
			Driver d = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(d); // Load a Driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aj1415", "root", "raysrmr"); // Connect to a
																										// Database
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}
	
	public static void closeConn() {
		try {
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
