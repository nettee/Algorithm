package wgraph;

import queue.LinkedQueue;
import queue.Queue;
import stdlib.In;
import stdlib.MinPQ;
import stdlib.UF;

public class KruskalMST {
	
	private Queue<Edge> mst;
	
	public KruskalMST(EdgeWeightedGraph G) {
		mst = new LinkedQueue<Edge>();
		MinPQ<Edge> pq = new MinPQ<Edge>(G.E());
		for (Edge e : G.edges()) {
			pq.insert(e);
		}
		UF uf = new UF(G.V());
		
		while (!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (uf.connected(v, w)) {
				continue;
			}
			uf.union(v, w);
			mst.enqueue(e);
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
		
		KruskalMST mst = new KruskalMST(G);
		for (Edge e : mst.edges()) {
			System.out.println(e);
		}
		System.out.println("total weight: " + mst.weight());
	}


}
