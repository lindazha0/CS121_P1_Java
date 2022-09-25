import java.util.*;

public class Main {

	// Run "java -ea Main" to run with assertions enabled (If you run
	// with assertions disabled, the default, then assert statements
	// will not execute!)

	public static void test1() {
		Graph g = new ListGraph();
		assert g.addNode("a");
		assert g.hasNode("a");
	}

	public static void test2() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);
		Edge e = new Edge("a", "b");
		assert eg.addEdge(e);
		assert eg.hasEdge(e);
	}

	public static void test() {
		Graph g1 = new ListGraph();
		g1.addNode("a");
		g1.addNode("b");
		g1.addNode("c");
		g1.addNode("d");
		g1.addNode("e");

		g1.addEdge("a", "b");
		g1.addEdge("a", "c");
		g1.addEdge("b", "c");
		g1.addEdge("b", "d");
		g1.addEdge("c", "d");
		g1.addEdge("c", "e");
		g1.addEdge("d", "e");
		g1.addEdge("d", "a");
		g1.addEdge("e", "a");
		g1.addEdge("e", "b");

		Set<String> subset = new HashSet<String>(Arrays.asList("a", "c", "e"));

		Graph g2 = g1.subGraph(subset);
		for (String node : subset) {
			assert g2.hasNode(node);
		}
		for (String node : g2.nodes()) {
			assert subset.contains(node);
		}

		List<String> aSucc = g2.succ("a");
		assert aSucc.size() == 1;
		assert g2.hasEdge("a", "c");

		List<String> cSucc = g2.succ("c");
		assert cSucc.size() == 1;
		assert g2.hasEdge("c", "e");

		List<String> eSucc = g2.succ("e");
		assert aSucc.size() == 1;
		assert g2.hasEdge("e", "a");
	}

	public static void main(String[] args) {
		test1();
		test2();
		test();
		System.out.println("Success after all testcases!");
	}

}