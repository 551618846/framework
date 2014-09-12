package com.huangzl.test.common;

import sun.misc.GC;

/**
 * 空对象占8个字节
有数据成员的话，你把数据成员按基本数据类型和对象引用分开统计。
基本数据类型按byte/boolean=1,char/short=2,int/float=4,long/double=8，累加，然后对齐到8的倍数。
对象引用按每个4字节，累加，然后对齐到8个字节的倍数。
＝＝＝＝＝＝＝＝＝＝＝＝＝
对象占用字节数=基本的8字节＋基本数据类型所占的＋对象引用所占的

比如
class A{
    int a;
    char b;
}
占 8(基本)+8(int 4+char 2=6,对齐到8）= 16个字节
再比如：
class B{
    Integer a;
    long b;
    byte c;
}
占 8(基本)+16(long8+byte1=9，对齐到16）+8(对象引用4,对齐到8)=32个字节

＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
如果你是从别的类继承的，父类的也要算上。



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
