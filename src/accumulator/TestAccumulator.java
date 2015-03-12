package accumulator;

public class TestAccumulator {

	public static void main(String[] args) {
		Accumulator a = new Accumulator();
		a.addDataValue(0.1);
		a.addDataValue(0.4);
		a.addDataValue(0.3);
		a.addDataValue(0.6);
		a.addDataValue(0.9);
		a.addDataValue(0.2);
		System.out.println(a);
	}

}
