package graph;

public class Bipartite {
	private boolean[] marked;
	private boolean[] state;
	private boolean isBipartite = true;
	
	public Bipartite(Graph G) {
		marked = new boolean[G.V()];
		state = new boolean[G.V()];
		for (int i = 0; i < G.V(); i++) {
			marked[i] = false;
			state[i] = false;
		}
		for (int s = 0; s < G.V(); s++) {
			if (!marked[s]) {
				dfs(G, s);
			}
		}
	}
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (marked[w]) {
				if (state[w] == state[v]) {
					isBipartite = false;
				}
			} else {
				state[w] = !state[v];
			}
		}
	}
	
	public boolean isBipartite() {
		return isBipartite;
	}
}
