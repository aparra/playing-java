package br.com.padroes.heranca;

import java.util.Collection;
import java.util.HashSet;

public class MeuSet<E> extends HashSet<E> {

	private static final long serialVersionUID = 4687149704775438361L;

	private int totalElementos = 0;
	
	@Override
	public boolean add(E element) {
		totalElementos++;
		return super.add(element);
	};
	
	@Override
	public boolean addAll(Collection<? extends E> elements) {
		totalElementos += elements.size();
		return super.addAll(elements);
	}

	public int getTotalElementos() {
		return totalElementos;
	}
}
