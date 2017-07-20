package org.laoguo.proxy;

public class ProxySubject implements Subject {

	private Subject delegate;
	
	public ProxySubject(Subject delegate){
		this.delegate = delegate;
	}
	
	@Override
	public void dealTaks(String name) {
		System.out.println("begin");
		this.delegate.dealTaks(name);
		System.out.println("end");

	}
}
