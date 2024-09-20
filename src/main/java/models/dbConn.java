package models;

import java.sql.*;

public class dbConn {
	public static Connection conn() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/authentication", "root", "123");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.print("error occured");
		}
		return con;
	}
}