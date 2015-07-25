package digraph;

import stdlib.In;
import list.Bag;

public class DiDFS {

	private boolean[] marked;
	
	public DiDFS(Digraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	
	public DiDFS(Digraph G, Iterable<Integer> sources) {
		marked = new boolean[G.V()];
		for (int s : sources) {
			if (!marked[s]) {
				dfs(G, s);
			}
		}
	}
	
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}
	
	public boolean marked(int v) {
		return marked[v];
	}
	
	public static void main(String[] args) {
		String filename = "D:\\Java-Projects\\Algorithm\\input\\tinyDG.txt";
		Digraph G = new Digraph(new In(filename));
//		System.out.println(G);
		Bag<Integer> sources = new Bag<Integer>();
		sources.add(0);
		DiDFS reachable = new DiDFS(G, sources);
		
		for (int v = 0; v < G.V(); v++) {
			if (reachable.marked(v)) {
				System.out.print(v + " ");
			}
		}
		System.out.println();
	}

}
