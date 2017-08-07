package org.laoguo.chapter1;

public class TestArgs {

	public static void main(String[] args) {
		int length = args.length;
		for (int i = 0; i < length; ++i) {
			System.out.println("" + i + ": " + args[i]);
		}
	}

}
