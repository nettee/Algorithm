package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] a;
	private int N;
	
	public RandomizedQueue() {
		a = (Item[]) new Object[1];
		N = 0;
	}
	
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
	
	public void enqueue(Item item) {
		if (item == null) {
			throw new NullPointerException("add null item to queue");
		}
		if (N == a.length) {
			resize(2 * a.length);
		}
		a[N++] = item;
	}
	
	// delete and return a random item
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("dequeue item from empty queue");
		}
		// pick a random item and switch it with the last item
		int rand = StdRandom.uniform(N);
		Item item = a[rand];
		a[rand] = a[--N];
		a[N] = null;
		if (N > 0 && N == a.length/4) {
			resize(a.length/2);
		}
		return item;
	}
	
	// return (but not delete) a random item
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException("sample item from empty queue");
		}
		int rand = StdRandom.uniform(N);
		return a[rand];
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedArrayIterator();
	}
	
	private class RandomizedArrayIterator implements Iterator<Item> {
		private Item[] b;
		private int i = 0;
		
		RandomizedArrayIterator() {
			b = (Item[]) new Object[N];
			for (int j = 0; j < N; j++) {
				b[j] = a[j];
			}
			StdRandom.shuffle(b);
		}
		public boolean hasNext() {
			return i < N;
		}
		public Item next() {
			if (i == N) {
				throw new NoSuchElementException("no more item for next()");
			}
			return b[i++];
		}
		public void remove() {
			throw new UnsupportedOperationException("'remove' unsupported");
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
