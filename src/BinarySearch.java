
public class BinarySearch {

	public static int rank(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key < a[mid]) {
				hi = mid - 1;
			} else if (key > a[mid]) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] theList = {1, 4, 6, 8, 9};
		for (int i = 1; i < 10; i++) {
			if (rank(i, theList) == -1) {
				System.out.print(i);
				System.out.print(" ");
			}
		}
	}

}
