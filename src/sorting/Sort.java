package sorting;

public class Sort {

	public static void main(String[] args) {
		String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
		mergeSort(a);
		assert isSorted(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	public static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i-1])) {
				return false;
			}
		}
		return true;
	}
	
	private static void exchange(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	// auxiliary array for merge sort
	private static Comparable[] aux;
	
	public static void mergeSort(Comparable[] a) {
		aux = new Comparable[a.length];
		mergeSort(a, 0, a.length - 1);
	}
	
	private static void mergeSort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int mid = lo + (hi - lo) / 2;
		mergeSort(a, lo, mid);
		mergeSort(a, mid+1, hi);
		merge(a, lo, mid, hi);
	}
	
	private static void merge(Comparable[] a, int lo, int mid, int hi) {
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
	
	public static void shellSort(Comparable[] a) {
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
	
	public static void insertionSort(Comparable[] a) {
		int N = a.length;
		// invariant: a[0..i) is already sorted
		for (int i = 1; i < N; i++) {
			// insert a[i] among a[0..i)
			for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
				exchange(a, j, j-1);
			}
		}
	}
	
	public static void selectSort(Comparable[] a) {
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
