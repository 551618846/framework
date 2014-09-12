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
		
		
//		new Thread(ds).start();ֻ��һ���߳�
//		new Thread(df).start();ֻ��һ���߳�
		
		//���߳�
		for(int f=0;f<10;f++){//������
			new Thread(df).start();
		}
		for(int s=0;s<10;s++){//��һ��
			new Thread(ds).start();
		}
		System.out.println("---------------------");
		
		
	}
	/**
	 * Э���߼�������ʵ��?�߳�run��������Operator
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
			}else{//���wait����else��,��ô�п���ִ����notify��ִ��wait,�Ƿ����Ӱ��?
				notify();////////////////�������notifyֱ��wait,���ܵ��������̶߳�wait(����ִ��1��,notifyһ��1���߳���ô������������������)
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
			}else{//���wait����else��,��ô�п���ִ����notify��ִ��wait,�Ƿ����Ӱ��?
				notify();/////////////////////////////////////////////////////////////////////////////�������notifyֱ��wait,���ܵ��������̶߳�wait
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






