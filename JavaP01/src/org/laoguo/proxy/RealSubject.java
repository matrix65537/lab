package org.laoguo.proxy;

public class RealSubject implements Subject {

	@Override
	public void dealTaks(String name) {
		System.out.println("Running " + name);

	}
}
