package br.com.padroes.composicao;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import br.com.padroes.comporsicao.MeuSet;

public class MeuSetTeste {

	@Test
	public void contarTotalElementos() {
		MeuSet<String> lista = new MeuSet<String>();
		lista.addAll(Arrays.asList("um", "dois", "três"));
		
		assertEquals(3, lista.getTotalElementos());
	}
}
