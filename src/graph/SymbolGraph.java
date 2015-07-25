package graph;

import stdlib.In;
import symbolTable.RedBlackTree;
import symbolTable.ST;

public class SymbolGraph {
	private ST<String, Integer> st;
	private String[] keys;
	private Graph G;
	
	public SymbolGraph(String filename, String delim) {
		st = new RedBlackTree<String, Integer>();
		In in = new In(filename);
		while (in.hasNextLine()) {
			String[] a = in.readLine().split(delim);
			for (int i = 0; i < a.length; i++) {
				if (!st.contains(a[i])) {
					st.put(a[i], st.size());
				}
			}
		}
		
		keys = new String[st.size()];
		for (String name : st.keys()) {
			keys[st.get(name)] = name;
		}
		
		G = new Graph(st.size());
		in = new In(filename);
		while (in.hasNextLine()) {
			String[] a = in.readLine().split(delim);
			int v = st.get(a[0]);
			for (int i = 1; i < a.length; i++) {
				G.addEdge(v, st.get(a[i]));
			}
		}
	}
	
	public boolean contains(String s) {
		return st.contains(s);
	}
	
	public int index(String s) {
		return st.get(s);
	}
	
	public String name(int v) {
		return keys[v];
	}
	
	public Graph G() {
		return G;
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
