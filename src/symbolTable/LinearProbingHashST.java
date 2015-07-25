package symbolTable;

import queue.LinkedQueue;
import queue.Queue;

public class LinearProbingHashST<Key, Value> {
	private int N;   // number of keys
	private int M;  // size of table
	private Key[] keys;
	private Value[] vals;
	
	public LinearProbingHashST() {
		this(16);
	}
	
	public LinearProbingHashST(int M) {
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
		N = 0;
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	private void resize(int cap) {
		LinearProbingHashST<Key, Value> temp;
		temp = new LinearProbingHashST<Key, Value>(cap);
		for (int i = 0; i < M; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		M = temp.M;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return N;
	}
	
	public boolean contains(Key key) {
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if (keys[i].equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	public Value get(Key key) {
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if (keys[i].equals(key)) {
				return vals[i];
			}
		}
		return null;
	}
	
	public void put(Key key, Value val) {
		if (N >= M/2) resize(M * 2);
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	public void delete(Key key) {
		if (!contains(key)) {
			return;
		}
		int i = hash(key);
		while (!keys[i].equals(key)) {
			i = (i + 1) % M;
		}
		keys[i] = null;
		vals[i] = null;
		N--;
		
		i = (i + 1) % M;
		while (keys[i] != null) {
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo, valToRedo);
			i = (i + 1) % M;
		}
		
		if (N > 0 && N == M / 8) {
			resize(M / 2);
		}
	}
	
	public Iterable<Key> keys() {
		Queue<Key> queue = new LinkedQueue<Key>();
		for (int i = 0; i < M; i++) {
			if (keys[i] != null) {
				queue.enqueue(keys[i]);
			}
		}
		return queue;
	}
}
