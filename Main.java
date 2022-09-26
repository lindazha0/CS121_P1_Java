import java.util.*;

public class Main {

	// Run "java -ea Main" to run with assertions enabled (If you run
	// with assertions disabled, the default, then assert statements
	// will not execute!)

	public static void test1() {
		Graph g = new ListGraph();
		assert g.addNode("a");
		assert g.hasNode("a");

		g.addNode("a");
		g.addNode("b");
		g.addNode("c");
		g.addNode("d");
		g.addEdge("a", "b");
		g.addEdge("b", "c");
		g.addEdge("c", "d");

		if (g.connected("a", "d")) {
			System.out.println("is connected, pass test1!");
		}
	}

	public static void test2() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);
		Edge e = new Edge("a", "b");
		List<Edge> l = new ArrayList<>();
		l.add(e);
		assert eg.addEdge(e);
		assert eg.hasEdge(e);
		assert eg.hasPath(l);
		System.out.println("Pass test2!");
	}

	public static void test3() {

		Graph g = new ListGraph();
		g.addNode("a");
		g.addNode("b");
		g.addEdge("a", "b");
		assert g.hasEdge("a", "b");
		System.out.println("Nodes of graph:" + g.nodes());

		g.addNode("c");
		g.addNode("d");
		g.addNode("e");
		g.addEdge("a", "c");

		assert g.hasEdge("a", "c");

		g.addEdge("a", "d");

		assert g.hasEdge("a", "d");

		g.addEdge("a", "e");

		assert g.hasEdge("a", "e");

		System.out.println("Nodes of graph: " + g.nodes());

		System.out.println("Successors of A: " + g.succ("a"));

		g.addEdge("b", "a");

		assert g.hasEdge("b", "a");

		g.addEdge("c", "a");

		assert g.hasEdge("c", "a");

		g.addEdge("d", "a");

		assert g.hasEdge("d", "a");

		System.out.println("Predecessors of A: " + g.pred("a"));

	}

	public static void graphUnitTest() {
		// this test cover all the methods of Graph

		Graph graph = new ListGraph();

		// test add function
		assert graph.addNode("a");
		assert graph.addNode("b");
		assert graph.addNode("c");
		assert graph.addNode("d");

		assert graph.addEdge("a", "b");
		assert graph.addEdge("b", "c");
		assert graph.addEdge("c", "d");
		assert graph.addEdge("d", "b");
		// System.out.println(graph.nodes());

		// test valid cases about add
		assert graph.hasNode("a");
		assert graph.hasNode("b");
		assert graph.hasNode("c");
		assert graph.hasNode("d");

		assert graph.hasEdge("a", "b");
		assert graph.hasEdge("b", "c");
		assert graph.hasEdge("c", "d");
		assert graph.hasEdge("d", "b");
		// System.out.println(graph.nodes());

		List<String> nodeList = new ArrayList<String>();
		nodeList = graph.nodes();
		assert nodeList.contains("a");
		assert nodeList.contains("b");
		assert nodeList.contains("c");
		assert nodeList.contains("d");

		// test invalid case about add
		assert !graph.hasNode("e");
		assert !graph.hasEdge("a", "d");

		// test succ & pred & connected
		nodeList = graph.succ("b");
		assert !nodeList.contains("a");
		assert nodeList.contains("c");

		nodeList = graph.pred("b");
		assert nodeList.contains("a");
		assert nodeList.contains("d");
		assert !nodeList.contains("c");

		assert graph.connected("a", "d");
		graph.addNode("z");
		assert !graph.connected("a", "z");
		graph.removeNode("z");

		// test remove function (valid & invalid)
		assert graph.removeEdge("c", "d");
		assert !graph.hasEdge("c", "d");

		assert graph.removeNode("b");
		assert !graph.hasNode("b");
		assert !graph.hasEdge("b", "c");
		assert !graph.hasEdge("b", "d");
		assert !graph.hasEdge("a", "b");

		// test unionGraph & subGraph
		Graph graph2 = new ListGraph();
		assert graph2.addNode("e");
		assert graph2.addNode("f");
		assert graph2.addEdge("e", "f");
		Graph unionGraph = graph.union(graph2);
		assert unionGraph.hasNode("a");
		assert unionGraph.hasNode("c");
		assert unionGraph.hasNode("d");
		assert unionGraph.hasNode("e");
		assert unionGraph.hasNode("f");
		assert unionGraph.hasEdge("e", "f");

		Set<String> nodes = new HashSet<String>();
		nodes.add("a");
		nodes.add("c");
		graph.addEdge("a", "c");
		graph.addEdge("c", "d");
		Graph subGraph = graph.subGraph(nodes);
		System.out.println(subGraph.nodes());

		assert subGraph.hasNode("a");
		assert subGraph.hasEdge("a", "c");
		assert !subGraph.hasNode("d");
		

	}

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		graphUnitTest();
		System.out.println("Success after all testcases!");
	}

}