package com.huangzl.test.jdbc.dao.impl;

import java.sql.SQLException;

import com.huangzl.test.jdbc.dao.IStudentDao;
import com.huangzl.test.jdbc.ds.JdbcTemplate;

public class JdbcTemplateStudentDao implements IStudentDao {
	
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}



	public void updateStudent(String sql) throws SQLException{
		template.excuteSql(sql);
	}

}
