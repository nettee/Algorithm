package symbolTable;

import stdlib.Range;

public class TestThreadBST {

	public static void main(String[] args) {
		ThreadBST<String, Integer> bst = new ThreadBST<String, Integer>();
		
		//String[] input = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
		String[] input = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};
		for (int i = 0; i < input.length; i++) {
			bst.put(input[i], i);
		}

		bst.print();
		
		System.out.println("Level order:");
		bst.printLevel();
		
		System.out.println("min: " + bst.min());
		System.out.println("max: " + bst.max());
		
		String[] r = {"A", "B", "C", "D", "E", "F"};
		for (String s : r) {
			System.out.println(s + ": floor = " + bst.floor(s) + ", ceiling = " + bst.ceiling(s));
		}
		
		for (int i : Range.range(10)) {
			System.out.println("select(" + i + ") = " + bst.select(i));
		}
	}

}
