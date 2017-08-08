package org.laoguo.chapter1.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

	private Object target;

	public MyInvocationHandler(Object target) {
		this.target = target;
	}

	public Object getProxy() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before");
		Object result = method.invoke(target, args);
		System.out.println("after");
		return result;
	}

}
