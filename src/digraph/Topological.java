package digraph;

public class Topological {
	
	private Iterable<Integer> order;  // topological order
	
	public Topological(Digraph G) {
		DiCycle cycleFinder = new DiCycle(G);
		if (!cycleFinder.hasCycle()) {
			DFSOrder dfs = new DFSOrder(G);
			order = dfs.reversePost();
		}
	}
	
	public Iterable<Integer> order() {
		return order;
	}
	
	public boolean isDAG() {
		return order != null;
	}
	
}
