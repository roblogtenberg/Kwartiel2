package opdracht4;

public class Main {

	public static void main(String[] args) {
		///// Starting vertexes test /////
//		GenericGraph graph = new GenericGraph();
//		Vertex a = new Vertex("a");
//		Vertex b = new Vertex("b");
//		Vertex c = new Vertex("c");
//		Vertex d = new Vertex("d");
//		Vertex e = new Vertex("e");
//		graph.addVertex(a);
//		graph.addVertex(b);
//		graph.addVertex(c);
//		graph.addVertex(d);
//		graph.addVertex(e);
//		graph.connect(a, c, 1);
//		graph.connect(b, d, 1);
//		graph.connect(c, e, 1);
//		System.out.println(graph.getStartingVertexes());
		PERTNetwork network = new PERTNetwork();
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");
		network.addVertex(a);
		network.addVertex(b);
		network.addVertex(c);
		network.addVertex(d);
		network.addVertex(e);
		network.connect(c, a, 1);
		network.connect(d, b, 1);
		network.connect(e, c, 1);
		System.out.println(network.getTopoLogicalSort());
	}
}
