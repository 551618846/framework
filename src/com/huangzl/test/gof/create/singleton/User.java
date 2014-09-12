package com.huangzl.test.gof.create.singleton;

public class User {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Singleton obj = Singleton.getInstance();
		System.out.println(obj);
		Singleton obj2 = Singleton.getInstance();
		System.out.println(obj2);
	}

}