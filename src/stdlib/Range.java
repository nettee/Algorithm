package stdlib;

import java.util.LinkedList;

public class Range {
	
	public static Iterable<Integer> range(int end) {
		return range(0, end);
	}
	
	public static Iterable<Integer> range(int begin, int end) {
		return range(begin, end, 1);
	}
	
	public static Iterable<Integer> range(int begin, int end, int step) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = begin; i < end; i += step) {
			list.add(i);
		}
		return list;
	}
	
}
