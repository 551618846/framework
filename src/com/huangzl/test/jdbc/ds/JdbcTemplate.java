package com.huangzl.test.jdbc.ds;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * ���Ҳ���Բ��õ���,DataSource��������
 * �ṩfactory�༴��
 * @author Administrator
 *
 */
public class JdbcTemplate {
	
	private static JdbcTemplate tp;
	
	private JdbcTemplate (){
		
	}
	
	public static synchronized JdbcTemplate getJdbcTemplate(){
		if(tp == null){
			tp = new JdbcTemplate();
		}
		return tp;
	}
	
	private DataSource ds = DataSourceUtil.getDataSource();//���ﲻ��static,��Ҫ��֤DataSource�ǵ���
	
	
	public void excuteSql(String sql) throws SQLException{
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
	
	public List<Map<String, Object>> findBySql(String sql) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection con = ds.getConnection();
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int columnCount = rsMetaData.getColumnCount();
			
			while(rs.next()){//ÿһ��
				Map<String, Object> map = new HashMap<String, Object>();
				
				for(int i=0;i<columnCount;i++){//ÿһ��
					String columnName = rsMetaData.getColumnName(i).toUpperCase();
					Object obj = null;
					
					int colType = rsMetaData.getColumnType(i);
					if (colType == Types.TIMESTAMP || colType == Types.DATE){
						obj = rs.getTimestamp(i);
					} 
					else{ 
						obj = rs.getObject(i); 
					}
					map.put(columnName, obj);
				}
				
				list.add(map);
			}
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
		
		return list;
	}
	

}
