package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

import models.User;
import models.dbConn;

public class logindao {

	public int login(User e) {
		Connection c = null;
		PreparedStatement ps = null;
		int fres = 0;
		try {
			c = dbConn.conn();
			ps = c.prepareStatement("SELECT password from user where username=(?)");
			ps.setString(1, e.getUsername());
			String pwd = e.getPassword();
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String x = rs.getString(1);
				if (x.equals(pwd)) {
					fres = 1;
				}
			} else {
				fres = 0;
			}
		} catch (SQLException ex) {
			fres = -1;
		}
		if (fres == 1) {
			return 1;
		}
		if (fres == -1) {
			return -1;
		} else {
			return 0;
		}
	}

	public void register(User e) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = dbConn.conn();
			ps = c.prepareStatement("INSERT INTO user values(?,?)");
//			System.out.println(e.getPassword());
//			System.out.println(e.getUsername());
			ps.setString(1, e.getUsername());
			ps.setString(2, e.getPassword());
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			System.out.println("finished");
		}
	}

	public String View() {
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			String resp = "";
			c = dbConn.conn();
			ps = c.prepareStatement("SELECT * from user");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String x = rs.getString(1);
				resp += x+ " " +rs.getString(2) + "<br>";
			}
			return resp;
		} catch (SQLException ex) {
			System.out.println(ex);
			System.out.println("err");
		}
		return null;
	}

	public void Update() {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = dbConn.conn();
			ps = c.prepareStatement("alter table user ");
			ResultSet rs = ps.executeQuery();

		} catch (SQLException ex) {

		}
	}

	public void Delete(User e) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = dbConn.conn();
			ps = c.prepareStatement("DELETE FROM USER where username = (?) ");
			ps.setString(1,  e.getUsername());
			ps.executeUpdate();
//			ps.executeQuery();
//			if (rs.next()) {
//				System.out.println("Success");
//			} else {
////				return -1;
//				System.out.println("Failure");
//			}
			System.out.println("success");
		} catch (SQLException ex) {
			System.out.println(ex);
//			return -1;
		}
	}
}
