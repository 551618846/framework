package com.huangzl.test.jdbc.dao.impl;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.huangzl.test.jdbc.dao.IStudentDao;
import com.huangzl.test.jdbc.hibernate.HibernateTemplate;

public class HibernateTemplateStudentDao implements IStudentDao {
	
	private HibernateTemplate tem;
	
	public void setHibernateTemplate(HibernateTemplate tem){
		this.tem = tem;
	}

	public void updateStudent(String sql) throws SQLException {
		tem.excuteSql(sql);
	}

}
