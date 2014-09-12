package com.huangzl.test.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pattern p = Pattern.compile("\\d+");
		String s = "a1bn3mm56m";
		Matcher m = p.matcher(s);
		
		int i=0;
		while(m.find()){
			String t = m.group();
			System.out.println("--"+t);
		}
		

	}

}
