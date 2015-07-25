package wdigraph;

import stdlib.In;
import list.Bag;

public class EdgeWeightedDigraph {

	private final int V;
	private int E;
	private Bag<DirectedEdge>[] adj;
	
	public EdgeWeightedDigraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<DirectedEdge>();
		}
	}
	
	public EdgeWeightedDigraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			addEdge(new DirectedEdge(v, w, weight));
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(DirectedEdge e) {
		int v = e.from();
		adj[v].add(e);
		E++;
	}
	
	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}
	
	public Iterable<DirectedEdge> edges() {
		Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
		for (int v = 0; v < V; v++) {
			for (DirectedEdge e : adj[v]) {
				bag.add(e);
			}
		}
		return bag;
	}
}
