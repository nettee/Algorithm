package problem;

import java.util.Arrays;

import stdlib.BinarySearch;
import stdlib.StdOut;

public class TwoSum {
	
	/*
	 * Given an array of integers, find two numbers such that 
	 * they add up to a specific target number.
	 * The function twoSum should return indices of the two numbers
	 * such that they add up to the target, where index1 must be less than index2. 
	 * You may assume that each input would have exactly one solution.
	 * Input: numbers={2, 7, 11, 15}, target=9
	 * Output: {0, 1}
	 */
	
	public static int[] twoSum(int[] numbers, int target) {
		Arrays.sort(numbers);
		for (int i = 0; i < numbers.length; i++) {
			int expected = target - numbers[i];
			int j = BinarySearch.search(numbers, expected);
			if (j != -1 && i < j) {
				int[] result = new int[2];
				result[0] = i;
				result[1] = j;
				return result;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {2, 7, 11, 15};
		int target = 18;
		int[] index = twoSum(a, target);
		StdOut.println(String.format("a[%d] + a[%d] = %d", index[0], index[1], target));
	}

}
