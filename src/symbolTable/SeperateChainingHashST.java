package symbolTable;

import queue.LinkedQueue;
import queue.Queue;

public class SeperateChainingHashST<Key extends Comparable<Key>, Value> {
	private int N;   // number of keys
	private int M;   // hash table size
	private LinkedST<Key, Value>[] st;
	
	public SeperateChainingHashST() {
		this(997);
	}
	
	public SeperateChainingHashST(int M) {
		this.M = M;
		st = (LinkedST<Key, Value>[]) new LinkedST[M];
		for (int i = 0; i < M; i++) {
			st[i] = new LinkedST<Key, Value>();
		}
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	public Value get(Key key) {
		return st[hash(key)].get(key);
	}
	
	public void put(Key key, Value val) {
		st[hash(key)].put(key, val);
	}
	
	public void delete(Key key) {
		st[hash(key)].delete(key);
	}
	
	public Iterable<Key> keys() {
		Queue<Key> queue = new LinkedQueue<Key>();
		for (int i = 0; i < M; i++) {
			for (Key key : st[i].keys()) {
				queue.enqueue(key);
			}
		}
		return queue;
	}
	
}
