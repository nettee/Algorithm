package stdlib;

public class BinarySearch {
	
	public static int search(int[] a, int target) {
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (a[mid] > target) {
				high = mid - 1;
			} else if (a[mid] < target) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
