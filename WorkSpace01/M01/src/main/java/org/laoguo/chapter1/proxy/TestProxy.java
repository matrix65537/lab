package org.laoguo.chapter1.proxy;

public class TestProxy {

	public static void main(String[] args) {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true"); 
		
		MyInvocationHandler h = new MyInvocationHandler(new ABImpl());
		InterfaceA proxy = (InterfaceA)h.getProxy();
		proxy.fa("ABCD", 2);
	}
}
