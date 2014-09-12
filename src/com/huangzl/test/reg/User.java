package com.huangzl.test.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 
 * һ��������ʽ����ɣ��ַ��������ʣ��߼�������
 * �ַ���������һ���ַ�����.����	\\d����	\\w��ĸ���� 	\\s�հ� 	[abc]a��b��c
 * ���ʣ�����ָ������
 * 		+������
 * 		*�����
 * 		?�л���
 * 		{n}n��
 * 		{n,}����n��
 * 		{n,m}n��m��
 * �߼���������
 * 		XYָY����X���� ;
 * 		X|YָX��Y;
 * 		(X)ָ��;
 * 
		//�ܽ�:����(*��+��?��{n}��{n,}��{n,m}),�������ƥ�����Сƥ��.Ĭ�������ƥ��.���ʺ��?�����Сƥ��
		//���������������ʽƥ�����ʲô(������,һ������(Ĭ�����ƥ��))���ٿ����ظ�����(һ������Сƥ��?)
		//����(.+)+?  ƥ�䣺(�����ַ�������Ĭ�����ƥ��)����������Сƥ��.
		//(.)+?  ƥ�䣺 (�����ַ�һ��)����������Сƥ��
		//������ָ����.Ĭ�����ƥ��.�ظ����ʵ�����Сƥ��?��ռ��ƥ��+
		//����һ����ָһ������
 * @author Administrator
 *
 */
public class User {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String mb = "\\d{11}";
		String mail = "\\w+@\\w+\\.\\w+";
		
//		validate(mb,"1380013800");
//		validate(mail,"xx@xx.xxx");
		
//		find(".+,","a01,12,q4,5,678,9");//ƥ��:Ĭ��ƥ�����
//		find(".+?,","a01,12,q4,5,678,9");//��Сƥ��
		
//		find("abc.+g","00abcxxxgabcvvvgabczzzg00");//Ĭ�����ƥ��abcxxxgabcvvvgabczzzg
//		find("abc.+?g","00abcxxxgabcvvvgabczzzg00");//��?ƥ����С
		
		find("abc(.+)g","00abcxxxgabcvvvgabczzzg00");//ƥ�����abc(�����ַ�������Ĭ�����ƥ��)g��Ĭ�����ƥ��abcxxxgabcvvvgabczzzg
		find("abc(.+)+?g","00abcxxxgabcvvvgabczzzg00");//ƥ�����abc(�����ַ�������Ĭ�����ƥ��)������,����Сƥ��
		find("abc(.)+?g","00abcxxxgabcvvvgabczzzg00");//ƥ�����abc(�����ַ�)��������Сƥ��g����?ƥ����С
		
		//�ܽ�:����(*��+��?��{n}��{n,}��{n,m}),�������ƥ�����Сƥ��.Ĭ�������ƥ��.���ʺ��?�����Сƥ��
		//���������������ʽƥ�����ʲô(������,һ������(Ĭ�����ƥ��)),�ٿ����ظ�����(һ������Сƥ��?)
		//����(.+)+?  ��(�����ַ�������Ĭ�����ƥ��)����������Сƥ��.
		//(.)+?  �� (�����ַ�һ��)����������Сƥ��
		//������ָ����.Ĭ�����ƥ��.�ظ����ʵ�����Сƥ��?��ռ��ƥ��+
		//����
		

//		find(".?+,","a01,12,q4,5,678,9");//
		
//		find("<script>\\w*</script>","text..<script>xx</script>text...<script>zz</script>text...");
//		find("<script>.*</script>","text..<script>xx</script>text...<script>zz</script>text...");
		

	}
	
	static void find(String reg,String input){
//		String reg = "((\\d+),)+";
//		reg = "(\\w+,)+";//���ƥ��
//		reg = "\\d*,";//��Сƥ��
//		reg = "\\d+,";//һ��ƥ��
		Pattern mob = Pattern.compile(reg);
		Matcher m = mob.matcher(input);
		
		while(m.find()){
			int st = m.start();
			int ed = m.end();
			String g = m.group();
//			String g1 = m.group(1);// java.lang.IndexOutOfBoundsException: No group 1
			
			System.out.println(st+"~"+ed+" : "+g);
		}
		
		
	}
	
	static void validate(String reg,String input){
//		String input = "13900139000";
		Pattern mob = Pattern.compile(reg);
		Matcher m = mob.matcher(input);
		
		boolean is = m.matches();
		System.out.println(is);
	}

}
