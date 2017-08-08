package org.laoguo.chapter1.cglib2;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

public class AuthProxyFilter implements CallbackFilter {

	public int accept(Method method) {
		if (method.getName().equals("query")) {
			return 1;
		} else {
			return 0;
		}
	}
}
