package graph;

import stack.LinkedStack;
import stack.Stack;

public class DFSPath {
	private int[] color;
	private int[] edgeTo;
	private final int s;
	
	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;
	
	public DFSPath(Graph G, int s) {
		color = new int[G.V()];
		for (int i = 0; i < G.V(); i++) {
			color[i] = WHITE;
		}
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s);
	}
	
	private void dfs(Graph G, int v) {
		color[v] = GRAY;
		for (int w : G.adj(v)) {
			if (color[w] == WHITE) {
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
		color[v] = BLACK;
	}
	
	public boolean hasPathTo(int v) {
		return color[v] != WHITE;
	}
	
	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) {
			return null;
		}
		Stack<Integer> path = new LinkedStack<Integer>();
		for (int x = v; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		return path;
	}
}
