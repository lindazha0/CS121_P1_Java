import java.util.*;

public class ListGraph implements Graph {
    private HashMap<String, LinkedList<String>> nodes = new HashMap<>();

    public boolean addNode(String n) {
        if (!nodes.containsKey(n)) {
            nodes.put(n, new LinkedList<String>()); // add 'n' to the graph
            return true;
        } else {
            return false;
        }
        // throw new UnsupportedOperationException();
    }

    public boolean addEdge(String n1, String n2) {
        if (nodes.containsKey(n1) && nodes.containsKey(n2)) {
            if (hasEdge(n1, n2)) {
                return false;
            }
            nodes.get(n1).add(n2); // add edge from "n1" to "n2"
            return true;
        }
        throw new NoSuchElementException();
        // throw new UnsupportedOperationException();
    }

    public boolean hasNode(String n) {
        return nodes.containsKey(n);
        // throw new UnsupportedOperationException();
    }

    public boolean hasEdge(String n1, String n2) {
        return nodes.containsKey(n1) && nodes.containsKey(n2) && nodes.get(n1).contains(n2);
        // throw new UnsupportedOperationException();
    }

    public boolean removeNode(String n) {
        if (nodes.containsKey(n)) {
            // remove edges to n from all adjacent nodes
            for (String node : nodes.keySet()) { // iterate over key of HashMap
                if (nodes.get(node).contains(n))
                    nodes.get(node).remove(n); // get corresponding successor lists
            }

            // remove the node n from the graph (and all edged from n)
            nodes.remove(n);
            return true;
        } else {
            return false;
        }
        // throw new UnsupportedOperationException();
    }

    public boolean removeEdge(String n1, String n2) {
        if (!nodes.containsKey(n1) || !nodes.containsKey(n2)) {
            throw new NoSuchElementException();
        }
        if (hasEdge(n1, n2)) {
            nodes.get(n1).remove(n2);
            return true;
        } else {
            return false;
        }
    }

    public List<String> nodes() {
        Set<String> nodes_set = nodes.keySet();
        return new ArrayList<>(nodes_set);
        // throw new UnsupportedOperationException();
    }

    public List<String> succ(String n) {
        if (!nodes.containsKey(n)) {
            throw new NoSuchElementException();
            // throw new UnsupportedOperationException();
        }
        List<String> succ_list = new ArrayList<>();
        for (String node : nodes.keySet()) { // iterate over key of HashMap
            if (nodes.get(n).contains(node)) {
                // get corresponding pred lists
                succ_list.add(node);
            }
        }
        return succ_list;
    }

    public List<String> pred(String n) {
        if (!nodes.containsKey(n)) {
            throw new NoSuchElementException();
            // throw new UnsupportedOperationException();
        }
        List<String> pred_list = new ArrayList<>();
        for (String node : nodes.keySet()) { // iterate over key of HashMap
            if (nodes.get(node).contains(n)) {
                // get corresponding pred lists
                pred_list.add(node);
            }
        }
        return pred_list;
    }

    public Graph union(Graph g) {
        for (String node : this.nodes()) {
            if (!g.hasNode(node)) {
                // for node in listGraph: if not added, add node
                g.addNode(node);
            }
        }

        // add edges
        for (String node : this.nodes()) {
            for (String succ_node : this.succ(node)) {
                // for edge from node: if not added, add edge
                if (!g.hasEdge(node, succ_node)) {
                    g.addEdge(node, succ_node);
                }
            }
        }
        return g;
        // throw new UnsupportedOperationException();
    }

    public Graph subGraph(Set<String> nodes) {
        Graph g = new ListGraph();
        for (String node : nodes) {
            if (this.nodes.containsKey(node)) {
                g.addNode(node);
            }
        }

        // add edges
        for (String node : g.nodes()) {
            for (String succ_node : this.succ(node)) {
                // for edge from node: if not added, add edge
                if (!g.hasEdge(node, succ_node)) {
                    addEdge(node, succ_node);
                }
            }
        }
        return g;
        // throw new UnsupportedOperationException();
    }

    public boolean connected(String n1, String n2) {
        // trivial
        if(!nodes.containsKey(n1) || !nodes.containsKey(n2)){
            throw new NoSuchElementException();
        }
        if (n1.equals(n2)){
            return true;
        }
        // BFS from n1 to n2
        List<String> pathList = new LinkedList<>();
        pathList.add(n1);
        Iterator<String> it = pathList.iterator();
        Set<String> path = new HashSet<>(pathList);
        while(it.hasNext()){
            String s = it.next();
            for(String node: this.succ(s)){
                if (!path.contains(node)){
                    path.add(node);
                    pathList.add(node);
                    if(node.equals(n2)){
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}
