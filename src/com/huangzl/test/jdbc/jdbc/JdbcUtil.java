package com.huangzl.test.jdbc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
	
	public static Connection getConnection() throws SQLException{
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "rsla";
		String password = "rsla";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		Connection con = DriverManager.getConnection(url, user, password);
		return con;
	}

}
