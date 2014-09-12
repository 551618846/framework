package com.huangzl.test.gof.strut.adapter;


public class User {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Target t = new Adapter(new Adaptee());
		use(t);
	}
	
	static void use(Target t){
		t.doSth();
	}

}
