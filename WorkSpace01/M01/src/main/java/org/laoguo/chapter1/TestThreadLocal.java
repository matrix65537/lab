package org.laoguo.chapter1;

public class TestThreadLocal {

	private static ThreadLocal<String> threadLocalA = new ThreadLocal<String>();

	public static void main(String[] args) {
		threadLocalA.set("ABCD");
		threadLocalA.get();
	}

}
