package com.huangzl.test.gof.create.abstractFactory;

public interface AbstractFactory {
	
	ProductA createA();
	
	ProductX createX();

}

class FactoryOfImpl1 implements AbstractFactory{

	public ProductA createA() {
		return new ProductAImpl1();
	}

	public ProductX createX() {
		return new ProductXImpl1();
	}
	
	//工厂一般都做成单例模式
	private static FactoryOfImpl1 me = null;
	private FactoryOfImpl1(){
	}
	public static FactoryOfImpl1 getInstance(){
		if(me == null){
			me = new FactoryOfImpl1();
		}
		return me;
	}
	
	
	
}

class FactoryOfImpl2 implements AbstractFactory{

	public ProductA createA() {
		return new ProductAImpl2();
	}

	public ProductX createX() {
		return new ProductXImpl2();
	}
	
	//工厂一般都做成单例模式
	private static FactoryOfImpl2 me = null;
	private FactoryOfImpl2(){
	}
	public static FactoryOfImpl2 getInstance(){
		if(me == null){
			me = new FactoryOfImpl2();
		}
		return me;
	}
}

