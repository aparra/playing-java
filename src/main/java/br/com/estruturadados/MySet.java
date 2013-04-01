package br.com.estruturadados;

public class MySet {

	public static abstract class Set {
		public abstract boolean contains(int element);
		
		public Set add(int element) {
			return union(this, singleton(element));
		}
		
		public static Set singleton(final int elem) {
			return new Set() {
				@Override
				public boolean contains(int element) {
					return elem == element;
				}
			};
		}
		
		private Set union(final Set set1, final Set set2) {
			return new Set() {
				@Override
				public boolean contains(int element) {
					return set1.contains(element) || set2.contains(element) ;
				}
			};
		}
	}
	
	public static void main(String[] args) {
		Set numerosPares = new Set() {
			@Override
			public boolean contains(int element) {
				return element % 2 == 0;
			}
		};

		System.out.println(numerosPares.contains(3000));
		
		Set mySet = Set.singleton(1).add(2).add(3).add(4);
		
		System.out.println(mySet.contains(1));
		System.out.println(mySet.contains(2));
		System.out.println(mySet.contains(3));
		System.out.println(mySet.contains(4));
		System.out.println(mySet.contains(5));
	}
}
