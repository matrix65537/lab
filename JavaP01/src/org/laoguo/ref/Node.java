package org.laoguo.ref;

public class Node{
	private int x;
	private int y;
	
	public Node(){
		this.x = 0;
		this.y = 0;
	}
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return "[" + x + ", " + y +  "]";
	}
}
