package com.huangzl.test.io.fromWeb;

import java.io.IOException;

public class User {
	
	//���Բ��ָܷ�exe�ļ�
	public static void main(String[] args) throws Exception {
		FILECut file = new FILECut("D:\\aa\\log.log"); //�ָ��ļ�·��
		file.Cut(3, "btt","tt"); //�ָ���� ǰ׺�ļ��� ��׺�ļ���(��ʽ.tt)
		
		FILEHut.Hut("D:\\aa\\cp.log", "tt"); //���ƶ�Ŀ¼�µ����и�ʽΪtt���ļ��ϳ�cd.exe 
	}
}
