package com.huangzl.test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

interface SocketProcess{
	void process(InputStream is,OutputStream os) throws IOException;
}

public class Client {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) {
		
//		shortClient();
		
		for(int i =0;i<10;i++){
			strategyA();
		}
	}
	
	static void strategyB(){
		try {
			Socket sk = new Socket("127.0.0.1", 8989);
			strategyContext(sk, new SocketProcess() {
				
				public void process(InputStream is, OutputStream os) throws IOException {
					String w = "hello,this is ?";
					os.write(w.getBytes());
					System.out.println("set.."+w);
					
					byte[] b = new byte[1024];
					int n = is.read(b);
					System.out.println("readed.."+new String(b,0,n));
				}
			});
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void strategyA(){
		try {
			Socket sk = new Socket("127.0.0.1", 8989);
			strategyContext(sk, new SocketProcess() {
				
				public void process(InputStream is, OutputStream os) throws IOException {
					os.write("hello".getBytes());
					System.out.println("set..hello");
					
					byte[] b = new byte[1024];
					int n = is.read(b);
					System.out.println("readed.."+new String(b,0,n));
				}
			});
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void strategyContext(final Socket c,final SocketProcess process){
		new Thread(new Runnable() {
			
			public void run() {
				OutputStream os;
				InputStream is;
				
				try {
					is = c.getInputStream();
					os = c.getOutputStream();
					
					process.process(is, os);
					
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(c != null){
						try {
							c.close();
						} catch (IOException e) {
						}
					}
				}
			}
		}).start();
		
		
	}
	
	
	static void longClient(){
		Socket c = null;
		OutputStream os;
		InputStream is;
		
		try {
			c = new Socket("127.0.0.1", 8989);//考虑存储到全局变量

			is = c.getInputStream();//多次打开?
			os = c.getOutputStream();
			
			os.write("hello".getBytes());
			System.out.println("set..hello");
			
			int count = 0;
			while(true){//保持连接(注意服务端也需要保持)
				count++;
				
				byte[] b = new byte[1024];
				int n = is.read(b);
				System.out.println("readed.."+new String(b,0,n));
				Thread.sleep(1000*10);
				
				os.write("hello".getBytes());//
				
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			if(c != null){
				try {
					c.close();
				} catch (IOException e) {
				}
			}
		}
		
	}
	
	static void shortClient(){
		Socket c = null;
		OutputStream os;
		InputStream is;
		
		try {
			c = new Socket("127.0.0.1", 8989);
			is = c.getInputStream();
			os = c.getOutputStream();
			
			is.close();
			os.close();
			
			is = c.getInputStream();
			os = c.getOutputStream();
			
			
			
			os.write("hello".getBytes());
			System.out.println("set..hello");
			
			byte[] b = new byte[1024];
			int n = is.read(b);
			System.out.println("readed.."+new String(b,0,n));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(c != null){
				try {
					c.close();
				} catch (IOException e) {
				}
			}
		}
		
		
		
	}

}
