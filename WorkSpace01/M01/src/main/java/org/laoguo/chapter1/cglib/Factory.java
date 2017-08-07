package org.laoguo.chapter1.cglib;

import net.sf.cglib.proxy.Enhancer;

public class Factory {

	public static Base getInstance(CglibProxy proxy) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Base.class);
		enhancer.setCallback(proxy);
		Base base = (Base) enhancer.create();
		return base;
	}

}
