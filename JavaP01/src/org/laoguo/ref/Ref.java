package org.laoguo.ref;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;


public class Ref {

	public static void P(Object obj){
		System.out.println(obj);
	}
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class c1 = Class.forName("org.laoguo.ref.Node");
		Node node1 = (Node) c1.newInstance();
		P(node1);
		
		Constructor[] cons = c1.getConstructors();
		for(int i = 0; i < cons.length; ++i)
		{
			P(cons[i]);
		}
		Node node2 = (Node)cons[1].newInstance(3, 5);
		P(node2);
		
		Field[] fields = c1.getDeclaredFields();
		for(int i = 0; i < fields.length; i++){
			int mo = fields[i].getModifiers();
			String priv = Modifier.toString(mo);
			
			Class<?> type = fields[i].getType();
			
			P(priv + " " + type.getName() + " " + fields[i].getName());
		}
		
		P(Ref.class.getClassLoader().getClass().getName());
	}
}
