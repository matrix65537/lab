package org.laoguo.c;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.laoguo.util.Util;

class A1{
	
}

interface I1{
	
}

interface I2{
	
}
class ClassA<T>{
	T t;
	public ClassA(){
	}
}

public class Generic {

	public static void main(String[] args) {
		ClassA<A1> obj = new ClassA();
		Util.P(obj.t.getClass());
	}

}
