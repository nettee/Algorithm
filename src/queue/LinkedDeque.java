package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** double-ended queue
 * support inserting and removing from either the front or the back
 * each inserting and removing method takes constant time cost
 * 
 * generic, support any type
 * 
 * @author William
 *
 * @param <Item>
 */
public class LinkedDeque<Item> implements Queue<Item> {
	
	private class Node {
		Item item;
		Node prev;
		Node next;
	}
	
	private int N;
	private Node first, last;
	
	public LinkedDeque() {
		first = null;
		last = null;
		N = 0;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void enqueue(Item item) {
		addLast(item);
	}
	
	public Item dequeue() {
		return removeFirst();
	}
	
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException("add null item to deque");
		}
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.prev = null;
		first.next = oldfirst;
		if (oldfirst == null) { // add to empty deque
			last = first;
		} else {
			oldfirst.prev = first;
		}
		N++;
	}
	
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException("add null item to deque");
		}
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.prev = oldlast;
		last.next = null;
		if (oldlast == null) { // add to empty deque
			first = last;
		} else {
			oldlast.next = last;
		}
		N++;
	}
	
	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("remove item from empty deque");
		}
		Item item = first.item;
		first = first.next;
		if (first == null) {
			// remove from single-item deque
			last = null;
		} else {
			// avoid loitering of removed node
			first.prev = null;
		}
		N--;
		return item;
	}
	
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException("remove item from empty deque");
		}
		Item item = last.item;
		last = last.prev;
		if (last == null) {
			// remove from single-item deque
			first = null;
		} else {
			// avoid loitering of removed node
			last.next = null;
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
