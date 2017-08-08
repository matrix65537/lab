package org.laoguo.chapter1.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

	public Object intercept(Object object, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println(object.getClass().getName());
		System.out.println(method);
		System.out.println(args);
		System.out.println(proxy);

		System.out.println("before");
		Object result = proxy.invokeSuper(object, args);
		System.out.println("after");
		return result;
	}
}
