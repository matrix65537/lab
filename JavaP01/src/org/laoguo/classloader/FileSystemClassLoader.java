package org.laoguo.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class FileSystemClassLoader extends ClassLoader {

	private String rootDir;

	public FileSystemClassLoader(String rootDir) {
		this.rootDir = rootDir;
	}

	private String classNameToPath(String className) {
		return rootDir + File.separator
				+ className.replace(".", File.separator) + ".class";
	}

	protected Class<?> findClass(String className)
			throws ClassNotFoundException {
		byte[] classData = getClassData(className);
		if (classData == null) {
			throw new ClassNotFoundException();
		} else {
			return defineClass(className, classData, 0, classData.length);
		}
	}

	private byte[] getClassData(String className) {
		String path = classNameToPath(className);
		InputStream ins;
		try {
			ins = new FileInputStream(path);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int bufferSize = 4096;
			byte[] buffer = new byte[bufferSize];
			int bytesNumRead = 0;
			while ((bytesNumRead = ins.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesNumRead);
			}
			ins.close();
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		String rootDir = "C:\\dev2";
		String className = "org.laoguo.classloader.Node";
		

		FileSystemClassLoader fclA = new FileSystemClassLoader(rootDir);
		FileSystemClassLoader fclB = new FileSystemClassLoader(rootDir);

		Class<?> classA = fclA.loadClass(className);
		Class<?> classB = fclB.loadClass(className);
		
		System.out.println(classA.hashCode());
		System.out.println(classB.hashCode());
		
		Object objA = classA.newInstance();
		Object objB = classB.newInstance();
		System.out.println(objA.getClass().getClassLoader());
		System.out.println(objB.getClass().getClassLoader());
		
		Method method = classA.getMethod("set", Object.class);
		//method.invoke(objA, objB);
	}
}
