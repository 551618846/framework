package com.huangzl.test.collection.list;

import java.util.LinkedList;
import java.util.List;


/**
 * µ×²ãÓÉNodeÊµÏÖ
 *
 */
public class LinkedListApp {

	/**
	 * @param args
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		List list = new LinkedList();
		
		list.add(new Demo("a"));
		
		System.out.println(list);
		
		

	}

}
