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
            nodes.get(n1).add(n2); // add edge from "n1" to "n2"
            return true;
        }
        throw new UnsupportedOperationException();
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
            // remove n from all adjacent nodes
            for (String node : nodes.keySet()) { // iterate over key of HashMap
                if (nodes.get(node).contains(n))
                    nodes.get(node).remove(n); // get corresponding successor lists
            }

            // remove the node n from the graoh
            nodes.remove(n);
            return true;
        } else {
            return false;
        }
        // throw new UnsupportedOperationException();
    }

    public boolean removeEdge(String n1, String n2) {
        if (this.hasEdge(n1, n2)) {
            nodes.get(n1).remove(n2);
            return true;
        } else {
            return false;
        }
        // throw new UnsupportedOperationException();
    }

    public List<String> nodes() {
        Set<String> nodes_set = nodes.keySet();
        return new ArrayList<>(nodes_set);
        // throw new UnsupportedOperationException();
    }

    public List<String> succ(String n) {
        if (!nodes.containsKey(n)) {
            throw new UnsupportedOperationException();
        }
        List<String> succ_list = new ArrayList<>();
        for (String node : nodes.keySet()) { // iterate over key of HashMap
            if (this.hasEdge(n, node)){
                // get corresponding pred lists
                succ_list.add(node);
            }
        }
        return succ_list;
    }

    public List<String> pred(String n) {
        if (!nodes.containsKey(n)) {
            throw new UnsupportedOperationException();
        }
        List<String> pred_list = new ArrayList<>();
        for (String node : nodes.keySet()) { // iterate over key of HashMap
            if (this.hasEdge(node, n)){
                // get corresponding pred lists
                pred_list.add(node);
            }
        }
        return pred_list;
    }

    public Graph union(Graph g) {
        Graph u_graph  =new ListGraph();
        List<String> g_nodes = g.nodes();
        for(String node: g_nodes){
            // HERE!!!!
        }
        throw new UnsupportedOperationException();
    }

    public Graph subGraph(Set<String> nodes) {
        throw new UnsupportedOperationException();
    }

    public boolean connected(String n1, String n2) {
        // trival
        if(!nodes.containsKey(n1) || !nodes.containsKey(n2)){
            throw new UnsupportedOperationException();
        }
        if (n1==n2){
            return true;
        }


    }
}
