package digraph;

import stack.*;
import stdlib.In;

public class DiCycle {
	
	private int[] color;
	private int[] edgeTo;
	private Stack<Integer> cycle;

	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;
	
	public DiCycle(Digraph G) {
		color = new int[G.V()];
		for (int i = 0; i < G.V(); i++) {
			color[i] = WHITE;
		}
		edgeTo = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (color[v] == WHITE) {
				dfs(G, v);
			}
		}
	}
	
	private void dfs(Digraph G, int v) {
		color[v] = GRAY;
		for (int w : G.adj(v)) {
			if (this.hasCycle()) {
				return;
			}
			if (color[w] == WHITE) {
				edgeTo[w] = v;
				dfs(G, w);
			} else if (color[w] == GRAY) {
				cycle = new LinkedStack<Integer>();
				for (int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		color[v] = BLACK;
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}
	
	public Iterable<Integer> cycle() {
		return cycle;
	}

	public static void main(String[] args) {
		String filename = "D:\\Java-Projects\\Algorithm\\input\\tinyDG.txt";
		Digraph G = new Digraph(new In(filename));
		
		DiCycle dc = new DiCycle(G);
		if (dc.hasCycle()) {
			System.out.print("has cycle:");
			for (int v : dc.cycle()) {
				System.out.print(" " + v);
			}
			System.out.println();
		}
		
	}

}
