package org.laoguo.util;

import static org.laoguo.util.PrintUtil.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflect {

	public static void reflectClass(String className) {
		Class<?> cls = null;
		try {
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 类名称
		PLN("================class Name:");
		PLN(cls.getName());

		// 实现的接口
		PLN("================implements interface:");
		Class<?>[] interfaces = cls.getInterfaces();
		for (int i = 0; i < interfaces.length; ++i) {
			PLN(interfaces[i].getName());
		}

		// 父类
		PLN("================super class:");
		Class<?> superCls = cls.getSuperclass();
		PLN(superCls.getName());

		// 构造函数
		PLN("================constructors:");
		Constructor<?>[] cons = cls.getConstructors();
		for (int i = 0; i < cons.length; ++i) {
			Constructor<?> con = cons[i];
			int mod = con.getModifiers();
			P(Modifier.toString(mod) + " ");
			P(con.getName());

			Class<?> p[] = con.getParameterTypes();
			P("(");
			for (int j = 0; j < p.length; j++) {
				P(p[j].getName() + " arg" + j);
				if (j < p.length - 1) {
					P(",");
				}
			}
			PLN(")");
		}

		//普通方法
		PLN("================methods:");
		Method methods[] = cls.getMethods();
		for(Method m : methods){
			Class<?> returnType = m.getReturnType();
			Class<?> params[] = m.getParameterTypes();
			Annotation annos[] = m.getAnnotations();
			int t = m.getModifiers();
			P(Modifier.toString(t) + " ");
			P(returnType.getName() + " ");
			P(m.getName() + " ");
			P("(");
			for(int j = 0; j < params.length; j++){
				P(params[j].getName() + " " + "arg" + j);
				if(j < params.length - 1){
					P(",");
				}
			}
			P(")");
			Class<?> exces[] = m.getExceptionTypes();
			if(exces.length > 0){
				P(" throws ");
				for(int k = 0; k < exces.length; ++k){
					P(exces[k].getName());
					if(k < exces.length - 1){
						P(", ");
					}
				}
			}
			PLN("");
		}
		
		//本类的属性
		PLN("================this class properties:");
		Field[] fields = cls.getDeclaredFields();
		for(Field field : fields){
			int t = field.getModifiers();
			String pri = Modifier.toString(t);
			PLN(pri + " " + field.getType().getName() + " " + field.getName() + ";");
		}
		
		//父类或者接口的属性
		PLN("================super class or interface properties:");
		Field[] fields2 = cls.getFields();
		for(Field field : fields2){
			int t = field.getModifiers();
			String pri = Modifier.toString(t);
			PLN(pri + " " + field.getType().getName() + " " + field.getName() + ";");
		}
	}

	public static void main(String[] args) {
		String className = args[0];
		reflectClass(className);
	}

}
