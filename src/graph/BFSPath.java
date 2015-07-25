package graph;

import queue.LinkedQueue;
import queue.Queue;
import stack.LinkedStack;
import stack.Stack;

public class BFSPath {
	private int[] color;
	private int[] edgeTo;
	private final int s;

	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;
	
	public BFSPath(Graph G, int s) {
		color = new int[G.V()];
		for (int i = 0; i < G.V(); i++) {
			color[i] = WHITE;
		}
		edgeTo = new int[G.V()];
		this.s = s;
		bfs(G, s);
	}

	private void bfs(Graph G, int s) {
		Queue<Integer> queue = new LinkedQueue<Integer>();
		color[s] = GRAY;
		queue.enqueue(s);
		
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			for (int w : G.adj(v)) {
				if (color[w] == WHITE) {
					color[w] = GRAY;
					edgeTo[w] = v;
					queue.enqueue(w);
				}
			}
			color[v] = BLACK;
		}
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
