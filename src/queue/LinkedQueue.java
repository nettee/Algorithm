package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Queue<Item> {
	
	private class Node {
		Item item;
		Node next;
	}
	
	private Node first, last;
	private int N;
	
	public LinkedQueue() {
		first = null;
		last = null;
		N = 0;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void enqueue(Item item) {
		if (item == null) {
			throw new NullPointerException("add null item to queue");
		}
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) {
			first = last;
		} else {
			oldlast.next = last;
		}
		N++;
	}
	
	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		if (isEmpty()) {
			last = null;
		}
		N--;
		return item;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext() {
			return current != null;
		}
		public Item next() {
			if (current == null) {
				throw new NoSuchElementException("no more item for next()");
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
		public void remove() {
			throw new UnsupportedOperationException("'remove' unsupported");
		}
	}
}
