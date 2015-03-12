package sorting;

import java.util.Iterator;
import java.util.NoSuchElementException;

// single linked list for sorting practise
public class LinkedListSorting {
	
	private class Node {
		Integer item;
		Node next;
	}
	
	private Node first;
	private int N;
	
	public LinkedListSorting() {
		first = null;
		N = 0;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void addFirst(Integer item) {
		if (item == null) {
			throw new NullPointerException("add null item to list");
		}
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		N++;
	}
	
	public Integer removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("remove item from empty list");
		}
		Integer item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	public boolean isSorted() {
		if (first == null) {
			return true;
		}
		for (Node t = first; t.next != null; t = t.next) {
			if (t.item.compareTo(t.next.item) > 0) {
				return false;
			}
		}
		return true;
	}
	
	public void sort() {
		// selection sort
		// invariant: elements before t are already sorted
		// and no elements after t(including) is smaller than those before t
		for (Node t = first; t != null; t = t.next) {
			// exchange t.item with smallest item after t
			Node min = t;
			for (Node r = t.next; r != null; r = r.next) {
				if (r.item.compareTo(min.item) < 0) {
					min = r;
				}
			}
			Integer temp = t.item;
			t.item = min.item;
			min.item = temp;
		}
	}

}
