package org.laoguo.chapter2;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClassUtil {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ClassUtil.class);

	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	public static Class<?> loadClass(String className, boolean isInitialized) {
		Class<?> cls;
		try {
			cls = Class.forName(className, isInitialized, getClassLoader());
		} catch (ClassNotFoundException e) {
			LOGGER.error("load class failure", e);
			throw new RuntimeException(e);
		}
		return cls;
	}

	public static Set<Class<?>> getClassSet(String packageName) {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(
					packageName.replace(".", "/"));
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (url != null) {
					String protocol = url.getProtocol();
					if (protocol.equals("file")) {
						String packagePath = url.getPath().replace("%20", " ");
						addClass(classSet, packagePath, packageName);
					} else if (protocol.equals("jar")) {
						// TODO
					}
				}
			}

		} catch (Exception e) {
			LOGGER.error("get class set failure", e);
			throw new RuntimeException(e);
		}
		return classSet;
	}

	private static void addClass(Set<Class<?>> classSet, String packagePath,
			String packageName) {
		File[] files = new File(packagePath).listFiles(new FileFilter() {

			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(".class"))
						|| file.isDirectory();
			}
		});
		for (File file : files) {
			String fileName = file.getName();
			if (file.isFile()) {
				String className = fileName.substring(0,
						fileName.lastIndexOf("."));
				if (StringUtil.isNotEmpty(className)) {
					className = packageName + "." + className;
				}
				doAddClass(classSet, className);
			} else {
				String subPackagePath = fileName;
				if (StringUtil.isNotEmpty(packagePath)) {
					subPackagePath = packagePath + "/" + subPackagePath;
				}
				String subPackageName = fileName;
				if (StringUtil.isNotEmpty(packageName)) {
					subPackageName = packageName + "." + subPackageName;
				}
				addClass(classSet, subPackagePath, subPackageName);
			}
		}
	}

	private static void doAddClass(Set<Class<?>> classSet, String className) {
		Class<?> cls = loadClass(className, false);
		classSet.add(cls);
	}
}
