package com.huangzl.test.thread;

public class Cooperate2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cooperate2 x = new Cooperate2();
		Operator opt = x.new Operator();
		
		Runner_doFirst df = x.new Runner_doFirst();
		df.setOpt(opt);
		
		Runner_doSecond ds = x.new Runner_doSecond();
		ds.setOpt(opt);
		
		
//		new Thread(ds).start();只起一个线程
//		new Thread(df).start();只起一个线程
		
		//多线程
		for(int f=0;f<10;f++){//起两个
			new Thread(df).start();
		}
		for(int s=0;s<10;s++){//起一个
			new Thread(ds).start();
		}
		System.out.println("---------------------");
		
		
	}
	/**
	 * 协作逻辑在哪里实现?线程run方法还是Operator
	 *
	 */


	class Operator{
		boolean condition_doFirst = true;
		
		synchronized public void first() throws InterruptedException{
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
				notify();////////////////如果不先notify直接wait,可能导致所有线程都wait(例如执行1后,notify一个1的线程那么不满足条件进入休眠)
				System.out.println(Thread.currentThread().getName() + " wait");
				wait();
			}
			
		}
		
		synchronized public void second() throws InterruptedException{

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
				notify();/////////////////////////////////////////////////////////////////////////////如果不先notify直接wait,可能导致所有线程都wait
				System.out.println(Thread.currentThread().getName() + " wait");
				wait();
			}
		}
		
	}


	
	
	class Runner_doFirst implements Runnable{
		private Operator opt;
		
		public void setOpt(Operator opt) {
			this.opt = opt;
		}


		public void run() {
			try {
				while(true){
					opt.first();
				}
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
				while(true){
					opt.second();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	

}






