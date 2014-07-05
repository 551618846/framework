package com.huangzl.test.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.huangzl.test.jdbc.dao.IStudentDao;
import com.huangzl.test.jdbc.jdbc.JdbcUtil;

public class JdbcStudentDao implements IStudentDao {
	
	

	public void updateStudent(String sql) throws SQLException{
		Connection con = JdbcUtil.getConnection();
		
		try {
			Statement st = con.createStatement();
			boolean rt = st.execute(sql);
			System.out.println(rt);
		} catch (Exception e) {
			if(con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
