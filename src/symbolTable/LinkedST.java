package symbolTable;

import queue.LinkedQueue;

public class LinkedST<Key extends Comparable<Key>, Value>
		implements ST<Key, Value> {
	
	private class Node {
		Key key;
		Value val;
		Node next;
		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
		public String toString() {
			return "<" + key + ", " + val + ">";
		}
	}
	
	private Node first;
	private int N;
	
	public LinkedST() {
		first = null;
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
	
	public Value get(Key key) {
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				return x.val;
			}
		}
		return null;
	}
	
	public void put(Key key, Value val) {
		if (key == null) {
			throw new NullPointerException();
		}
		if (val == null) {
			delete(key);
			return;
		}
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		}
		first = new Node(key, val, first);
		N++;
	}
	
	public Key min() {
		if (first == null) {
			throw new NullPointerException();
		}
		Key min = first.key;
		for (Node t = first; t != null; t = t.next) {
			if (t.key.compareTo(min) < 0) {
				min = t.key;
			}
		}
		return min;
	}
	
	public Key max() {
		if (first == null) {
			throw new NullPointerException();
		}
		Key max = first.key;
		for (Node t = first; t != null; t = t.next) {
			if (t.key.compareTo(max) > 0) {
				max = t.key;
			}
		}
		return max;
	}
	
	public void deleteMin() {
		delete(min());
	}
	
	public void deleteMax() {
		delete(max());
	}

		
	public void delete(Key key) {
		if (first != null && first.key.equals(key)) {
			first = first.next;
			return;
		} 
		for (Node prev = first; prev.next != null; prev = prev.next) {
			Node current = prev.next;
			if (current.key.equals(key)) {
				prev.next = current.next;
				N--;
				return;
			}
		}
	}
	
	public Key floor(Key key) {
		Key max = null;
		for (Node t = first; t != null; t = t.next) {
			if (t.key.compareTo(key) <= 0) {
				if (t.key.compareTo(max) > 0) {
					max = t.key;
				}
			}
		}
		return max;
	}
	
	public Key ceiling(Key key) {
		Key min = null;
		for (Node t = first; t != null; t = t.next) {
			if (t.key.compareTo(key) >= 0) {
				if (t.key.compareTo(min) < 0) {
					min = t.key;
				}
			}
		}
		return min;
	}
	
	public Key select(int k) {
		// TODO improve from O(n^2) to O(nlogn)
		for (Node t = first; t != null; t = t.next) {
			if (rank(t.key) == k) {
				return t.key;
			}
		}
		return null;
	}
	
	public int rank(Key key) {
		int n = 0;
		for (Node t = first; t != null; t = t.next) {
			if (t.key.compareTo(key) < 0) {
				n++;
			}
		}
		return n;
	}
	
	public Iterable<Key> keys() {
		LinkedQueue<Key> q = new LinkedQueue<Key>();
		for (Node x = first; x != null; x = x.next) {
			q.enqueue(x.key);
		}
		return q;
	}

	public void print() {
		for (Node t = first; t != null; t = t.next) {
			System.out.print(t + " ");
		}
		System.out.println();
	}

}
