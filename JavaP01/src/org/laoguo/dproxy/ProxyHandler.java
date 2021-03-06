package org.laoguo.dproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
	
	private Subject subject;
	
	public ProxyHandler(Subject subject){
		this.subject = subject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy.getClass());
		System.out.println("===before===");
		Object result = method.invoke(subject, args);
		System.out.println("===end===");
		return result;
	}
}
