package wgraph;

import queue.LinkedQueue;
import queue.Queue;
import stdlib.In;
import stdlib.MinPQ;

public class LazyPrimMST {
	
	private boolean[] marked;
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;
	
	public LazyPrimMST(EdgeWeightedGraph G) {
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		mst = new LinkedQueue<Edge>();
		
		// assume G is connected
		visit(G, 0);
		while (!pq.isEmpty()) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			// skip if ineligible
			if (marked[v] && marked[w]) {
				continue;
			}
			// add edge to tree
			mst.enqueue(e);
			// add vertex to tree
			if (!marked[v]) {
				visit(G, v);
			}
			if (!marked[w]) {
				visit(G, w);
			}
		}
	}
	
	/**
	 * mark v and add to pq all edges from v to unmarked vertices
	 */
	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for (Edge e : G.adj(v)) {
			if (!marked[e.other(v)]) {
				pq.insert(e);
			}
		}
	}
	
	/**
	 * 
	 * @return all of the MST edges
	 */
	public Iterable<Edge> edges() {
		return mst;
	}
	
	/**
	 * 
	 * @return weight of MST
	 */
	public double weight() {
		double w = 0.0;
		for (Edge e : edges()) {
			w += e.weight();
		}
		return w;
	}

	public static void main(String[] args) {
		String filename = "D:\\Java-Projects\\Algorithm\\input\\tinyEWG.txt";
		In in = new In(filename);
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		
		LazyPrimMST mst = new LazyPrimMST(G);
		for (Edge e : mst.edges()) {
			System.out.println(e);
		}
		System.out.println("total weight: " + mst.weight());
	}

}
