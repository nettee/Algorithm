package test;

import stdlib.StdOut;
import stdlib.StdRandom;

public class StdTest {
	
	public static double[] getRatioRandoms(int size) {
		double[] randoms = new double[size];
		for (int i = 0; i < size; i++) {
			randoms[i] = StdRandom.uniform();
		}
		return randoms;
	}
	
	public static int[] getDataRandoms(int size, int max) {
		int[] randoms = new int[size];
		for (int i = 0; i < size; i++) {
			randoms[i] = StdRandom.uniform(max);
		}
		return randoms;
	}
	
	public static int[] getDataRandoms(int size) {
		int[] randoms = new int[size];
		for (int i = 0; i < size; i++) {
			randoms[i] = StdRandom.uniform(100);
		}
		return randoms;
	}
	
	public static void printWrongResult(String methodName, int testResult, int reference) {
		StdOut.println(methodName + "() returns wrong result");
		StdOut.println("  - test object = " + testResult);
		StdOut.println("  - reference   = " + reference);
	}
	
	public static void printFailOrNot(boolean fail) {
		if (fail) {
			StdOut.println("==> FAILED");
		} else {
			StdOut.println("==> passed");
		}
	}
}
