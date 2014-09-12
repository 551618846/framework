package com.huangzl.test.gof.create.abstractFactory;

public class User {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractFactory f = FactoryOfImpl1.getInstance();
		use(f);
		
		f = FactoryOfImpl2.getInstance();
		use(f);
	}
	
	static void use(AbstractFactory ft){
		ProductA pa = ft.createA();
		ProductX px = ft.createX();
		
		System.out.println("" + pa.getName() + " and " + px.getName());
	}

}
