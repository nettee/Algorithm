package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

import stdlib.In;

public class TarjanBCC {

	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;
	
	private int[] color;
	private int[] discoverTime;
	private int[] back;
	private int count = 0;
	private Stack<Edge> edgeStack = new Stack<Edge>();
	private List<Integer> articulations = new LinkedList<Integer>();
	private List<TreeSet<Integer>> components = new LinkedList<TreeSet<Integer>>();
	
	public TarjanBCC(Graph G) {
		color = new int[G.V()];
		discoverTime = new int[G.V()];
		back = new int[G.V()];
		for (int s = 0; s < G.V(); s++) {
			if (color[s] == WHITE) {
				dfs(G, s, -1);
			}
		}
	}
	
	private void dfs(Graph G, int v, int parent) {
		color[v] = GRAY;
		discoverTime[v] = count;
		back[v] = discoverTime[v];
		count++;
		
		int numChild = 0;
		
		for (int w : G.adj(v)) {
			
			if (color[w] == WHITE) {
				
				numChild++;
				
				// (v,w) is tree edge
				edgeStack.push(new Edge(v, w));
				
				dfs(G, w, v);
				back[v] = Math.min(back[v], back[w]);
				
				/* case 1:
				 * v is root of DFS tree, and has more than one subtrees,
				 * then v is articulation vertex
				 */
				if ((parent == -1 && numChild > 1)
				/* case 2:
				 * v is not root,
				 * if v's child w cannot reach vertex above v
				 * unless through v, then v is articulation vertex
				 */
						|| (parent != -1 && back[w] >= discoverTime[v])) {
					
					articulations.add(v);
					
					TreeSet<Integer> component = new TreeSet<Integer>(); 
					Edge e;
					Edge now = new Edge(v, w);
					do {
						e = edgeStack.pop();
						int vi = e.either(); 
						int wi = e.other(vi);
						System.out.println(String.format("pop edge (%d, %d)", vi, wi));
						component.add(vi);
						component.add(wi);
					} while (!e.equals(now));
					components.add(component);
				}
				
			} else if (color[w] == GRAY && w != parent) {
				// (w,v) is is back edge
				back[v] = Math.min(back[v], discoverTime[w]);
			}
		}
		
		if (parent == -1) {
			TreeSet<Integer> component = new TreeSet<Integer>();
			while (!edgeStack.isEmpty()) {
				Edge e = edgeStack.pop();
				int vi = e.either();
				int wi = e.other(vi);
				component.add(vi);
				component.add(wi);
			}
			components.add(component);
		}
		
		color[v] = BLACK;
	}
	
	public boolean isBiconnected() {
		return articulations == null;
	}
	
	public Iterable<Integer> articulations() {
		return articulations;
	}
	
	public Iterable<TreeSet<Integer>> components() {
		return components;
	}
	

	public static void main(String[] args) {
		String filename = "D:\\Java-Projects\\Algorithm\\input\\tinyBCC.txt";
		Graph G = new Graph(new In(filename));
		
		TarjanBCC bcc = new TarjanBCC(G);
		
		if (bcc.isBiconnected()) {
			System.out.println("is bcc");
		} else {
			System.out.print("Articulation vertices: ");
			for (int v : bcc.articulations()) {
				System.out.print(v + " ");
			}
			System.out.println();
			System.out.println("components:");
			for (TreeSet<Integer> component : bcc.components()) {
				for (int v : component) {
					System.out.print(v + " ");
				}
				System.out.println();
			}
		}
	}
}
