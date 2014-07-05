package com.huangzl.test.jdbc.dao;

import java.sql.SQLException;

import com.huangzl.test.jdbc.dao.impl.DsStudentDao;
import com.huangzl.test.jdbc.dao.impl.HibernateStudentDao;
import com.huangzl.test.jdbc.dao.impl.HibernateTemplateStudentDao;
import com.huangzl.test.jdbc.dao.impl.JdbcStudentDao;
import com.huangzl.test.jdbc.dao.impl.JdbcTemplateStudentDao;
import com.huangzl.test.jdbc.ds.DataSourceUtil;
import com.huangzl.test.jdbc.ds.JdbcTemplate;
import com.huangzl.test.jdbc.hibernate.HibernateTemplate;
import com.huangzl.test.jdbc.hibernate.HibernateUtil;

public class DaoTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String sql = "select SEQ_ADMIN.NEXTVAL from dual";
		
		jdbcDaoTest(sql);
		DsDaoTest(sql);
		jdbcTemplateDaoTest(sql);
		hibernateDaoTest(sql);
		HibernateTemplateStudentDaoTest(sql);
	}
	
	public static void jdbcDaoTest(String sql) throws SQLException{//to run
		IStudentDao dao = new JdbcStudentDao();
		dao.updateStudent(sql);
	}
	
	public static void DsDaoTest(String sql) throws SQLException{//to run
		DsStudentDao dao = new DsStudentDao();
		dao.setDs(DataSourceUtil.getDataSource());
		dao.updateStudent(sql);
	}
	
	public static void jdbcTemplateDaoTest(String sql) throws SQLException{//to run
		JdbcTemplateStudentDao dao = new JdbcTemplateStudentDao();
		dao.setTemplate(JdbcTemplate.getJdbcTemplate());
		dao.updateStudent(sql);
	}
	
	public static void hibernateDaoTest(String sql) throws SQLException{//to run
		HibernateStudentDao dao = new HibernateStudentDao();
		dao.setSessionFactory(HibernateUtil.getSessionFactory());
		dao.updateStudent(sql);
	}
	
	
	public static void HibernateTemplateStudentDaoTest(String sql) throws SQLException{//to run
		HibernateTemplateStudentDao dao = new HibernateTemplateStudentDao();
		dao.setHibernateTemplate(HibernateTemplate.getHibernateTemplate());
		dao.updateStudent(sql);
	}
	
	

}
