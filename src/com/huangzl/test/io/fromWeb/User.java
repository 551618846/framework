package com.huangzl.test.io.fromWeb;

import java.io.IOException;

public class User {
	
	//测试不能分割exe文件
	public static void main(String[] args) throws Exception {
		FILECut file = new FILECut("D:\\aa\\log.log"); //分割文件路径
		file.Cut(3, "btt","tt"); //分割份数 前缀文件名 后缀文件名(格式.tt)
		
		FILEHut.Hut("D:\\aa\\cp.log", "tt"); //将制定目录下得所有格式为tt的文件合成cd.exe 
	}
}
