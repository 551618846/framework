package com.huangzl.test.common;

/**
 * �䳤����String...���������һ������.//String x,String... list�������صĻ�,���ÿ��ܲ����������:����
 * ������������ֵ(���û�����ֵ)
 * �������ͺͰ�װ���Ͳ���,��������
 * @author Administrator
 *
 */
public class Parameter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		parameterList1("x");//ok//String x
//		parameterList1("x","a");//�������:����
		
		int i = 10;
		sendParameter(i);//����ֵ//����//int:11
		System.out.println(i);
		
		Integer ig = 10;
		sendParameter(ig);//����ֵ//����//Integer:100
		System.out.println(ig);
		
		/*String s = "xx";
		sendParameter_String(s);//����ֵ
		System.out.println(s);*/
		
		/*StringBuilder sb = new StringBuilder("vvv");//����ֵ
		sendParameter_Object(sb);
		System.out.println(sb);
		*/
		
		
	}
	
	static void sendParameter(Integer i){//�������ͺͰ�װ�����,��������
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
	
	
	/*static void parameterList(String... list,String x){//�ɱ�����б�,�������һ������
		
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
