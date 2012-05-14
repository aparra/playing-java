package br.com.padroes.heranca;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class MeuSetTeste {

	@Test
	public void contarTotalElementos() {
		MeuSet<String> lista = new MeuSet<String>();
		lista.addAll(Arrays.asList("um", "dois", "três"));
		
		assertEquals(6, lista.getTotalElementos());
	}
}
