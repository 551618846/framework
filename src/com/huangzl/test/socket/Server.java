package com.huangzl.test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

interface ServerStrategy{
	void process(InputStream is,OutputStream os) throws IOException;
}
/**
 * ����˻����ṹ
 * 1,�����˿�
 * 2,while(true){}
 * 3,��������Socket z = sv.accept()
 * 4,���̴߳�������,�ر�����
 * 
 * �ͻ��˻����ṹ
 * 1,������
 * 2,��������
 * 3,�ر�����
 * 
 * ������
 * ÿ�δ����Ӷ�ȡ����,�����,����read()����
 * @author Administrator
 *
 */
public class Server {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		shortConnect();
		serverA();
	}
	
	static void serverA(){
		try {
			ServerSocket server = new ServerSocket(8989);
			System.out.println("serverA start...");
			serverStrategyContext(server, new ServerStrategy() {
				
				public void process(InputStream is, OutputStream os) throws IOException {
					byte[] b = new byte[102];
					int n = is.read(b);
					System.out.println("get.."+new String(b,0,n));
					
					String w = "hello too!";
					os.write(w.getBytes());
					System.out.println("set.."+w);
				}
			});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	static void serverStrategyContext(ServerSocket server,final ServerStrategy stg){
		
		while(true){
			try {
				final Socket z = server.accept();
				
				new Thread(new Runnable() {
					
					public void run() {
						InputStream is = null;
						OutputStream os = null;
						
						try {
							is = z.getInputStream();
							os = z.getOutputStream();
							
							stg.process(is, os);
						} catch (IOException e) {
							e.printStackTrace();
						}finally{
							try {
								if(z != null){
									z.close();
								}
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}
					}
				}).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	
	static void shortConnect() throws Exception{
		ServerSocket sv = new ServerSocket(8989);
		System.out.println("ServerSocket start ...");
		
		while(true){
			final Socket z = sv.accept();//����.ÿ�ν������󶼷���һ���µĶ˿ڵ�socket
			
			//һ��ӵ�һ�����Ӷ���Ҫ��һ���̴߳���
			new Thread(new Runnable() {
				
				public void run() {
					System.out.println("Socket start===="+z);
					InputStream is;
					try {
						is = z.getInputStream();
						OutputStream os = z.getOutputStream();
						
						byte[] b = new byte[102];
//						System.out.println("start read..");
						int n = is.read(b);
						System.out.println("get.."+new String(b,0,n));
						
						os.write("hello too!".getBytes());
						System.out.println("set..hello too!");
					} catch (IOException e) {
						e.printStackTrace();
					}finally{
						if(z != null){
							try {
								z.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						System.out.println("Socket end===="+z);
					}
				}
				
			}).start();
		}
	}
	
	static void longConnect() throws Exception{
		ServerSocket sv = new ServerSocket(8989);
		System.out.println("ServerSocket start ...");
		while(true){
			final Socket z = sv.accept();//����.ÿ�ν������󶼷���һ���µĶ˿ڵ�socket
			
			//һ��ӵ�һ�����Ӷ���Ҫ��һ���̴߳���
			new Thread(new Runnable() {
				
				public void run() {
					System.out.println("Socket start===="+z);
					InputStream is;
					try {
						is = z.getInputStream();
						OutputStream os = z.getOutputStream();
						
						byte[] b = new byte[102];
//						System.out.println("start read..");
						int n = is.read(b);
						System.out.println("get.."+new String(b,0,n));
						
						int count = 0;
						while(true){
							count++;
							os.write("hello too!".getBytes());
							System.out.println(count + " set..hello too!");
							
							byte[] t = new byte[102];
//							System.out.println("start read..");
							int m = is.read(b);
							System.out.println(count + " get.."+new String(t,0,m));
							Thread.sleep(5*1000);
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						if(z != null){
							try {
								z.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						System.out.println("Socket end===="+z);
					}
				}
				
			}).start();
		}
	}

}


