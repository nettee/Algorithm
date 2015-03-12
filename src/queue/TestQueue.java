package queue;

public class TestQueue {

	public static void main(String[] args) {
		LinkedQueue<String> q = new LinkedQueue<String>();
		for (int i = 0; i < 40; i++) {
			q.enqueue("text" + i);
		}
		for (int i = 0; i < 40; i++) {
			System.out.println(q.dequeue());
		}

	}

}
