package com.huangzl.test.jdbc.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateTemplate {
	
	private static HibernateTemplate tp;
	
	private HibernateTemplate(){
		
	}
	
	public synchronized static HibernateTemplate getHibernateTemplate(){
		if(tp == null){
			tp = new HibernateTemplate();
		}
		return tp;
	}
	
	private SessionFactory sf = HibernateUtil.getSessionFactory();
	
	
	
	public void excuteSql(String sql) throws SQLException{
//		sf.getCurrentSession().createSQLQuery(sql).executeUpdate();//
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
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
	}
	
	
	public List findBySql(String sql) throws SQLException{
		List list = new ArrayList();
		
		Session s = null;
		Transaction t = null;

		try {
			s = sf.openSession();
			t = s.beginTransaction();
			
			list = s.createSQLQuery(sql).list();
			
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		
		return list;
	}

}
