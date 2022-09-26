import java.util.*;

public class EdgeGraphAdapter implements EdgeGraph {

  private Graph g;

  public EdgeGraphAdapter(Graph g) {
    this.g = g;
  }

  public boolean addEdge(Edge e) {
    // throw new UnsupportedOperationException();
    if (hasEdge(e)) {
      return false;
    }

    // add edge (also nodes if not)
    if (!g.hasNode(e.getSrc())) {
      g.addNode(e.getSrc());
    }
    if (!g.hasNode(e.getDst())) {
      g.addNode(e.getDst());
    }
    g.addEdge(e.getSrc(), e.getDst());
    return true;
  }

  public boolean hasNode(String n) {
    return g.hasNode(n);
    // throw new UnsupportedOperationException();
  }

  public boolean hasEdge(Edge e) {
    return g.hasEdge(e.getSrc(), e.getDst());
    // throw new UnsupportedOperationException();
  }

  public boolean removeEdge(Edge e) {
    if (hasEdge(e)) {
      g.removeEdge(e.getSrc(), e.getDst());

      // if last edge: remove the node
      if (g.succ(e.getSrc()).isEmpty()) {
        g.removeNode(e.getSrc());
      }
      if (g.succ(e.getDst()).isEmpty()) {
        g.removeNode(e.getDst());
      }
      return true;
    }
    return false;

    // no error
    // throw new UnsupportedOperationException();
  }

  public List<Edge> outEdges(String n) {
    List<Edge> edges = new ArrayList<>();
    for (String node : g.succ(n)) {
      edges.add(new Edge(n, node));
    }
    return edges;
    // throw new UnsupportedOperationException();
  }

  public List<Edge> inEdges(String n) {
    List<Edge> edges = new ArrayList<>();
    for (String node : g.nodes()) {
      if (g.succ(node).contains(n)) {
        edges.add(new Edge(node, n));
      }
    }
    return edges;
    // throw new UnsupportedOperationException();
  }

  public List<Edge> edges() {
    List<Edge> edges = new ArrayList<>();
    for (String n : g.nodes()) {
      for (String node : g.succ(n)) {
        edges.add(new Edge(n, node));
      }
    }
    return edges;
    // throw new UnsupportedOperationException();
  }

  public EdgeGraph union(EdgeGraph g) {
    // clone this.g
    EdgeGraph u_g = new EdgeGraphAdapter(this.g);
    for (Edge e : g.edges()) {
      if (!u_g.hasEdge(e)) {
        u_g.addEdge(e);
      }
    }
    return u_g;
    // throw new UnsupportedOperationException();
  }

  public boolean hasPath(List<Edge> e) {
    // trvial
    if (e.isEmpty()) {
      return true;
    }

    // iterate the list
    Iterator<Edge> it = e.iterator();
    Edge curr_e = it.next();
    String dst = curr_e.getDst();
    while (it.hasNext()) {
      // if g has edge
      if (!hasEdge(curr_e)) {
        throw new BadPath();
      }

      // if is a path
      curr_e = it.next();
      if (!curr_e.getSrc().equals(dst)) {
        throw new BadPath();
      }
      dst = curr_e.getDst();
    }
    return true;
    // throw new UnsupportedOperationException();
  }

}
