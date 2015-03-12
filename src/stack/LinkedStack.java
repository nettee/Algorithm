package stack;

import java.util.Iterator;

public class LinkedStack<Item> implements Stack<Item> {
	private Node first;
	private int N;
	
	private class Node {
		Item item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return N;
	}
	
	public void push(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	
	public Item pop() {
		if (first == null) {
			throw new NullPointerException();
		}
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	public Item top() {
		if (first == null) {
			throw new NullPointerException();
		}
		return first.item;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext() {
			return current != null;
		}
		public void remove() {
			// not supported
		}
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}
