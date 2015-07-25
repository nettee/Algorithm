package symbolTable;

import java.util.*;

public class BST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
	
	protected class Node {
		Key key;
		Value val;
		Node left, right;
		int N;  // number of nodes in subtree
		int height;
		boolean color = false;  // for Red-Black tree
		Node prev = null, next = null;  // for threaded BST 
		
		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.height = 1;
		}
		
		// for Red-Black tree
		public Node(Key key, Value val, int N, boolean color) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.height = 1;
			this.color = color;
		}
		
		public String toString() {
			return "<" + key + ", " + val + ">";
		}
	}
	
	protected Node root;
	
	public BST() {
		root = null;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return size(root);
	}
	
	// used by sub-classes
	protected int size(Node x) {
		if (x == null) {
			return 0;
		} else {
			return x.N;
		}
	}
	
	public int height() {
		return height(root);
	}
	
	// used in AVL tree
	protected int height(Node x) {
		if (x == null) {
			return 0;
		} else {
			return x.height;
		}
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public Value get(Key key) {
		if (key == null) {
			throw new NullPointerException();
		}
		return get(root, key);
	}
	
	private Value get(Node x, Key key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return get(x.left, key);
		} else if (cmp > 0) {
			return get(x.right, key);
		} else {
			return x.val;
		}
	}
	
	public void put(Key key, Value val) {
		if (key == null) {
			throw new NullPointerException();
		}
		if (val == null) {
			delete(key);
		} else {
			root = put(root, key, val);
		}
	}
	
	private Node put(Node x, Key key, Value val) {
		// returning and assigning is necessary
		if (x == null) {
			// base case: inserting a new key
			return new Node(key, val, 1);
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key, val);
		} else if (cmp > 0) {
			x.right = put(x.right, key, val);
		} else {
			// base case: modify value
			x.val = val;  
		}
		x.N = size(x.left) + size(x.right) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		return x;
	}
	
	public Key min() {
		if (root == null) {
			throw new NullPointerException();
		}
		return min(root).key;
	}
	
	protected Node min(Node x) {
		while (x.left != null) {
			x = x.right;
		}
		return x;
	}
	
	public Key max() {
		if (root == null) {
			throw new NullPointerException();
		}
		return max(root).key;
	}
	
	private Node max(Node x) {
		while (x.right != null) {
			x = x.right;
		}
		return x;
	}
	
	public void deleteMin() {
		if (isEmpty()) {
			return;
		}
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x) {
		if (x.left == null) {
			// x is the min
			return x.right;
		} else {
			x.left = deleteMin(x.left);
			x.N = size(x.left) + size(x.right) + 1;
			x.height = Math.max(height(x.left), height(x.right)) + 1;
			return x;
		}
	}
	
	public void deleteMax() {
		if (isEmpty()) {
			return;
		}
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node x) {
		if (x.right == null) {
			// x is the max
			return x.left;
		} else {
			x.right = deleteMax(x.right);
			x.N = size(x.left) + size(x.right) + 1;
			x.height = Math.max(height(x.left), height(x.right)) + 1;
			return x;
		}
	}

	public void delete(Key key) {
		root = delete(root, key);
	}
	
	private Node delete(Node x, Key key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = delete(x.left, key);
		} else if (cmp > 0) {
			x.right = delete(x.right, key);
		} else {
			// delete node x:
			// case 1: if x has no more than one child,
			// simply cut off x
			if (x.right == null) {
				return x.left;
			}
			if (x.left == null) {
				return x.right;
			}
			// case 2: if x has two children,
			// replace it with it's successor
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		return x;
	}
	
	public Key floor(Key key) {
		Node x = floor(root, key);
		if (x == null) {
			return null;
		} else {
			return x.key;
		}
	}
	
	private Node floor(Node x, Key key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp == 0) {
			return x;
		} else if (cmp < 0) {
			return floor(x.left, key);
		} else {
			Node t = floor(x.right, key);
			if (t == null) {
				return x;
			} else {
				return t;
			}
		}
	}
	
	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if (x == null) {
			return null;
		} else {
			return x.key;
		}
	}
	
	private Node ceiling(Node x, Key key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp == 0) {
			return x;
		} else if (cmp > 0) {
			return ceiling(x.right, key);
		} else {
			Node t = ceiling(x.left, key);
			if (t == null) {
				return x;
			} else {
				return t;
			}
		}
	}
	
	public Key select(int k) {
		if (k < 0 || k >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Node x = select(root, k);
		return x.key;
	}
	
	private Node select(Node x, int k) {
		if (x == null) {
			return null;
		}
		int t = size(x.left);
		if (t > k) {
			return select(x.left, k);
		} else if (t < k) {
			return select(x.right, k-t-1);
		} else {
			return x;
		}
	}
	
	// number of keys less than the given key
	public int rank(Key key) {
		if (key == null) {
			throw new NullPointerException("rank of null key");
		}
		return rank(root, key);
	}
	
	private int rank(Node x, Key key) {
		if (x == null) {
			return 0;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return rank(x.left, key);
		} else if (cmp > 0) {
			return 1 + size(x.left) + rank(x.right, key);
		} else {
			return size(x.left);
		}
	}
	
	public Iterable<Key> keys() {
		return keys(min(), max());
	}
	
	public Iterable<Key> keys(Key low, Key high) {
		LinkedList<Key> list = new LinkedList<Key>();
		keys(root, list, low, high);
		return list;
	}
	
	private void keys(Node x, LinkedList<Key> list, Key low, Key high) {
		if (x == null) {
			return;
		}
		int cmpLow = low.compareTo(x.key);
		int cmpHigh = high.compareTo(x.key);
		if (cmpLow < 0) {
			keys(x.left, list, low, high);
		}
		if (cmpLow <= 0 && cmpHigh >= 0) {
			list.add(x.key);
		}
		if (cmpHigh > 0) {
			keys(x.right, list, low, high);
		}
	}
	
	public void printPreorder() {
		for (Node x : preorder()) {
			System.out.println(x.key + ", N = " + x.N);
		}
//		System.out.println();
	}
	
	private Iterable<Node> preorder() {
		LinkedList<Node> order = new LinkedList<Node>();
		preorder(root, order);
		return order;
	}
	
	private void preorder(Node x, LinkedList<Node> order) {
		if (x == null) {
			return;
		}
		order.add(x);
		preorder(x.left, order);
		preorder(x.right, order);
	}
	
	private Iterable<Node> inorder() {
		LinkedList<Node> order = new LinkedList<Node>();
		inorder(root, order);
		return order;
	}
	
	private void inorder(Node x, LinkedList<Node> order) {
		if (x == null) {
			return;
		}
		inorder(x.left, order);
		order.add(x);
		inorder(x.right, order);
	}
	
	public void print() {
		for (Node t: inorder()) {
			System.out.print(t + " ");
		}
		System.out.println();
	}
	
	// level-order traversal
	public void printLevel() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node x = queue.remove();
			if (x != null) {
				System.out.print(x + " ");
				queue.add(x.left);
				queue.add(x.right);
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
