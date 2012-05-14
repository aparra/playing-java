package br.com.classloader;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class LoaderTest {

	@Test
	public void equalSameClass() {
		Loader loader = new Loader();

		Class<?> clazz = loader.forName("br.com.dao.HibernateDao");
		Class<?> sameClazz = loader.forName("br.com.dao.HibernateDao");
	
		System.out.println(clazz.getClassLoader());
		System.out.println(sameClazz.getClassLoader());
		System.out.println(clazz == sameClazz);
		System.out.println(clazz.equals(sameClazz));
		
		assertEquals(clazz, sameClazz);
	}
	
}
