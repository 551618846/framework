package com.huangzl.test.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.huangzl.test.jdbc.dao.IStudentDao;

/**
 * 
 * @author Administrator
 *
 */
public class DsStudentDao implements IStudentDao {
	
	private DataSource ds;//µ¥Àý
	
	public void setDs(DataSource ds) {
		this.ds = ds;
	}




	public void updateStudent(String sql) throws SQLException {
		Connection con = ds.getConnection();
		
		try {
			Statement st = con.createStatement();
			boolean rt = st.execute(sql);
			System.out.println(rt);
		} catch (Exception e) {
			e.printStackTrace();
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
