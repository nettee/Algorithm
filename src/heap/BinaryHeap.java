package heap;

import java.util.NoSuchElementException;

public class BinaryHeap<Key extends Comparable<Key>> implements PQ<Key> {
	
	private int N;
	private Key[] a;
	
	public BinaryHeap() {
		N = 0;
		a = (Key[]) new Comparable[100];
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return N;
	}
	
	private void resize(int max) {
		Key[] temp = (Key[]) new Comparable[max];
		for (int i = 0; i < N; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}
	
	public void insert(Key key) {
		if (key == null) {
			throw new NullPointerException("insert null key to heap");
		}
		if (N == a.length) {
			resize(2 * a.length);
		}
		
		// percolate up
		int hole = N++;
		while (hole > 0 && key.compareTo(a[(hole-1)/2]) < 0) {
			a[hole] = a[(hole-1)/2];
			hole = (hole - 1) / 2;
		}
		a[hole] = key;
	}
	
	public Key remove() {
		if (isEmpty()) {
			throw new NoSuchElementException("remove item form empty heap");
		}
		
		Key min = top();
		a[0] = a[--N];
		percolateDown(0);
		
		if (N > 0 && N == a.length / 4) {
			resize(a.length/2);
		}
		
		return min;
	}
	
	public Key top() {
		return a[0];
	}
	
	private void percolateDown(int hole) {
		int child;
		Key temp = a[hole];
		
		while (hole * 2 + 1 <= N) {
			
			child = hole * 2 + 1;  // left child
			if (child != N-1 && a[child+1].compareTo(a[child]) < 0) {
				// if a right child is smaller, choose it
				child++;
			}
			
			if (a[child].compareTo(temp) < 0) {
				a[hole] = a[child];
			} else {
				break;
			}
			
			hole = child;
		}
		a[hole] = temp;
	}
	
	public void fromArray(Key[] from) {
		N = from.length;
		for (int i = 0; i < N; i++) {
			a[i] = from[i];
		}
	}
	
	public Key[] toArray() {
		Key[] to = (Key[]) new Comparable[N];
		for (int i = 0; i < N; i++) {
			to[i] = a[i];
		}
		return to;
	}
	
	public void print() {
		int tag = 2;
		for (int i = 0; i < N; i++) {
			if (i == tag - 1) {
				System.out.println();
				tag *= 2;
			}
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
}
