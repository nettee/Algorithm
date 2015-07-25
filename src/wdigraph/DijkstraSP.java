package wdigraph;

import stack.LinkedStack;
import stack.Stack;
import stdlib.In;
import stdlib.IndexMinPQ;

public class DijkstraSP {
	
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	
	/**
	 * find shortest path in edge weighted directed graph
	 * @param G the edge weighted directed graph
	 * @param s the source vertex
	 */
	public DijkstraSP(EdgeWeightedDigraph G, int s) {
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		
		for (int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;	
		}
		distTo[s] = 0.0;

		pq.insert(s, 0.0);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			relax(G, v);
		}
	}
	
	private void relax(EdgeWeightedDigraph G, int v) {
		for (DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if (distTo[v] + e.weight() < distTo[w]) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (pq.contains(w)) {
					pq.changeKey(w, distTo[w]);
				} else {
					pq.insert(w, distTo[w]);
				}
			}
		}
	}
	
	public double distTo(int v) {
		return distTo[v];
	}
	
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	public Iterable<DirectedEdge> pathTo(int v) {
		if (!hasPathTo(v)) {
			return null;
		}
		Stack<DirectedEdge> path = new LinkedStack<DirectedEdge>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}

	public static void main(String[] args) {
		String filename = "";
		int s = 0;
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(filename));
		DijkstraSP sp = new DijkstraSP(G, s);
		
		for (int t = 0; t < G.V(); t++) {
			System.out.print(s + " to " + t);
			System.out.printf(" (%4.2f): ", sp.distTo(t));
			if (sp.hasPathTo(t)) {
				for (DirectedEdge e : sp.pathTo(t)) {
					System.out.print(e + "   ");
				}
			}
			System.out.println();
		}
	}

}
