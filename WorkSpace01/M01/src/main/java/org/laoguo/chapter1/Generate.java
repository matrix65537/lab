package org.laoguo.chapter1;

import java.util.ArrayList;
import java.util.List;

public class Generate {

	public <T> T f1(T a) {
		return a;
	}

	public void test1(){
		List<String> list = new ArrayList();
		list = f1(list);
	}
	
	public static void main(String[] args) {

	}

}
