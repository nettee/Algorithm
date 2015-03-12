package symbolTable;

public class AvlTree<Key extends Comparable<Key>, Value> extends BST<Key, Value> {
	
	public AvlTree() {
		root = null;
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
		if (x == null) {
			return new Node(key, val, 1);
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key, val);
		} else if (cmp > 0) {
			x.right = put(x.right, key, val);
		} else {
			x.val = val;
		}
		return balance(x);
	}
	
	private static final int ALLOWED_IMBALANCE = 1;
	
	private Node balance(Node x) {
		if (x == null) {
			return null;
		}
		if (height(x.left) - height(x.right) > ALLOWED_IMBALANCE) {
			// x.left can never be null
			if (height(x.left.left) >= height(x.left.right)) {
				x = singleRotateLeft(x);
			} else {
				x = doubleRotateLeft(x);
			}
		} else if (height(x.right) - height(x.left) > ALLOWED_IMBALANCE) {
			if (height(x.right.right) >= height(x.right.left)) {
				x = singleRotateRight(x);
			} else {
				x = doubleRotateRight(x);
			}
		}
		x.N = size(x.left) + size(x.right) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		return x;
	}
	
	private Node singleRotateLeft(Node x) {
		Node t = x.left;
		x.left = t.right;
		t.right = x;
		x.N = size(x.left) + size(x.right) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		t.N = size(t.left) + size(t.right) + 1;
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}
	
	private Node singleRotateRight(Node x) {
		Node t = x.right;
		x.right = t.left;
		t.left = x;
		x.N = size(x.left) + size(x.right) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		t.N = size(t.left) + size(t.right) + 1;
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}
	
	private Node doubleRotateLeft(Node x) {
		x.left = singleRotateRight(x.left);
		return singleRotateLeft(x);
	}
	
	private Node doubleRotateRight(Node x) {
		x.right = singleRotateLeft(x.right);
		return singleRotateRight(x);
	}
	
	public void deleteMin() {
		if (root == null) {
			throw new NullPointerException();
		}
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x) {
		if (x.left == null) {
			return x.right;
		} else {
			x.left = deleteMin(x.left);
			x.N = size(x.left) + size(x.right) + 1;
			return balance(x);
		}
	}
	
	public void deleteMax() {
		if (root == null) {
			throw new NullPointerException();
		}
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node x) {
		if (x.right == null) {
			return x.left;
		} else {
			x.right = deleteMax(x.right);
			x.N = size(x.left) + size(x.right) + 1;
			return balance(x);
		}
	}
	
	public void delete(Key key) {
		root = delete(root, key);
	}
	
	private Node delete(Node x, Key key) {
		if (x == null) {
			// base case: key not found
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = delete(x.left, key);
		} else if (cmp > 0) {
			x.right = delete(x.right, key);
		} else {
			if (x.left == null) {
				x = x.right;
			} else if (x.right == null) {
				x = x.left;
			} else {
				// replace t with t's successor
				Node t = x;
				x = min(t.right);
				x.right = deleteMin(t.right);
				x.left = t.left;
			}
		}
		x.N = size(x.left) + size(x.right) + 1;
		return balance(x);
	}
}
