package symbolTable;

import stdlib.Range;

public class TestBST {

	public static void main(String[] args) {
		
		AvlTree<String, Integer> bst = new AvlTree<String, Integer>();
		
		//String[] input = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
		String[] input = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};
		for (int i = 0; i < input.length; i++) {
			bst.put(input[i], i);
		}

		bst.print();
		
		System.out.println("Level order:");
		bst.printLevel();
		
		System.out.println("Preorder:");
		bst.printPreorder();
		
		System.out.println("min: " + bst.min());
		System.out.println("max: " + bst.max());
		
		String[] r = {"A", "B", "C", "D", "E", "F"};
		for (String s : r) {
			System.out.println(s + ": floor = " + bst.floor(s) + ", ceiling = " + bst.ceiling(s));
		}
		
		for (int i : Range.range(10)) {
			System.out.println("select(" + i + ") = " + bst.select(i));
		}
		
		System.out.println(bst.height());
		
	}

}
