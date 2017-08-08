package org.laoguo.chapter1.proxy2;

import static org.laoguo.util.PrintUtil.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

interface IA {
	public void f1();
}

class A implements IA {

	public void f1() {
		PLN("A f1()");

	}
}

class B extends A {
	public void f1() {
		PLN("B f1()");
	}
}

public class ProxyInvoke {

	public static void main(String[] args) {
		try {
			Method method = Class.forName("org.laoguo.chapter1.proxy2.A").getMethod("f1", new Class[0]);
			PLN(method);
			try {
				method.invoke(new B(), (Object[])null);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
