package stack;

import stdlib.*;

public class TestStack {

	public static void main(String[] args) {
		LinkedStack<String> stack = new LinkedStack<String>();
		for (int i = 0; i < 10; i++) {
			stack.push("text" + i);
		}
		
		while (!stack.isEmpty()) {
			StdOut.println(stack.pop() + " ");
		}

	}

}
