package opdracht4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PERTNetwork extends GenericGraph {

	public void earliestTimes() {
		List<Vertex> topologicalSort = getTopoLogicalSort();

		for (Vertex v : topologicalSort) {
			int highest = 0;
			int timeToComplete = 0;
			for (Vertex vertex : getIncomingVertexes(v)) {

				if (vertex.getEdgeWeight(v) > timeToComplete)
					timeToComplete = vertex.getEdgeWeight(v);

				if (vertex.getEarliest() > highest)
					highest = vertex.getEarliest();
			}

			v.setEarliest(highest + timeToComplete);
		}
	}

	public void latestTimes() {
		List<Vertex> topologicalSort = getTopoLogicalSort();

		for (int i = topologicalSort.size() - 1; i >= 0; i--) {
			Vertex v = topologicalSort.get(i);
			int lowest = Integer.MAX_VALUE;
			int timeToComplete = 0;

			if (v.getEdges().isEmpty()) {
				v.setLatest(v.getEarliest());
			} else {
				for (Vertex vertex : v.getEdges().keySet()) {
					if (v.getEdgeWeight(vertex) > timeToComplete) {
						timeToComplete = v.getEdgeWeight(vertex);
					}

					if (vertex.getLatest() < lowest) {
						lowest = vertex.getLatest();
					}
				}
				v.setLatest(lowest - timeToComplete);
			}
		}
	}

	public List<Vertex> getTopoLogicalSort() {
		List<Vertex> sort = new ArrayList<>();
		Queue<Vertex> queue = new LinkedList<>(getStartingVertexes());

		while (!queue.isEmpty()) {
			Vertex v = queue.poll();
			sort.add(v);

			for (Vertex vertex : v.getEdges().keySet()) {
				if (sort.containsAll(getIncomingVertexes(vertex))) {
					queue.add(vertex);
				}
			}
		}

		return sort;
	}

	public List<Vertex> getIncomingVertexes(Vertex currentVertex) {
		List<Vertex> incomingVertexes = new ArrayList<>();

		for (Vertex vertex : getVertexes()) {
			for (Vertex v : vertex.getEdges().keySet()) {
				if (v.equals(currentVertex)) {
					incomingVertexes.add(vertex);
				}
			}
		}

		return incomingVertexes;
	}
}
