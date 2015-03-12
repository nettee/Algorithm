package bag;

public class TestBag {

	public static void main(String[] args) {
		Bag<String> b = new Bag<String>();
		for (int i = 0; i < 40; i++) {
			b.add("text" + i);
		}
		for (String s : b) {
			System.out.println(s);
		}

	}

}
