package opdracht4;

import java.util.HashMap;
import java.util.Map;

public class Vertex {

	private String name;
	private Map<Vertex, Integer> edges;
	private int earliest, latest;
	
	public Vertex(String name) {
		this.name = name;
		edges = new HashMap<>();
	}
	
	public void addEdge(Vertex vertex, int weight) {
		edges.put(vertex, weight);
	}

	public void setEarliest(int earliest) {
		this.earliest = earliest;
	}
	
	public void setLatest(int latest) {
		this.latest = latest;
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
	
	public int getEarliest() {
		return earliest;
	}
	
	public int getLatest() {
		return latest;
	}
	
	@Override
	public String toString() {
		return name + " " + earliest + " " + latest;
	}
}
