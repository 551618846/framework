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
 * �̳л���ʵ�ַ���,�쳣����͸��෽�����쳣����,���߲��׳�,����RuntimeException.����û����.
 * 
 * try-catch:��RuntimeException�쳣,�������׳��Ĳ���catch.����IOException.
 * RuntimeException,Exception��������catch
 * 
 * ��RuntimeException����try-catch
 * 
 * catch˳��:��������ڸ���ǰ��
 * 
 * ������throws�����������쳣.����δ��,����Ҳ������
 * 
 * 
 * 
 * 
 * 
 * catch���Ļ���ԭ����,��RuntimeException�оͱ���catch;��д���в�����catch,���������ڸ�����catch,��RuntimeExceptionδ�׳���catch;
 * throw/throws�Ļ���ԭ����,�̳л�ʵ��ʱ�������(ͬһϵ���ڻ��׳���RuntimeException)
 * ��RuntimeException��try-catch�ǳ�ֱ�Ҫ��.
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
//	void testThrow() throws ActivationException{//����͸������,���߲��׳�,����RuntimeException
//	}
	
//	void noThrow() throws Exception{//����δ��,����Ҳ������
//	}
}

interface TestEx{
	void testThrow() throws IOException;
}

class UseInterface{
	public static void main(String[] args) {
		TestEx t = new TestExImlp4();
		try {
			t.testThrow();//�ӿڷ����׳��쳣
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		TestExImlp4 t4 = new TestExImlp4();//�ӿ�ʵ���಻���쳣
		t4.testThrow();
		
		
	}
}

class TestExImlp implements TestEx{
	public void testThrow() throws ObjectStreamException,CharacterCodingException,IndexOutOfBoundsException{//���쳣
		
	}
	
	public void throwAn() throws IOException{//throws��������
		String x = "";
		System.out.println(x);
	}
}
class TestExImlp2 implements TestEx{
	public void testThrow() throws RuntimeException{//RuntimeException����
		
	}
}
class TestExImlp21 implements TestEx{
	public void testThrow() throws IndexOutOfBoundsException{//
		
	}
}
class TestExImlp3 implements TestEx{
	public void testThrow() throws RemoteException{//���쳣
		
	}
}
class TestExImlp4 implements TestEx{
	public void testThrow(){//���׳��쳣
		
	}
}
/*class TestExImlp5 implements TestEx{
	public void testThrow() throws ActivationException{//�����ݵ��쳣
	}
}*/

public class TryCatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*try {
			
		} catch (IOException e) {//��RuntimeException��,����try�����׳�����catch
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
		}/* catch (IllegalArgumentException e) {//��������ڸ���ǰ��
			// TODO: handle exception
		}*/
		
		
		
		
		
		
		try {
			
		}finally{
			
		}
		
		
		try {  
//            throw new Exception("Nothing");  
//            throw new Error("nothing");// �л�����������??  
        } catch (Exception e) {  
            System.out.println("�����쳣����");  
            e.printStackTrace(System.err);  
        }finally{  
            System.out.println("finally ��䡣��");  
        }  
        //  
        System.out.println("try �ⲿ�ĺ���Ĵ��롣����");

	}
	
	static void test(){
		
	}
	
	

}
