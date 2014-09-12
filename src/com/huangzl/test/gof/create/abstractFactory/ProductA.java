package com.huangzl.test.gof.create.abstractFactory;

public interface ProductA {
	String getName();
}


class ProductAImpl1 implements ProductA{

	public String getName() {
		return "ProductAImpl1";
	}
	
}

class ProductAImpl2 implements ProductA{
	
	public String getName() {
		return "ProductAImpl2";
	}
	
}