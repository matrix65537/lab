package org.laoguo.chapter1.cglib2;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class AuthProxy implements MethodInterceptor {

	private String name;

	public AuthProxy(String name) {
		this.name = name;
	}

	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("====> " + method.getName());
		if (!"ABCD".equals(name)) {
			System.out.println("do not have privilege!");
			return null;
		}
		return proxy.invokeSuper(obj, args);
	}

}
