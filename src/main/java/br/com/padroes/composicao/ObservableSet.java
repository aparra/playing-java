package br.com.padroes.composicao;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObservableSet<E> extends ForwardingSet<E> {

	private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<SetObserver<E>>();
	
	public ObservableSet(Set<E> elements) {
		super(elements);
	}

	public void addObserver(SetObserver<E> observer) {
		observers.add(observer);
	}

	public boolean removeObserver(SetObserver<E> observer) {
		return observers.remove(observer);
	}
	
	private void notifyElementAdded(E element) {
		for (SetObserver<E> observer : observers) {
			observer.added(this, element);
		}
	}

	@Override
	public boolean add(E element) {
		boolean added = super.add(element);
		if (added) {
			notifyElementAdded(element);
		}
		return added;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> collection) {
		boolean result = false;
		for (E element : collection) {
			result |= add(element);
		}
		return result;
	}

	public static void main(String[] args) {
		ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());
		
		set.addObserver(new SetObserver<Integer>() {
			@Override
			public void added(ObservableSet<Integer> set, Integer element) {
				System.out.println(element);
			}
		});
		
		for (int i = 0; i < 100; i++) {
			set.add(i);
		}
	}
	
	public interface SetObserver<E> {
		void added(ObservableSet<E> set, E element);
	}
}
