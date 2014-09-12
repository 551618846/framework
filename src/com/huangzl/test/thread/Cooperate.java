package com.huangzl.test.thread;

public class Cooperate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Operator opt = new Operator();
		
		Runner_doFirst df = new Runner_doFirst();
		df.setOpt(opt);
		
		Runner_doSecond ds = new Runner_doSecond();
		ds.setOpt(opt);
		
		
//		new Thread(ds).start();只起一个线程
//		new Thread(df).start();只起一个线程
		
		//多线程
		for(int f=0;f<2;f++){//起两个
			new Thread(df,"first"+f).start();
		}
		for(int s=0;s<2;s++){//起一个
			new Thread(ds,"second"+s).start();
		}
		
		
	}
	/**
	 * 协作逻辑在哪里实现?线程run方法还是Operator
	 *
	 */
}


class Runner_doFirst implements Runnable{
	private Operator opt;
	
	public void setOpt(Operator opt) {
		this.opt = opt;
	}


	public void run() {
		try {
			opt.first();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
class Runner_doSecond implements Runnable{
	private Operator opt;
	
	public void setOpt(Operator opt) {
		this.opt = opt;
	}


	public void run() {
		try {
			opt.second();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

class Operator{
	boolean condition_doFirst = true;
	
	synchronized public void first() throws InterruptedException{
		while(true){
			if(condition_doFirst){
				System.out.println(Thread.currentThread().getName() + " do first=========");
				try {
					Thread.sleep(1000 * 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				condition_doFirst = false;//
				
				notify();//
			}else{//如果wait不在else内,那么有可能执行完notify后执行wait,是否会有影响?
				//注释掉这个位置的2个notify可导致死锁
//				notify();////////////////如果不先notify直接wait,可能导致所有线程都wait(例如执行1后,notify一个1的线程那么不满足条件进入休眠)
				System.out.println(Thread.currentThread().getName() + " wait");
				wait();
			}
			
		}
		
	}
	
	synchronized public void second() throws InterruptedException{
//		System.out.println(Thread.currentThread().getName() + " into second()");
		while(true){
			if(!condition_doFirst){
				System.out.println(Thread.currentThread().getName() + " do second...");
				try {
					Thread.sleep(1000 * 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				condition_doFirst = true;//
				
				notify();//
			}else{//如果wait不在else内,那么有可能执行完notify后执行wait,是否会有影响?
//				notify();/////////////////////////////////////////////////////////////////////////////如果不先notify直接wait,可能导致所有线程都wait
				System.out.println(Thread.currentThread().getName() + " wait");
				wait();
			}
			
			
		}
	}
	
}

/**
 * Thread-0 do first=========
Thread-19 do second...
Thread-9 do first=========
Thread-19 do second...
Thread-0 do first=========
Thread-17 do second...
 * 
 */



