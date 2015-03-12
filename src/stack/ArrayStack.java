package stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<Item> implements Stack<Item> {
	
	private Item[] a = (Item[]) new Object[1];
	private int N = 0;
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}
	
	public void push(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}
		if (N == a.length) {
			resize(2 * a.length);
		}
		a[N++] = item;
	}
	
	public Item pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("pop from empty stack");
		}
		Item item = a[--N];
		a[N] = null;
		if (N > 0 && N == a.length/4) {
			resize(a.length/2);
		}
		return item;
	}
	
	public Item top() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return a[N-1];
	}
	
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item> {
		private int i = N;
		public boolean hasNext() { return i > 0; }
		public Item next() { return a[--i]; }
		public void remove() {}
	}
}

