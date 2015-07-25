package sorting;

import stdlib.Arrai;

public class SortExample {

	public static void main(String[] args) {
		int[] a = {3,1,4,15,9,2,6,53,58,97,93,23,8,46,26,43,6,97};
		mergeSort(a);
		if (!isSorted(a)) {
			throw new IllegalStateException();
		}
	}
	
	private static boolean less(int v, int w) {
		return v < w;
	}
	
	public static boolean isSorted(int[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i-1])) {
				return false;
			}
		}
		return true;
	}
	
	private static void exchange(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	// auxiliary array for merge sort
	private static int[] aux;
	
	public static void mergeSort(int[] a) {
		aux = new int[a.length];
		mergeSort(a, 0, a.length - 1);
	}
	
	private static void mergeSort(int[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int mid = lo + (hi - lo) / 2;
		mergeSort(a, lo, mid);
		System.out.print("left ");
		Arrai.print(a);
		mergeSort(a, mid+1, hi);
		System.out.print("right ");
		Arrai.print(a);
		merge(a, lo, mid, hi);
		System.out.print("merge ");
		Arrai.print(a);
	}
	
	private static void merge(int[] a, int lo, int mid, int hi) {
		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) {
				a[k] = aux[i++];
			} else if (less(aux[j], aux[i])) {
				a[k] = aux[j++];
			} else {
				a[k] = aux[i++];
			}
		}
	}
	
	
	public static void quickSort(int[] a) {
		quickSort(a, 0, a.length-1);
	}
	
	private static void quickSort(int[]a, int lo, int hi) {
		if (hi <= lo) return;
		int j = partition(a, lo, hi);
		System.out.print(String.format("(%d, %d) ", j, a[j]));
		Arrai.print(a);
		quickSort(a, lo, j-1);
		quickSort(a, j+1, hi);
	}
	
	private static int partition(int[] a, int lo, int hi) {
		int i = lo, j = hi + 1;
		int v = a[lo];
		while (true) {
			while (a[++i] < v) if (i == hi) break;
			while (v < a[--j]) if (j == lo) break;
			if (i >= j) break;
			exchange(a, i, j);
		}
		exchange(a, lo, j);
		return j;
	}
	
	public static void shellSort(int[] a) {
		int N = a.length;
		int h = 1;
		while (h < N / 3) {
			h = 3 * h + 1;
		}
		
		while (h >= 1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
					exchange(a, j, j-h);
				}
			}
			h = h / 3;
		}
	}
	
	public static void insertionSort(int[] a) {
		int N = a.length;
		// invariant: a[0..i) is already sorted
		for (int i = 1; i < N; i++) {
			Arrai.print(a);
			// insert a[i] among a[0..i)
			for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
				exchange(a, j, j-1);
			}
		}
		Arrai.print(a);
	}
	
	public static void selectSort(int[] a) {
		int N = a.length;
		// invariant: a[0..i) is already sorted
		// and no element in a[i..n) is smaller than element in a[0..i)
		for (int i = 0; i < N; i++) {
			// exchange a[i] with smallest item in a[i+1..N)
			int min = i;
			for (int j = i + 1; j < N; j++) {
				if (less(a[j], a[min])) {
					min = j;
				}
			}
			exchange(a, i, min);
		}
	}
	
}
