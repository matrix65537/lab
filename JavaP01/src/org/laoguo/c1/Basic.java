package org.laoguo.c1;

class A1{
	void f3(){
		System.out.println("A1 f1");
	}
}

class A2 extends A1{

	public void f1() {
		System.out.println("f1");
	}

	public void f2() {
		System.out.println("f2");
	}
}

public class Basic {

	public static void main(String[] args) {
		A2 obj = new A2();
		obj.f1();
		obj.f2();
		obj.f3();
	}
}
