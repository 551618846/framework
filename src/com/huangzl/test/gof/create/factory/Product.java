package com.huangzl.test.gof.create.factory;

public interface Product {
	String getName();
}

class ProductA implements  Product{

	public String getName() {
		return "ProductA";
	}
	
}

class ProductX implements  Product{
	
	public String getName() {
		return "ProductX";
	}
	
}