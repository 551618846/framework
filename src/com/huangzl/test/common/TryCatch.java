package com.huangzl.test.common;

import java.awt.image.RasterFormatException;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.WriteAbortedException;
import java.net.UnknownHostException;
import java.nio.charset.CharacterCodingException;
import java.rmi.RemoteException;
import java.rmi.activation.ActivationException;

/*
 * 继承或者实现方法,异常必须和父类方法的异常兼容,或者不抛出,或者RuntimeException.个数没限制.
 * 
 * try-catch:非RuntimeException异常,必须先抛出的才能catch.比如IOException.
 * RuntimeException,Exception可以随意catch
 * 
 * 非RuntimeException必须try-catch
 * 
 * catch顺序:子类必须在父类前面
 * 
 * 方法的throws可以随意抛异常.父类未抛,子类也不能抛
 * 
 * 
 * 
 * 
 * 
 * catch语句的基本原则是,非RuntimeException有就必须catch;不写运行不到的catch,比如子类在父类后才catch,非RuntimeException未抛出就catch;
 * throw/throws的基本原则是,继承或实现时必须兼容(同一系列内或不抛出或RuntimeException)
 * 非RuntimeException与try-catch是充分必要的.
 * 
 * 
 */

class Super{
	void testThrow() throws IOException{
		
	}
	
	void noThrow(){
		
	}
}

class SuperExtend extends Super{
//	void testThrow() throws ActivationException{//必须和父类兼容,或者不抛出,或者RuntimeException
//	}
	
//	void noThrow() throws Exception{//父类未抛,子类也不能抛
//	}
}

interface TestEx{
	void testThrow() throws IOException;
}

class UseInterface{
	public static void main(String[] args) {
		TestEx t = new TestExImlp4();
		try {
			t.testThrow();//接口方法抛出异常
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		TestExImlp4 t4 = new TestExImlp4();//接口实现类不抛异常
		t4.testThrow();
		
		
	}
}

class TestExImlp implements TestEx{
	public void testThrow() throws ObjectStreamException,CharacterCodingException,IndexOutOfBoundsException{//子异常
		
	}
	
	public void throwAn() throws IOException{//throws可以随意
		String x = "";
		System.out.println(x);
	}
}
class TestExImlp2 implements TestEx{
	public void testThrow() throws RuntimeException{//RuntimeException可以
		
	}
}
class TestExImlp21 implements TestEx{
	public void testThrow() throws IndexOutOfBoundsException{//
		
	}
}
class TestExImlp3 implements TestEx{
	public void testThrow() throws RemoteException{//子异常
		
	}
}
class TestExImlp4 implements TestEx{
	public void testThrow(){//不抛出异常
		
	}
}
/*class TestExImlp5 implements TestEx{
	public void testThrow() throws ActivationException{//不兼容的异常
	}
}*/

public class TryCatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*try {
			
		} catch (IOException e) {//非RuntimeException的,必须try中有抛出才能catch
			// TODO: handle exception
		}*/
		
		
		try {
			
		} catch (EnumConstantNotPresentException e) {
			// TODO: handle exception
		}catch (RasterFormatException e) {
			// TODO: handle exception
		} catch (IndexOutOfBoundsException e) {//IndexOutOfBoundsException extends RuntimeException
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}/* catch (IllegalArgumentException e) {//子类必须在父类前面
			// TODO: handle exception
		}*/
		
		
		
		
		
		
		try {
			
		}finally{
			
		}
		
		
		try {  
//            throw new Exception("Nothing");  
//            throw new Error("nothing");// 切换这两行试试??  
        } catch (Exception e) {  
            System.out.println("捕获到异常。。");  
            e.printStackTrace(System.err);  
        }finally{  
            System.out.println("finally 语句。。");  
        }  
        //  
        System.out.println("try 外部的后面的代码。。。");

	}
	
	static void test(){
		
	}
	
	

}
