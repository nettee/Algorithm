package heap;

public class TestBinaryHeap {

	public static void main(String[] args) {
		BinaryHeap<Integer> h = new BinaryHeap();
		Integer[] arr = {13, 21, 16, 24, 31, 19, 68, 65, 26, 32};
		h.fromArray(arr);
		h.insert(14);
		h.print();
		h.remove();
		h.print();
		h.remove();
		h.print();
		
//		for (int i = 0; i < 10; i++) {
//			h.insert(i);
//		}
//		h.print();
	}

}
