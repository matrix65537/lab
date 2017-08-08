package org.laoguo.chapter1.generic;

import static org.laoguo.util.PrintUtil.*;

import java.util.List;
import java.util.ArrayList;

interface IA {

}

class A implements IA {

}

class B<T extends A> {
	private T e;

	public T getE() {
		return e;
	}
}

class C extends A{
	
}

class C1{}

class C2 extends C1{
	
}

public class Generic {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
	}
}
