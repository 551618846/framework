package com.huangzl.test.thread;

public class App {
	
	public static void main(String[] args) {
		Resource only = new Resource();
		
		
		MyThread r1 = new MyThread(only);
		new Thread(r1).start();
//		new Thread(r1).start();
		
		
		/*
		UnUseRes r2 = new UnUseRes(only);
		
		new Thread(r2).start();
		new Thread(r2).start();//不使用sync的不会加(对象锁)
		*/
		
		
		
		/*
		UseRs2 r3 = new UseRs2(only);
		new Thread(r3).start();//使用sync的所有方法共享一个对象锁
		new Thread(r3).start();
		*/
		
		/*
		new Thread(new Runnable() {
			public void run() {
				Resource.useResourceStatic();
			}
		}).start();
		*/

		new Thread(new Runnable() {
			public void run() {
				Resource.useResourceStatic();
			}
		}).start();
		//使用static sync的方法共享一个Class锁.但是不会和对象锁互斥
	}

}

class Resource{
	
	synchronized public static void useResourceStatic(){
		try {
			System.out.println(Thread.currentThread().getName()+"__useResourceStatic__start");
			Thread.sleep(5*1000);
			System.out.println(Thread.currentThread().getName()+"__useResourceStatic__end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	synchronized public void useResource(){
		try {
			System.out.println(Thread.currentThread().getName()+"__useResource__start");
			Thread.sleep(5*1000);
			System.out.println(Thread.currentThread().getName()+"__useResource__end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	synchronized public void useResource2(){
		try {
			System.out.println(Thread.currentThread().getName()+"__useResource2__start");
			Thread.sleep(5*1000);
			System.out.println(Thread.currentThread().getName()+"__useResource2__end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void unUseRes(){
		try {
			System.out.println(Thread.currentThread().getName()+"__unUseRes__start");
			Thread.sleep(5*1000);
			System.out.println(Thread.currentThread().getName()+"__unUseRes__end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class MyThread implements Runnable{
	
	Resource r = null;
	
	public MyThread(Resource r) {
		this.r = r;
	}
	
	public void run() {
		r.useResource();
	}
	
}


class UseRs2 implements Runnable{
	
	Resource r = null;
	
	public UseRs2(Resource r) {
		this.r = r;
	}
	
	public void run() {
		r.useResource2();
	}
	
}


class UnUseRes implements Runnable{
	Resource r = null;
	
	public UnUseRes(Resource r) {
		this.r = r;
	}
	public void run() {
		r.unUseRes();
	}
	
}

