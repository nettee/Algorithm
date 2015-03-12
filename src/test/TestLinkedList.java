package test;

import list.LinkedList;
import stdlib.StdOut;

public class TestLinkedList {
	
	private static int[] sizes = {5, 50, 500, 1000};
	
	private static boolean test1() throws TestFailureException {
		
		StdOut.println("Test 1: calls to addFirst() only");
		boolean isFail = false;
		
		for (int size : sizes) {
			StdOut.printf("* %5d random calls\n", size);
			
			LinkedList<Integer> list = new LinkedList<Integer>();
			java.util.LinkedList<Integer> reference = new java.util.LinkedList<Integer>();
			int[] dataRandoms = StdTest.getDataRandoms(size);
			
			for (int i = 0; i < size; i++) {
				int data = dataRandoms[i];
				list.addFirst(data);
				reference.addFirst(data);
				
				if (list.size() != reference.size()) {
					StdTest.printWrongResult("size", list.size(), reference.size());
					isFail = true;
					break;
				}
			}
		}
		return isFail;
		
	}
	
	private static boolean test2() {
		
		StdOut.println("Test 2: calls to addFirst() and removeFirst()");
		boolean isFail = false;
		
		for (int size : sizes) {
			StdOut.printf("* %5d random calls\n", size);
			
			double[] ratioRandoms = StdTest.getRatioRandoms(size);
			int[] dataRandoms = StdTest.getDataRandoms(size);
			LinkedList<Integer> list = new LinkedList<Integer>();
			java.util.LinkedList<Integer> reference = new java.util.LinkedList<Integer>();
			
			int N = 0;
			for (int i = 0; i < size; i++) {
				int data = dataRandoms[i];
				if (ratioRandoms[i] < 0.9) {
					list.addFirst(data);
					reference.addFirst(data);
					N++;
				} else {
					if (N == 0) {
						// avoid removing from empty container
						break;
					}
					list.removeFirst();
					reference.removeFirst();
					N--;
				}
				
				if (list.size() != reference.size()) {
					StdTest.printWrongResult("size", list.size(), reference.size());
					isFail = true;
					break;
				}
			}
		}
		return isFail;
	}

	public static void main(String[] args) throws TestFailureException {
		StdTest.printFailOrNot(test1());
		StdTest.printFailOrNot(test2());
		
	}
}
