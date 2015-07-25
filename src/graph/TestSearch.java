package graph;

import stdlib.In;

public class TestSearch {
	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
//		DFS search = new DFS(G, s);
		BFS search = new BFS(G, s);
		
		for (int v = 0; v < G.V(); v++) {
			if (search.marked(v)) {
				System.out.print(v + " ");
			}
		}
		System.out.println();
		
		if (search.count() != G.V()) {
			System.out.print("NOT ");
		}
		System.out.println("connected");
		
		System.out.println("count = " + search.count());
	}
}
