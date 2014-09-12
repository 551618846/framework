package com.huangzl.test.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 
 * 一个正则表达式的组成：字符代表，量词，逻辑操作符
 * 字符代表：代表一个字符，如.任意	\\d数字	\\w字母数字 	\\s空白 	[abc]a或b或c
 * 量词：用于指定次数
 * 		+正数次
 * 		*任意次
 * 		?有或无
 * 		{n}n次
 * 		{n,}至少n次
 * 		{n,m}n到m次
 * 逻辑操作符：
 * 		XY指Y跟在X后面 ;
 * 		X|Y指X或Y;
 * 		(X)指组;
 * 
		//总结:量词(*、+、?、{n}、{n,}、{n,m}),存在最大匹配和最小匹配.默认是最大匹配.量词后加?变成最小匹配
		//必须先理解正则表达式匹配的是什么(考虑组,一次量词(默认最大匹配))。再考虑重复量词(一般是最小匹配?)
		//比如(.+)+?  匹配：(任意字符正数个默认最大匹配)正数次且最小匹配.
		//(.)+?  匹配： (任意字符一个)正数次且最小匹配
		//量词是指次数.默认最大匹配.重复量词导致最小匹配?或占有匹配+
		//组是一般是指一个部分
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
		
//		find(".+,","a01,12,q4,5,678,9");//匹配:默认匹配最大
//		find(".+?,","a01,12,q4,5,678,9");//最小匹配
		
//		find("abc.+g","00abcxxxgabcvvvgabczzzg00");//默认最大匹配abcxxxgabcvvvgabczzzg
//		find("abc.+?g","00abcxxxgabcvvvgabczzzg00");//加?匹配最小
		
		find("abc(.+)g","00abcxxxgabcvvvgabczzzg00");//匹配的是abc(任意字符正数个默认最大匹配)g。默认最大匹配abcxxxgabcvvvgabczzzg
		find("abc(.+)+?g","00abcxxxgabcvvvgabczzzg00");//匹配的是abc(任意字符正数个默认最大匹配)正数个,再最小匹配
		find("abc(.)+?g","00abcxxxgabcvvvgabczzzg00");//匹配的是abc(任意字符)正数个最小匹配g。加?匹配最小
		
		//总结:量词(*、+、?、{n}、{n,}、{n,m}),存在最大匹配和最小匹配.默认是最大匹配.量词后加?变成最小匹配
		//必须先理解正则表达式匹配的是什么(考虑组,一次量词(默认最大匹配)),再考虑重复量词(一般是最小匹配?)
		//比如(.+)+?  ：(任意字符正数个默认最大匹配)正数次且最小匹配.
		//(.)+?  ： (任意字符一个)正数次且最小匹配
		//量词是指次数.默认最大匹配.重复量词导致最小匹配?或占有匹配+
		//组是
		

//		find(".?+,","a01,12,q4,5,678,9");//
		
//		find("<script>\\w*</script>","text..<script>xx</script>text...<script>zz</script>text...");
//		find("<script>.*</script>","text..<script>xx</script>text...<script>zz</script>text...");
		

	}
	
	static void find(String reg,String input){
//		String reg = "((\\d+),)+";
//		reg = "(\\w+,)+";//最大匹配
//		reg = "\\d*,";//最小匹配
//		reg = "\\d+,";//一般匹配
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
