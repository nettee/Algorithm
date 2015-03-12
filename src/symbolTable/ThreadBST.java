package symbolTable;

public class ThreadBST<Key extends Comparable<Key>, Value> extends BST<Key, Value>{

	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private Node prev, succ;
		private int N;  // number of nodes in subtree
		
		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
		
		public String toString() {
			return "<" + key + ", " + val + ">";
		}
	}

}
