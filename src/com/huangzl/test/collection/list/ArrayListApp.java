package com.huangzl.test.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * �ײ�������ʵ��
 * ��λ�ö�ȡ��
 * ��λ�ò�����,��ΪҪ�ƶ���λ�ú��Ԫ��
 *
 */
public class ArrayListApp {
	

	/**
	 * @param args
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add(new Demo("a"));//ĩβ����
		list.add(new Demo("b"));//ĩβ����
		
		System.out.println(list);//[a__@1fc4bec, b__@dc8569]
		
		list.add(1, new Demo("c"));//��λ�ò���
		System.out.println(list);//[a__@1fc4bec, c__@1bab50a, b__@dc8569]
		
		Demo d = new Demo("d");
		System.out.println(list.contains(d));//false
		list.add(d);
		System.out.println(list.contains(d));//true
		
		System.out.println(list);
		System.out.println(list.indexOf(d));//����i=-1��������:o==null ? get(i)==null : o.equals(get(i))
		
		list.add(1,null);//����null
		System.out.println(list);
		System.out.println(list.indexOf(null));//1
		System.out.println(list.contains(null));//true
		
		list.remove(null);
		System.out.println(list);
		
		
		
		
		
	}

}

class Demo{
	private String name = null;
	public Demo(String name) {
		this.name = name;
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		String addr = "__@" + Integer.toHexString(hashCode());//�ο�ԭtoString:getClass().getName() + "@" + Integer.toHexString(hashCode());
		return this.name + addr;
	}
}
