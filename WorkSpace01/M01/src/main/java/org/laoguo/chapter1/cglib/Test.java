package org.laoguo.chapter1.cglib;

public class Test {

	public static void main(String[] args) {
		CglibProxy proxy = new CglibProxy();
		Base base = Factory.getInstance(proxy);
		base.add();
	}
}
