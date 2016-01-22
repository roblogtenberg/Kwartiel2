package opdracht4;

public class Main {

	public static void main(String[] args) {
		PERTNetwork network = new PERTNetwork();
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");
		Vertex f = new Vertex("f");
		Vertex g = new Vertex("g");
		network.addVertex(a);
		network.addVertex(b);
		network.addVertex(c);
		network.addVertex(d);
		network.addVertex(e);
		network.addVertex(f);
		network.addVertex(g);
		network.connect(a, b, 3);
		network.connect(a, d, 1);
		network.connect(a, g, 3);
		network.connect(b, c, 1);
		network.connect(b, f, 1);
		network.connect(d, e, 1);
		network.connect(e, f, 1);
		network.connect(g, f, 2);
		network.connect(f, c, 1);
		network.earliestTimes();
		network.latestTimes();
		System.out.println(network.getVertexes());
	}
}
