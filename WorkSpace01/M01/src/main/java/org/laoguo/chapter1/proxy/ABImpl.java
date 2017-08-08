package org.laoguo.chapter1.proxy;

public class ABImpl implements InterfaceA, InterfaceB {

	public void fb() {
		System.out.println("fb");
	}

	public void fa(String str, int v) {
		System.out.println("fa");
	}
}
