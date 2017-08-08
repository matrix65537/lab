package org.laoguo.chapter1.cglib;

import net.sf.cglib.core.DebuggingClassWriter;

public class Test {

	public static void main(String[] args) {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,  "c:\\work\\matrix65537\\lab\\trunk\\WorkSpace01\\M01");  
		CglibProxy proxy = new CglibProxy();
		Base base = Factory.getInstance(proxy);
		base = base.add();
	}
}
