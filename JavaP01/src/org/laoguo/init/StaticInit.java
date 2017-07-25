package org.laoguo.init;

import org.laoguo.util.*;

class A{
	public A(String info){
		Util.P(info);
	}
}

class B{
	public B(String info){
		Util.P(info);
	}
}

class C{
	private static A sa1 = new A("static a1");
	private A ia1 = new A("a1");
	
	private static A sa2 = new A("static a2");
	private A ia2 = new A("a2");
	
	static{
		new C("CCC");
		Util.P("C static init");
	}
	
	private static A sa3 = new A("static a3");
	private A ia3 = new A("a3");
	
	public C(String info){
		Util.P(info);
	}
}

class D{
	public C ic1 = new C("ic1");
	public C ic2 = new C("ic2");
	public static C sc1 = new C("sc1");
	public static C sc2 = new C("sc2");
	
	public D(String info){
		Util.P(info);
	}
}

public class StaticInit {
	static D d = new D("sd1");
	public static void main(String[] args) {
		Util.P("begin main");
		D d = new D("id1");
		Util.P("end main");
	}
}
