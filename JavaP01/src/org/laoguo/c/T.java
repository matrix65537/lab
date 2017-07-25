package org.laoguo.c;

import static org.laoguo.util.Util.P;

class Node{
	int x;
	int y;
}

class Class1<A, B, C, D> {
	A a1;
	B b1;
	C c1;
	D d1;
	
	public Class1(){
		
	}

	public Class1(A a1, B b1, C c1, D d1) {
		this.a1 = a1;
		this.b1 = b1;
		this.c1 = c1;
		this.d1 = d1;
	}
}

public class T {

	public static void main(String[] args) {
		Node n1 = new Node();
		Node n2 = new Node();
		Node n3 = new Node();
		Node n4 = new Node();
		Class1<Node, Node, Node, Node> c1 = new Class1<Node, Node, Node, Node>(n1, n2, n3, n4);
		c1.a1.x = 8;
		P(c1);
		P(Class1.class.getName());
	}

}
