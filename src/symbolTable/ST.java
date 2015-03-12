package symbolTable;

public interface ST<Key extends Comparable<Key>, Value> {
	
	boolean isEmpty();
	int size();
	
	boolean contains(Key key);
	Value get(Key key);
	void put(Key key, Value val);

	Key min();
	Key max();
	
	void deleteMin();
	void deleteMax();
	void delete(Key key);
	
	Key floor(Key key);
	Key ceiling(Key key);
	
	Key select(int k);
	int rank(Key key);
	
	Iterable<Key> keys();
	
	void print();
}
