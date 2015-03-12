package symbolTable;

import queue.LinkedQueue;

public class ArrayST<Key extends Comparable<Key>, Value> 
		implements ST<Key, Value> {

	private Key[] keys;
	private Value[] vals;
	private int N;
	
	public ArrayST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
		N = 0;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return N;
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public void put(Key key, Value val) {
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}
		for (int j = N; j > i; j--) {
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	public Key min() {
		return keys[0];
	}
	
	public Key max() {
		return keys[N-1];
	}

	public Value get(Key key) {
		if (isEmpty()) {
			return null;
		}
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			return vals[i];
		} else {
			return null;
		}
	}
	
	public void deleteMin() {
		for (int i = 1; i < N; i++) {
			keys[i-1] = keys[i];
			vals[i-1] = vals[i];
		}
		N--;
	}
	
	public void deleteMax() {
		N--;
	}
	
	public void delete(Key key) {
		int i = rank(key);
		if (i == N || keys[i].compareTo(key) != 0) {
			return;
		}
		for (int j = i+1; j < N; j++) {
			keys[j-1] = keys[j];
			vals[j-1] = vals[j];
		}
		N--;
	}
	
	public Key floor(Key key) {
		int i = rank(key);
		if (keys[i].equals(key)) {
			return keys[i];
		} else {
			return keys[i-1];
		}
	}

	public Key ceiling(Key key) {
		int i = rank(key);
		return keys[i];
	}
	
	public Key select(int k) {
		return keys[k];
	}

	// number of keys less than key
	public int rank(Key key) {
		// use binary search
		int low = 0;
		int high = N-1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0) {
				high = mid - 1;
			} else if (cmp > 0) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return low;
	}

	public Iterable<Key> keys() {
		LinkedQueue<Key> q = new LinkedQueue<Key>();
		for (int i = 0; i < N; i++) {
			q.enqueue(keys[i]);
		}
		return q;
	}
	
	public Iterable<Key> keys(Key low, Key high) {
		LinkedQueue<Key> q = new LinkedQueue<Key>();
		for (int i = rank(low); i < rank(high); i++) {
			q.enqueue(keys[i]);
		}
		if (contains(high)) {
			q.enqueue(keys[rank(high)]);
		}
		return q;
	}
	
	public void print() {
		for (int i = 0; i < N; i++) {
			System.out.print("<" + keys[i] + ", " + vals[i] + ">");
		}
		System.out.println();
	}
}
