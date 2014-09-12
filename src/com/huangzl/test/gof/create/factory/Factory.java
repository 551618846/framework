package com.huangzl.test.gof.create.factory;

public class Factory {
	
	public Product getProduct(){
		return new Product() {
			
			public String getName() {
				return "default";
			}
		};
	}
	
	public void user(){
		Product t = getProduct();
		System.out.println(t.getName());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Product p = null;
		p = new FactoryA().getProduct();
		System.out.println(p.getName());
		
		p = new FactoryX().getProduct();
		System.out.println(p.getName());
		
		
//		FactoryA
		
		//����Product������һ��Ʒ��ProductZ,��ôֻ���Ӧ���½���FactoryZ
	}

}

class FactoryA extends Factory{
	public Product getProduct(){
		return new ProductA();
	}
}
class FactoryX extends Factory{
	public Product getProduct(){
		return new ProductX();
	}
}
