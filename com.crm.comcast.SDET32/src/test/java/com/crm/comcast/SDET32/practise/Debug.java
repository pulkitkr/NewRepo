package com.crm.comcast.SDET32.practise;


public class Debug {
	
	public void test1(int a, int b) {
		int c=a/b;
		System.out.println(c);
		System.out.println("Hello");
	}
	public void test2() {
		System.out.println("bye");
	}
	
	public static void main(String[] args) {
		Debug d=new Debug();
		d.test1(10, 0);
		System.out.println("completed");
	}
	
}
