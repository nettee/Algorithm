package symbolTable;

import symbolTable.BST.Node;

public class RedBlackST<Key extends Comparable<Key>, Value> 
//implements ST<Key, Value> 
{
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private boolean isRed(Node x) {
		if (x == null) {
			return false;
		}
		return x.color == RED;
	}
	
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
	
	private Node root;
	
	public RedBlackST() {
		root = null;
	}

	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node x) {
		if (x == null) {
			return 0;
		} else {
			return x.N;
		}
	}
	
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	private void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
		root.color = BLACK;  // flipColors may make root red
	}
	
	private Node put(Node h, Key key, Value val) {
		if (h == null) {
			// do standard insert, with red link to parent
			return new Node(key, val, 1, RED);
		}
		int cmp = key.compareTo(h.key);
		if (cmp < 0) {
			h.left = put(h.left, key, val);
		} else if (cmp > 0) {
			h.right = put(h.right, key, val);
		} else {
			h.val = val;
		}
		
		// rotate adjusting
		if (isRed(h.right) && !isRed(h.left)) {
			h = rotateLeft(h);
		}
		if (isRed(h.left) && isRed(h.left.left)) {
			h = rotateRight(h);
		}
		if (isRed(h.left) && isRed(h.right)) {
			flipColors(h);
		}
		
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
	
	
}
