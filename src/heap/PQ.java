package heap;

public interface PQ<Key extends Comparable<Key>> {

	boolean isEmpty();
	int size();
	
	void insert(Key key);
	Key remove();
	Key top();
}
