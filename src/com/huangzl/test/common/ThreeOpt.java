package com.huangzl.test.common;

public class ThreeOpt {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 5;
		String s = i<0?"负数":(i>10?"大于10":"其他");
		String s1 = i<0?"负数":i>10?"大于10":"其他";//三元操作从左到右
		
		System.out.println(s);
		System.out.println(s1);
	}

}
