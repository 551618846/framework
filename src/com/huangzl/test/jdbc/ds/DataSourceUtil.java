package com.huangzl.test.jdbc.ds;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtil {
	
	private static DataSource ds;//ЕЅР§
	
	private DataSourceUtil(){
		
	}
	
	public synchronized static DataSource getDataSource(){
		if(ds == null){
			//DBCP Лђеп C3P0
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			try {
				cpds.setDriverClass( "oracle.jdbc.driver.OracleDriver" );
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			} 
			//loads the jdbc driver            
			cpds.setJdbcUrl( "jdbc:oracle:thin:@127.0.0.1:1521:orcl" );
			cpds.setUser("rsla");                                  
			cpds.setPassword("rsla");                                  
				
			// the settings below are optional -- c3p0 can work with defaults
			cpds.setMinPoolSize(5);                                     
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(20);
			ds = cpds;
		}
		return ds;
	}

}
