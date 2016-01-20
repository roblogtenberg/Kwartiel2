package opdracht4;

import java.util.HashMap;
import java.util.Map;

public class Vertex {

	private String name;
	private Map<Vertex, Integer> edges;
	
	public Vertex(String name) {
		this.name = name;
		edges = new HashMap<>();
	}
	
	public void addEdge(Vertex vertex, int weight) {
		edges.put(vertex, weight);
	}

	public int getEdgeWeight(Vertex end) {
		return edges.get(end);
	}
	
	public Map<Vertex, Integer> getEdges() {
		return edges;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
