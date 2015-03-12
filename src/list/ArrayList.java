package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<Item> implements Iterable<Item> {
	
	private int N;
	private Item[] a;
	
	public ArrayList() {
		N = 0;
		a = (Item[]) new Object[1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public Item get(int i) {
		if (i < 0 || i >= N) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return a[i];
	}
	
	public Item set(int i, Item item) {
		if (i < 0 || i >= N) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if (item == null) {
			throw new NullPointerException("add null item to arraylist");
		}
		Item old = a[i];
		a[i] = item;
		return old;
	}
	
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}
	
	public void add(Item item) {
		if (item == null) {
			throw new NullPointerException("add null item into arraylist");
		}
		if (N == a.length) {
			resize(2 * a.length);
		}
		a[N++] = item;
	}
	
	public Item remove() {
		if (isEmpty()) {
			throw new NoSuchElementException("remove item from empty arraylist");
		}
		Item item = a[--N];
		a[N] = null;
		if (N > 0 && N == a.length/4) {
			resize(a.length/2);
		}
		return item;
	}
	
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<Item> {
		private int i = 0;
		public boolean hasNext() {
			return i < N;
		}
		public Item next() {
			if (i == N) {
				throw new NoSuchElementException("no more item for next()");
			}
			return a[i++];
		}
		public void remove() {
			throw new UnsupportedOperationException("'remove' unsupported");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
