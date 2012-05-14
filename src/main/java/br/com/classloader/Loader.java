package br.com.classloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Loader {
	
	public Class<?> loadClass(String clazz) {
		try {
			URL[] bin = new URL[] {new URL("file:src/main/bin/")};
			return new URLClassLoader(bin, null).loadClass(clazz);
		} catch (ClassNotFoundException | MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Class<?> forName(String clazz) {
		try {
			return Class.forName("br.com.dao.HibernateDao");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
}
