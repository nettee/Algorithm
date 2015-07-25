package graph;

import stdlib.In;

public class TestPaths {

	public static void main(String[] args) {
//		Graph G = new Graph(new In(args[0]));
		Graph G = new Graph(new In("D:\\Java-Projects\\Algorithm\\input\\tinyG.txt"));
//		int s = Integer.parseInt(args[1]);
		int s = 1;
		DFSPath search = new DFSPath(G, s);
//		BFSPath search = new BFSPath(G, s);
		
		for (int v = 0; v < G.V(); v++) {
			System.out.print(s + " to " + v + ": ");
			if (search.hasPathTo(v)) {
				for (int x : search.pathTo(v)) {
					if (x == s) {
						System.out.print(x); 
					} else {
						System.out.print("-" + x);
					}
				}
			} else {
				System.out.print("(not reachable)");
			}
			System.out.println();
		}
	}

}
