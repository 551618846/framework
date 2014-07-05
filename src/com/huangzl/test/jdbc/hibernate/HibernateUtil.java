package com.huangzl.test.jdbc.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private HibernateUtil(){
		
	}
	
	private static SessionFactory sf;
	
	public synchronized static SessionFactory getSessionFactory(){
		if(sf == null){
			sf = new Configuration().configure().buildSessionFactory();
		}
		return sf;
	}

}
