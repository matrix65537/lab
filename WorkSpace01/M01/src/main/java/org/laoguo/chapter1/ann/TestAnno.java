package org.laoguo.chapter1.ann;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.laoguo.util.PrintUtil.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface ANN1 {
	public String value();

	public int X();

	public int Y();

	public int Z();
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface ANN2 {
	public Class<?> value();
}

@ANN2(B.class)
@ANN1(X = 1, Y = 2, Z = 3, value = "AAA")
class A {
}

class B {

}

public class TestAnno {

	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> cls = Class.forName("org.laoguo.chapter1.ann.A");
		// PLN(cls);
		Annotation[] anns = cls.getAnnotations();

		for (Annotation ann : anns) {
			if (ann instanceof ANN1) {
				PLN(((ANN1) ann).X());
			} else if (ann instanceof ANN2) {
				PLN(((ANN2) ann).value());
			} else {
				PLN("--");
			}
			// PLN(ann);
		}
	}
}
