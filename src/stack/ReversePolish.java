package stack;

import java.util.*;
import stdlib.StdIn;

public class ReversePolish {
	
	private static List<String> readTokens() {
		List<String> st = new LinkedList<String>();
		while (!StdIn.isEmpty()) {
			st.add(StdIn.readString());
		}
		return st;
	}
	
	private static double calculate(List<String> tokens) {
		Stack<Double> st = new LinkedStack<Double>();
		for (String s : tokens) {
			if (Character.isDigit(s.charAt(0))) {
				double d = Double.parseDouble(s);
				st.push(d);
			} else {
				double val2 = st.pop();
				double val1 = st.pop();
				if (s.equals("+")) {
					st.push(val1 + val2);
				} else if (s.equals("-")) {
					st.push(val1 - val2);
				} else if (s.equals("*")) {
					st.push(val1 * val2);
				} else if (s.equals("/")) {
					st.push(val1 / val2);
				} else {
					throw new IllegalStateException(s + " is illegal");
				}
			}
		}
		return st.top();
	}
	
	private static void printTokens(List<String> tokens) {
		System.out.print(tokens.size() + " tokens: ");
		for (String s : tokens) {
			System.out.print(s + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		List<String> tokens = readTokens();
		printTokens(tokens);
		System.out.println(calculate(tokens));
	}

}
