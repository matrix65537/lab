package org.laoguo.dproxy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import sun.misc.ProxyGenerator;

public class ProxyUtil {

	private FileOutputStream out;

	public static void saveProxyClass(String path){
		byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy_01", RealSubject.class.getInterfaces());
		try {
			FileOutputStream out = new FileOutputStream(path);
			out.write(classFile);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
