package com.huangzl.test.thread;

public class Mutex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Mutex t = new Mutex();
		
		Resource rs = t.new Resource();
		Runner run = t.new Runner();
		run.setRs(rs);
		
		for(int i=0;i<3;i++){
			System.out.println("main...");
			new Thread(run).start();
		}
		
		Runner2 run2 = t.new Runner2();
		run2.setRs(rs);
		
		for(int i=0;i<3;i++){
			System.out.println("main...");
			new Thread(run2).start();
		}

	}
	
	
	
	
	class Resource{
		private String rs = "resource";
		
		synchronized public void useResource1(){
			System.out.println("\nuseResource1 start...");
			try {
				System.out.println("resource..." + rs);
				Thread.sleep(1000 * 3);
			} catch (Exception e) {
			}
			System.out.println("useResource1 end...");
		}
		
		synchronized public void useResource2(){
			System.out.println("\nuseResource2 start...");
			try {
				System.out.println("resource..." + rs);
				Thread.sleep(1000 * 3);
			} catch (Exception e) {
			}
			System.out.println("useResource2 end...");
		}
	}
	
	class Runner implements Runnable{
		
		private Resource rs;
		
		public void setRs(Resource rs) {
			this.rs = rs;
		}
		
		public void run() {
			rs.useResource1();
		}
		
	}
	
	class Runner2 implements Runnable{
		
		private Resource rs;
		
		public void setRs(Resource rs) {
			this.rs = rs;
		}
		
		public void run() {
			rs.useResource2();
		}
		
	}

}
