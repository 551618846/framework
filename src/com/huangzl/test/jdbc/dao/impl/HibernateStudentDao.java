package com.huangzl.test.jdbc.dao.impl;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.huangzl.test.jdbc.dao.IStudentDao;

public class HibernateStudentDao implements IStudentDao {
	
	private SessionFactory sf;
	
	public void setSessionFactory(SessionFactory sf){
		this.sf = sf;
	}

	public void updateStudent(String sql) throws SQLException {
		Session s = null;
		Transaction t = null;

		try {
			s = sf.openSession();
			t = s.beginTransaction();
			
//			sf.getCurrentSession().createSQLQuery(sql);
			
//			s.createSQLQuery(sql).list();
			s.createSQLQuery(sql).executeUpdate();
//			s.save(arg0);
			
			t.commit();
		} catch (Exception err) {
			err.printStackTrace();
			if(t != null){
				t.rollback();
			}
		} finally {
			s.close();
		}
	}

}
