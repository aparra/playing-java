package br.com.padroes.comporsicao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MeuSet<E> extends ForwardingSet<E> {

	private int totalElementos = 0;

	public MeuSet() {
		super(new HashSet<E>());
	}

	public MeuSet(Set<E> elements) {
		super(elements);
	}

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
