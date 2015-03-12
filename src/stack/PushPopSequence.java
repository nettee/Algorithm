package stack;

import stdlib.*;

public class PushPopSequence {
	
	/** given push-in sequence, check whether the output sequence can occur
	 * in some case of intermixed push/pop order
	 * 
	 * @param in: the input sequence, out: the output sequence
	 * @return true if can occur, false otherwise
	 */
	private static boolean checkPushPopSequence(int[] in, int[] out) {
		if (in.length != out.length) {
			throw new IllegalArgumentException("input and output array of different length");
		}
		int N = out.length;
		
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		int i = 0;  // out[i]: next to check item
		int j = 0;  // in[j]: next integer to push
		
		
		// invariant: i items are already checked
		while (i < N) {
			if (!stack.isEmpty() && stack.top() == out[i]) {
//				StdOut.printf("check out[%d]=%d\n", i, out[i]);
				stack.pop();
				i++;
			} else if (j == N) {  // no more to push
//				StdOut.println("return bad");
				return false;
			} else {
//				StdOut.println("push " + j);
				stack.push(in[j++]);
			}
		}
		// exit loop normally only if all items are checked
		return true;
	}
	
	/** given push-in sequence, check whether the output sequence can occur
	 * in some case of intermixed push/pop order
	 * assume the input sequence is [0..N-1], where N = length of output sequence
	 * 
	 * @param out: the output sequence
	 * @return true if can occur, false otherwise
	 */
	private static boolean checkPushPopSequence(int[] out) {
		int N = out.length;
		int[] in = new int[N];
		for (int i = 0; i < N; i++) {
			in[i] = i;
		}
		return checkPushPopSequence(in, out);
	}
	
	public static void main(String[] args) {
		int length = StdIn.readInt();
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			arr[i] = StdIn.readInt();
		}
		boolean good = checkPushPopSequence(arr);
		if (good) { 
			StdOut.println("good sequence.");
		} else {
			StdOut.println("bad sequence.");
		}
	}
}
