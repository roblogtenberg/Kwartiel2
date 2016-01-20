package opdracht4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericGraph {

	private List<Vertex> vertexes;
	
	public GenericGraph() {
		vertexes = new ArrayList<>();
	}
	
	public void addVertex(Vertex vertex) {
		vertexes.add(vertex);
	}
	
	public Vertex getVertex(String name) {
		for(Vertex vertex : vertexes) {
			if(vertex.getName().equalsIgnoreCase(name)) {
				return vertex;
			}
		}
		return null;
	}
	
	public void connect(Vertex begin, Vertex end, int weight) {
		if(vertexes.contains(begin) && vertexes.contains(end)) {
			for(int i = 0; i < vertexes.size(); i++) {
				if(vertexes.get(i).getName().equalsIgnoreCase(begin.getName())) {
					vertexes.get(i).addEdge(end, weight);
					return; //we don't need to loop through the rest of the list;
				}
			}
		}
		System.err.println("One of the Vertexes does not exist");
	}

	public List<Vertex> getStartingVertexes() {
		List<Vertex> startingVertexes = new ArrayList<>();
		startingVertexes.addAll(vertexes);
		
		for(int i = 0; i < vertexes.size(); i++) {
			Map<Vertex, Integer> edges = vertexes.get(i).getEdges();
			for(int j = 0; j < vertexes.size(); j++) {
				if(edges.containsKey(vertexes.get(j))) {
					startingVertexes.remove(vertexes.get(j));
				}
			}
		}
		return startingVertexes;
	}
}
