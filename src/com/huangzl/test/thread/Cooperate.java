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
		
		
//		new Thread(ds).start();ֻ��һ���߳�
//		new Thread(df).start();ֻ��һ���߳�
		
		//���߳�
		for(int f=0;f<2;f++){//������
			new Thread(df,"first"+f).start();
		}
		for(int s=0;s<2;s++){//��һ��
			new Thread(ds,"second"+s).start();
		}
		
		
	}
	/**
	 * Э���߼�������ʵ��?�߳�run��������Operator
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
			}else{//���wait����else��,��ô�п���ִ����notify��ִ��wait,�Ƿ����Ӱ��?
				//ע�͵����λ�õ�2��notify�ɵ�������
//				notify();////////////////�������notifyֱ��wait,���ܵ��������̶߳�wait(����ִ��1��,notifyһ��1���߳���ô������������������)
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
			}else{//���wait����else��,��ô�п���ִ����notify��ִ��wait,�Ƿ����Ӱ��?
//				notify();/////////////////////////////////////////////////////////////////////////////�������notifyֱ��wait,���ܵ��������̶߳�wait
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



