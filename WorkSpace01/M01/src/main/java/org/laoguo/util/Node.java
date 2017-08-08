package org.laoguo.util;

interface A {
	public static final int A1 = 0x0A;
}

interface B {
	public static final int B1 = 0x0B;
}

class C {
	public String c1;
	public String c2;
	public String c3;
}

public class Node extends C implements A, B {
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public Node() {
		super();
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.z = 0;
	}

	public Node(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	private int x;
	private int y;
	private int z;
}
