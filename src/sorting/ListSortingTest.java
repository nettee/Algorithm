package sorting;

import stdlib.StdOut;
import stdlib.StdRandom;

public class ListSortingTest {

	public static void main(String[] args) {
		LinkedListSorting list = new LinkedListSorting();
		for (int i = 0; i < 20; i++) {
			list.addFirst(StdRandom.uniform(100));
		}
		list.sort();
		if (list.isSorted()) {
			StdOut.println("Good Sort!");
		} else {
			StdOut.println("boom!");
		}
	}
}
