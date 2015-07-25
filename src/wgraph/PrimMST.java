package wgraph;

import queue.LinkedQueue;
import queue.Queue;
import stdlib.In;
import stdlib.IndexMinPQ;

/**
 * eager version of prim's algorithm
 *
 */
public class PrimMST {
	
	private Edge[] edgeTo;  // shortest edge from tree vertex
	private double[] distTo;  // distTo[w] = edgeTo[w].weight()
	private boolean[] marked;  // if v is on tree
	private Queue<Edge> mst;
	private IndexMinPQ<Double> pq;  // eligible crossing edges
	
	public PrimMST(EdgeWeightedGraph G) {
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		mst = new LinkedQueue<Edge>(); 
		for (int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		pq = new IndexMinPQ<Double>(G.V());
		
		int s = 0;
		distTo[s] = 0.0;
		pq.insert(s, 0.0);
		while (!pq.isEmpty()) {
			// add closest vertex to tree
			int v = pq.delMin();
			if (v != s) {
				mst.enqueue(edgeTo[v]);
			}
			updateFringe(G, v);
		}
	}
	
	/**
	 * add v to tree, update data structures
	 */
	private void updateFringe(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for (Edge e : G.adj(v)) {
			int w = e.other(v);
			if (marked[w]) {  // v-w is ineligible
				continue;
			}
			if (e.weight() < distTo[w]) {
				// edge e is new best connection from tree to w
				edgeTo[w] = e;
				distTo[w] = e.weight();
				if (pq.contains(w)) {
					pq.changeKey(w, distTo[w]);
				} else {
					pq.insert(w, distTo[w]);
				}
			}
			
		}
	}
	
	public Iterable<Edge> edges() {
		return mst; 
	}
	
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
		
		PrimMST mst = new PrimMST(G);
		for (Edge e : mst.edges()) {
			System.out.println(e);
		}
		System.out.println("total weight: " + mst.weight());
	}

}
