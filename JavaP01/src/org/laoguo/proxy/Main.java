package org.laoguo.proxy;

public class Main {

	public static void main(String[] args) {
		Subject proxy = SubjectStaciFactory.getInstance();
		proxy.dealTaks("ABCD");
	}
}
