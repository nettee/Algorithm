package sorting;

import stdlib.StdOut;

public class Inversion {
	
	private static int[] aux;
	
	public static int inversion(int[] a) {
		aux = new int[a.length];
		return inversionCount(a, 0, a.length-1);
	}
	
	private static int inversionCount(int[] a, int low, int high) {
//		StdOut.printf("merge sort a[%d:%d]: ", low, high);
//		printArray(a);
		if (high <= low) {
			return 0;
		}
		int mid = low + (high - low + 1) / 2;
		int x = inversionCount(a, low, mid-1); 
		int y = inversionCount(a, mid, high);
		int z = splitInversionCount(a, low, mid, high);
		return x + y + z;
	}
	
	private static int splitInversionCount(int[] a, int low, int mid, int high) {
		for (int i = low; i <= high; i++) {
			aux[i] = a[i];
		}
		int i = low;
		int j = mid;
		int sum = 0;
		for (int k = low; k <= high; k++) {
			if (j > high) {
				a[k] = aux[i++];
			} else if (i >= mid) {
				a[k] = aux[j++];
			} else if (aux[i] < aux[j]) {
				a[k] = aux[i++];
			} else {
				a[k] = aux[j++];
				sum += mid - i;
			}
		}
		return sum;
	}
	
	public static boolean isSorted(int[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[i-1] > a[i]) {
				return false;
			}
		}
		return true;
	}
	
	private static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
	}
	
	private static void printInversion(int[] a) {
		int N = a.length;
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				if (a[i] > a[j]) {
					StdOut.printf("(%d, %d)\n", a[i], a[j]);
					count++;
				}
			}
		}
		StdOut.println("// totally " + count + " inversions.");
	}

	public static void main(String[] args) {
		int[] a = { 40, 33, 51, 7, 29, 36, 81, 62};
		StdOut.print("array a: ");
		printArray(a);
		printInversion(a);
		StdOut.printf("inversion of a: %d\n", inversion(a));
		
	}
}
