package br.com.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.padroes.composicao.ForwardingSet;

public class ObservableSet<E> extends ForwardingSet<E> {

	private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();
	
	public ObservableSet(Set<E> elements) {
		super(elements);
	}

	public void addObserver(SetObserver<E> observer) {
		synchronized (observers) {
			observers.add(observer);
		}
	}
	
	public boolean removeObserver(SetObserver<E> observer) {
		synchronized (observers) {
			return observers.remove(observer);
		}
	}
	
	/* uso incorreto de syncronized - chamada de método discrepante
	private void notifyElementAdded(E element) {
		synchronized (observers) {
			for (SetObserver<E> observer : observers) {
				observer.added(this, element);
			}
		}
	}
	*/
	
	private void notifyElementAdded(E element) {
		List<SetObserver<E>> snapshot = null;
		
		synchronized (observers) {
			snapshot = new ArrayList<SetObserver<E>>(observers);
		}
		
		for (SetObserver<E> observer : snapshot) {
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
	
	public interface SetObserver<E> {
		void added(ObservableSet<E> set, E element);
	}
	
	public static void main(String[] args) {
		testeDeadLock();
	}
	
	private static void testeSucesso() {
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
	
	private static void testeConcurrentModification() {
		ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());
		
		set.addObserver(new SetObserver<Integer>() {
			@Override
			public void added(ObservableSet<Integer> set, Integer element) {
				System.out.println(element);
				if (element == 23) {
					set.removeObserver(this);
				}
			}
		});
		
		for (int i = 0; i < 100; i++) {
			set.add(i);
		}
	}
	
	private static void testeDeadLock() {
		ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());
		
		set.addObserver(new SetObserver<Integer>() {
			@Override
			public void added(final ObservableSet<Integer> set, Integer element) {
				System.out.println(element);
				if (element == 23) {
					ExecutorService executor = Executors.newSingleThreadExecutor();
					final SetObserver<Integer> observer = this;
					
					try {
						executor.submit(new Runnable() {
							@Override
							public void run() {
								set.removeObserver(observer);
							}
						}).get();
					} catch (InterruptedException | ExecutionException e) {
						new AssertionError(e.getCause());
					} finally {
						executor.shutdown();
					}
				}
			}
		});
		
		for (int i = 0; i < 100; i++) {
			set.add(i);
		}
	}
}
