package org.laoguo.chapter1.proxy;

import java.lang.reflect.Proxy;

public class TestProxy {

	public static void main(String[] args) {
		MyInvocationHandler h;
		InterfaceA IA;
		InterfaceB IB;

		h = new MyInvocationHandler(new ABImpl());

		InterfaceB proxy = (InterfaceB) Proxy.newProxyInstance(Thread
				.currentThread().getContextClassLoader(), ABImpl.class
				.getInterfaces(), h);
		proxy.fb();
	}
}
