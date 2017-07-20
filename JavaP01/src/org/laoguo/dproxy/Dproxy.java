package org.laoguo.dproxy;

import java.lang.reflect.Proxy;

public class Dproxy {

	public static void main(String[] args) {
		ProxyUtil.saveProxyClass("C:\\dev2\\$Proxy0.class");
		RealSubject realSubject = new RealSubject();
		ProxyHandler handler = new ProxyHandler(realSubject);
		Subject subject = (Subject)Proxy.newProxyInstance(RealSubject.class.getClassLoader(), RealSubject.class.getInterfaces(), handler);
		subject.request();
	}
}
