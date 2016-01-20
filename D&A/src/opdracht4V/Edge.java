package opdracht4V;

public class Edge<Vertex,Data> {
	
	private Vertex fromVertex;
	private Vertex toVertex;
	private Data data;

	public Edge(Vertex fromVertex, Vertex toVertex, Data data) {
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
		this.data = data;
	}
	
	public Vertex getFromVertex() {
		return fromVertex;
	}
	
	public Vertex getToVertex() {
		return toVertex;
	}
	
	public Data getData() {
		return data;
	}
}
