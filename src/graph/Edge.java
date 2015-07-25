package graph;

public class Edge {
	private final int v;
	private final int w;
	
	public Edge(int v, int w) {
		this.v = v;
		this.w = w;
	}
	
	public int either() {
		return v;
	}
	
	public int other(int vertex) {
		if (vertex == v) {
			return w;
		} else if (vertex == w) {
			return v;
		} else {
			throw new RuntimeException("Inconsistent edge");
		}
	}
	
	public boolean equals(Edge that) {
		return (this.v == that.v && this.w == that.w)
				|| (this.v == that.w && this.w == that.v);
	}
	
	public String toString() {
		return String.format("%d-%d", v, w);
	}
}
