package org.laoguo.chapter1;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

public class Bean {

	public static void main(String[] args) throws IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(Bean.class);
		System.out.println(beanInfo.getBeanDescriptor());
		for (PropertyDescriptor pro : beanInfo.getPropertyDescriptors()) {
			System.out.println(pro);
		}

		for (MethodDescriptor mo : beanInfo.getMethodDescriptors()) {
			System.out.println(mo);
		}

	}

}
