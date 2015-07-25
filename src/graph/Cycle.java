package graph;

import stack.*;
import stdlib.In;

public class Cycle {
	
	private int[] color;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	
	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;
	
	public Cycle(Graph G) {
		color = new int[G.V()];
		for (int i = 0; i < G.V(); i++) {
			color[i] = WHITE;
		}
		edgeTo = new int[G.V()];
		for (int s = 0; s < G.V(); s++) {
			if (color[s] == WHITE) {
				dfs(G, s, -1);
			}
		}
	}
	
	private void dfs(Graph G, int v, int parent) {
		color[v] = GRAY;
		for (int w : G.adj(v)) {
			if (this.hasCycle()) {
				return;
			}
			if (color[w] == WHITE) {
				edgeTo[w] = v;
				dfs(G, w, v);
			} else if (color[w] == GRAY) {
				if (w != parent) {
					cycle = new LinkedStack<Integer>();
					for (int x = v; x != w; x = edgeTo[x]) {
						cycle.push(x);
					}
					cycle.push(w);
					cycle.push(v);
				}
			} else {
				throw new IllegalStateException();
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
		String filename = "D:\\Java-Projects\\Algorithm\\input\\tinyG.txt";
		Graph G = new Graph(new In(filename));
		
		Cycle cycle = new Cycle(G);
		if (cycle.hasCycle()) {
			System.out.print("has cycle:");
			for (int v : cycle.cycle()) {
				System.out.print(" " + v);
			}
			System.out.println();
		}
	}

}
