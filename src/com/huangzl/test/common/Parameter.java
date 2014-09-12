package com.huangzl.test.common;

/**
 * 变长参数String...必须在最后一个参数.//String x,String... list这种重载的话,调用可能产生编译错误:歧义
 * 参数传的是左值(引用或者数值)
 * 基本类型和包装类型参数,可以重载
 * @author Administrator
 *
 */
public class Parameter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		parameterList1("x");//ok//String x
//		parameterList1("x","a");//编译错误:歧义
		
		int i = 10;
		sendParameter(i);//传左值//重载//int:11
		System.out.println(i);
		
		Integer ig = 10;
		sendParameter(ig);//传左值//重载//Integer:100
		System.out.println(ig);
		
		/*String s = "xx";
		sendParameter_String(s);//传左值
		System.out.println(s);*/
		
		/*StringBuilder sb = new StringBuilder("vvv");//传左值
		sendParameter_Object(sb);
		System.out.println(sb);
		*/
		
		
	}
	
	static void sendParameter(Integer i){//基本类型和包装类参数,可以重载
		i = new Integer("100");
		System.out.println("Integer:"+i);
	}
	static void sendParameter(int i){
		i++;
		System.out.println("int:"+i);
	}
	static void sendParameter_String(String s){
		s = s.concat("__");
		System.out.println(s);
	}
	static void sendParameter_Object(StringBuilder sb){
		sb.append("___");
		System.out.println(sb);
	}
	
	
	/*static void parameterList(String... list,String x){//可变参数列表,必须最后一个参数
		
	}*/
	static void parameterList1(String x,String... list){
		System.out.println("String x,String... list");
	}
	static void parameterList1(String x,String z,String... list){
		System.out.println("String x,String z,String... list");
	}
	static void parameterList1(String x){
		System.out.println("String x");
	}
}
