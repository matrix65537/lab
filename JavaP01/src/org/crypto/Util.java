package org.crypto;

public class Util {

	public static void dump(byte[] p){
		Util.dump(p, 0, p.length);
	}

	public static void dump(byte[] p, int offset, int len){
		for(int i = 0; i < len; ++i){
			System.out.printf("%02X", p[offset + i]);
		}
		System.out.println();
	}
}
