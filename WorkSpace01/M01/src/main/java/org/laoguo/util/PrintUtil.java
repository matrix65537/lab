package org.laoguo.util;

public class PrintUtil {

	public static void PLN(Object obj) {
		System.out.println(obj);
	}

	public static void P(Object obj) {
		System.out.print(obj);
	}

	public static String S(byte[] data, int off, int len) {
		StringBuffer sb = new StringBuffer();
		while (off < len) {
			byte v = data[off];
			String temp = Integer.toHexString(0xFF & v);
			if (temp.length() < 2) {
				sb.append("0");
			}
			sb.append(temp);
			if (off != (len - 1)) {
				sb.append(" ");
			}
			off += 1;
		}
		return sb.toString().toUpperCase();
	}

	public static String S(byte[] data) {
		return S(data, 0, data.length);
	}

	public static String DS(byte[] data, int off, int len, int col) {
		if (len == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		while (off < len) {
			byte v = data[off];
			String temp = Integer.toHexString(0xFF & v);
			if (temp.length() < 2) {
				sb.append("0");
			}
			sb.append(temp);
			sb.append(" ");
			if (off % col == (col - 1)) {
				sb.append("\n");
			}
			off += 1;
		}
		if (len % col != 0) {
			sb.append("\n");
		}

		return sb.toString().toUpperCase();
	}

	public static String DS(byte[] data) {
		return DS(data, 0, data.length, 0x10);
	}

	public static void main(String[] args) {
		byte[] data = new byte[0x100];
		PLN(S(data));
		PLN(DS(data));
	}
}
