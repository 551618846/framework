package com.huangzl.test.gof.create.abstractFactory;

public interface ProductX {
	String getName();
}


class ProductXImpl1 implements ProductX{

	public String getName() {
		return "ProductXImpl1";
	}
	
}

class ProductXImpl2 implements ProductX{
	
	public String getName() {
		return "ProductXImpl2";
	}
	
}