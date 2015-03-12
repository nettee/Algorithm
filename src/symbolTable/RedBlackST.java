package symbolTable;

public class RedBlackST<Key extends Comparable<Key>, Value> {
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class Node {
		Key key;
		Value val;
		Node left, right;
		int N;
		boolean color;
		
		Node(Key key, Value val, int N, boolean color) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	
	private boolean isRed(Node x) {
		if (x == null) {
			return false;
		}
		return x.color == RED;
	}
	
	Node retateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	
}
