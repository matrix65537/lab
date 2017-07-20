package org.laoguo.proxy;

public class SubjectStaciFactory {
	public static Subject getInstance(){
		return new ProxySubject(new RealSubject());
	}
}
