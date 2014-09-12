package com.huangzl.test.gof.strut.adapter;

public class Adapter implements Target{
	
	private Adaptee ate;
	
	
	public Adapter(Adaptee ate) {
		this.ate = ate;
	}



	public void doSth() {
		System.out.println("I call Adaptee..");
		ate.haveDosth();
	}

}
