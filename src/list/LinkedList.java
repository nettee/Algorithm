package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<Item> implements Iterable<Item> {
	
	private class Node {
		Item item;
		Node prev;
		Node next;
	}
	
	private int N;
	private Node head, tail;
	
	public LinkedList() {
		head = new Node();
		tail = new Node();
		head.prev = null;
		head.next = tail;
		tail.prev = head;
		tail.next = null;
		N = 0;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException("add null item to list");
		}
		Node newNode = new Node();
		newNode.item = item;
		newNode.prev = head;
		newNode.next = head.next;
		head.next.prev = newNode;
		head.next = newNode;
		N++;
	}
	
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException("add null item to list");
		}
		Node newNode = new Node();
		newNode.item = item;
		newNode.next = tail;
		newNode.prev = tail.prev;
		tail.prev.next = newNode;
		tail.prev = newNode;
		N++;
	}
	
	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("remove item from empty list");
		}
		Item item = head.next.item;
		Node newFirst = head.next.next;
		head.next = newFirst;
		newFirst.prev = head;
		N--;
		return item;
	}
	
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException("remove item from empty list");
		}
		Item item = tail.item;
		Node newLast = tail.prev.prev;
		tail.prev = newLast;
		newLast.next = tail;
		N--;
		return item;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current = head;
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
