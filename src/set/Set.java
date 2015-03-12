package set;

public interface Set<Key, Value> {
	
	boolean isEmpty();
	int size();
	
	boolean contains(Key key);
	
	void insert(Key key);
	Key remove(Key key);
	
	Iterable<Key> keys();

	void print();
}
