package com.gms.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.gms.web.constants.DB;

public class JDBCTest {
	public static void main(String[] args) {
		String findName = "";
		Connection conn = null;
		try {
			Class.forName(DB.ORACLE_DRIVER);
			conn = DriverManager.getConnection(DB.ORACLE_URL, DB.USERNAME, DB.PASSWORD);
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Member WHERE userId='cho'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				findName = rs.getString("name");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("#####결과 : " + findName);
	}
}
