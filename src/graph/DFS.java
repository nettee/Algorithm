package graph;

public class DFS {
	private int[] color;
	private int count;
	
	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;
	
	public DFS(Graph G, int s) {
		color = new int[G.V()];
		for (int i = 0; i < G.V(); i++) {
			color[i] = WHITE;
		}
		dfs(G, s);
	}
	
	private void dfs(Graph G, int v) {
		color[v] = GRAY;
		count++;
		for (int w : G.adj(v)) {
			if (color[w] == WHITE) {
				dfs(G, w);
			}
		}
		color[v] = BLACK;
	}
	
	public boolean marked(int w) {
		return color[w] != WHITE;
	}
	
	public int count() {
		return count;
	}
}
