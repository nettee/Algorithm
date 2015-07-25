package graph;

import queue.LinkedQueue;
import queue.Queue;

public class BFS {
	private int[] color;
	private int count;

	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;
	
	public BFS(Graph G, int s) {
		color = new int[G.V()];
		for (int i = 0; i < G.V(); i++) {
			color[i] = WHITE;
		}
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
					queue.enqueue(w);
				}
			}
			count++;
			color[v] = BLACK;
		}
	}
	
	public boolean marked(int w) {
		return color[w] != WHITE;
	}
	
	public int count() {
		return count;
	}

}
