package opdracht4V;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graaf<Vertex, Data> {

	private ArrayList<Vertex> Vertices;
	private ArrayList<Edge<Vertex, Data>> edges;

	public Graaf() {
		Vertices = new ArrayList<>();
		edges = new ArrayList<>();
	}

	/**
	 * voeg een vertex toe
	 * 
	 * @param vertex
	 */
	public void putVertex(Vertex vertex) {
		assert vertex != null;
		
		Vertices.add(vertex);
	}

	/**
	 * voeg een edge toe (wordt niet gecontroleerd of de vertexen zich wel
	 * bevinden in de graaf)
	 * 
	 * @param from
	 * @param to
	 * @param data
	 */
	public void putEdge(Vertex from, Vertex to, Data data) {
		assert from != null;
		assert to != null;
		
		Edge<Vertex, Data> edge = new Edge<>(from, to, data);
		edges.add(edge);
	}

	/**
	 * verwijder een edge
	 * 
	 * @param edge
	 */
	public void removeEdge(Vertex from, Vertex to) {
		assert from != null;
		assert to != null;
		
		Edge tbrm = null;
		for (Edge<Vertex, Data> edge : edges) {
			if (edge.getFromVertex().equals(from)
					&& edge.getToVertex().equals(to)) {
				tbrm = edge;
			}
		}
		edges.remove(tbrm);
	}

	/**
	 * verwijder een vertex
	 * 
	 * @param vertex
	 */
	public void removeVertex(Vertex vertex) {
		Vertices.remove(vertex);
	}

	/**
	 * haal data op van een edge
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public Data getWeight(Vertex from, Vertex to) {
		assert from != null;
		assert to != null;
		
		for (Edge<Vertex, Data> edge : edges) {
			if (edge.getFromVertex().equals(from)
					&& edge.getToVertex().equals(to)) {
				return edge.getData();
			}
		}
		return null;
	}

	/**
	 * haal alle vertices op die naar deze vertex wijzen
	 * 
	 * @param vertex
	 * @return
	 */
	public List<Vertex> getIncomingVertices(Vertex vertex) {
		assert vertex != null;
		
		ArrayList<Vertex> vertices = new ArrayList<>();

		for (Edge<Vertex, Data> edge : edges) {
			if (edge.getToVertex().equals(vertex)) {
				vertices.add(edge.getFromVertex());
			}
		}

		return vertices;
	}

	/**
	 * 
	 * @param vertex
	 * @return alle vertices waar deze vertex naar wijst
	 */
	public List<Vertex> getNeighbours(Vertex vertex) {
		assert vertex != null;

		ArrayList<Vertex> vertices = new ArrayList<>();

		for (Edge<Vertex, Data> edge : edges) {
			if (edge.getFromVertex().equals(vertex)) {
				vertices.add(edge.getToVertex());
			}
		}

		return vertices;
	}

	/**
	 * 
	 * @return alle vertices
	 */
	public List<Vertex> getVertices() {
		return Vertices;
	}

	/**
	 * 
	 * @return alle vertexen in een topologische volgorde
	 */
	public List<Vertex> getTopologicalOrder() {
		ArrayList<Vertex> inOrder = new ArrayList<>();
		Queue<Vertex> queue = new LinkedList<>();

		// alle start vertices op zoeken
		for (Vertex v : Vertices) {
			if (getIncomingVertices(v).size() == 0) {
				queue.add(v);
			}
		}

		// zolang er nog items zijn
		while (!queue.isEmpty()) {
			Vertex v = queue.poll();

			// ze toevoegen aan de lijst
			inOrder.add(v);

			// controleren voor al zijn neighbours of ze nu vrij zijn en dan
			// toevoegen aan de queue
			for (Vertex ver : getNeighbours(v)) {
				if (inOrder.containsAll(getIncomingVertices(ver))) {
					queue.add(ver);
				}
			}
		}

		return inOrder;
	}
}
