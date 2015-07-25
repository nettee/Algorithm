package wgraph;

import stdlib.In;
import list.Bag;

public class EdgeWeightedGraph {
	private final int V;
	private int E;
	private Bag<Edge>[] adj;
	
	public EdgeWeightedGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Edge>();
		}
	}
	
	public EdgeWeightedGraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			addEdge(new Edge(v, w, weight));
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	public Iterable<Edge> adj(int v) {
		return adj[v];
	}
	
	public Iterable<Edge> edges() {
		Bag<Edge> bag = new Bag<Edge>();
		for (int v = 0; v < V; v++) {
			for (Edge e : adj[v]) {
				if (e.other(v) > v) {
					bag.add(e);
				}
			}
		}
		return bag;
	}
}
