package com.huangzl.test.common;

import sun.misc.GC;

/**
 * �ն���ռ8���ֽ�
�����ݳ�Ա�Ļ���������ݳ�Ա�������������ͺͶ������÷ֿ�ͳ�ơ�
�����������Ͱ�byte/boolean=1,char/short=2,int/float=4,long/double=8���ۼӣ�Ȼ����뵽8�ı�����
�������ð�ÿ��4�ֽڣ��ۼӣ�Ȼ����뵽8���ֽڵı�����
��������������������������
����ռ���ֽ���=������8�ֽڣ���������������ռ�ģ�����������ռ��

����
class A{
    int a;
    char b;
}
ռ 8(����)+8(int 4+char 2=6,���뵽8��= 16���ֽ�
�ٱ��磺
class B{
    Integer a;
    long b;
    byte c;
}
ռ 8(����)+16(long8+byte1=9�����뵽16��+8(��������4,���뵽8)=32���ֽ�

������������������������������
������Ǵӱ����̳еģ������ҲҪ���ϡ�



long beforeMemory=Runtime.getRuntime().totalMemory();
MyObject obj=new MyOjbect();
long afterMemory=Runtime.getRuntime().totalMemory();
        System.out.println("Memory used:"+(beforeMemory-afterMemory));
 * @author Administrator
 *
 */
public class UseMemory {
	
	String s = new String("ss");
	int i = 100;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long beforeMemory=Runtime.getRuntime().totalMemory();
//		MyObject obj=new MyOjbect();
		UseMemory ob = new UseMemory();
		
		int size = 60000000;
		
		/*String[] ar = new String[size];
		for(int i=0;i<ar.length;i++){
			ar[i] = new String("x");
		}*/
		
		
		int[] ar = new int[size];
		for(int i=0;i<ar.length;i++){
			ar[i] = new String("x").hashCode();
		}
		
		
		long afterMemory=Runtime.getRuntime().totalMemory();
		
		//100000://9245120//6554200
		//1000000://56930464//11453872
		//10000000://599813480//48079824
		//20000000://1200679200//88957384
		//60000000 x://1685177400//242684480
		System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
		
		
		System.out.println(ar);
		
        System.out.println("Memory used:"+afterMemory+"-"+beforeMemory +"="+(beforeMemory-afterMemory));
	}

}
