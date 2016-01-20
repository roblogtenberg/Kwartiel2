package opdracht4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PERTNetwork extends GenericGraph{

	public void earliestTimes() {
		//TODO: implement
		getTopoLogicalSort();
	}
	
	public void latestTimes() {
		//TODO: implement
		getTopoLogicalSort();
	}
	
	public List<Vertex> getTopoLogicalSort() {
		List<Vertex> sort = new ArrayList<>();
		Queue<Vertex> queue = new LinkedList<>(getStartingVertexes());

		while(!queue.isEmpty()) {
			Vertex v = queue.poll();
			sort.add(v);
			
			for(Vertex vertex : v.getEdges().keySet()) {
				if(!sort.contains(vertex) && !queue.contains(vertex)) {
					queue.add(vertex);
				}
			}
		}
		
		return sort;
	}
}
