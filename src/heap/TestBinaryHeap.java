package heap;

public class TestBinaryHeap {

	public static void main(String[] args) {
		Integer[] arr = {13, 21, 16, 24, 31, 19, 68, 65, 26, 32};
		BinaryHeap<Integer> h = new BinaryHeap<Integer>(arr);
//		BinaryHeap<Integer> h = new BinaryHeap();
//		h.fromArray(arr);
		h.insert(14);
		h.print();
		h.remove();
		h.print();
		h.remove();
		h.print();
		
		BinaryHeap<Integer> m = new BinaryHeap<Integer>();
		Integer[] a = {1,5,8,4,3,9,2,7};
		m.fromArray(a);
//		for (int i = 10; i > 0; i--) {
//			m.insert(i);
//		}
		m.print();
	}

}
