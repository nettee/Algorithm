package graph;

import stdlib.In;
import list.Bag;

public class Graph {
	
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];  // adjacent list
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	
	public Graph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	 
	public String toString() {
		String a = V + " vertices, " + E + " edges";
		for (int v = 0; v < V; v++) {
			a += "\n";
			a += v + ":";
			for (int w : adj[v]) {
				a += " " + w;
			}
		}
		return a;
	}
	
	public static void main(String[] args) {
		In in = new In("D:\\Java-Projects\\Algorithm\\input\\tinyCG.txt");
		Graph graph = new Graph(in);
		System.out.println(graph);
	}
}
