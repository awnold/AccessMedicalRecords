package rebac;

import java.util.*;

public class Graph {
    // list of unique nodes
    private List<Node> nodes = new ArrayList<>();
    // list of rules in the form of strings
    private ArrayList<String> rules;

    // add node to graph
    void addNode(Node node) {
        nodes.add(node);
    }

    // imports an ArrayList of authorization rules
    void setRules(ArrayList<String> rules) {
        this.rules = rules;
    }

    // function that determines if source can access destination based on authorization rules
    void canAccess(Node source, Node destination) {
        // initialization of objects required for recursive BFS
        Map<Node, Boolean> visited = new HashMap<>(nodes.size());
        ArrayList<Node> nodePath = new ArrayList();
        ArrayList edgePath = new ArrayList();
        nodePath.add(source); // add root node to traversed path
        checkPaths(source, destination, visited, nodePath, edgePath); // checks all paths from source to destination
    }

    // recursive function to cross-check all possible paths from source to destination with authorization rules
    void checkPaths(Node source, Node destination, Map<Node,Boolean> visited, ArrayList nodePath, ArrayList edgePath) {
        boolean access = false;
        // when destination node has been reached
        if (source.equals(destination)) {
            System.out.println(nodePath);
            System.out.println(edgePath);
            // checks if edgePath matches that of any given rules
            for (String rule : rules) {
                if (edgePath.toString().equals(rule)) {
                    access = true;
                }
            } if (access) { // if match is found, access is granted
                System.out.println("-> Access granted! <-\n");
            } else { // else, access is denied
                System.out.println("-> Access denied! <-\n");
            }
            return;
        }
        // add source node to visited nodes
        visited.put(source, true);
        for (Edge edge : source.edges) {
            // if current destination node has not been visited, add node and edge to their respective paths
            if (!visited.containsKey(edge.getDestination()) ) {
                nodePath.add(edge.getDestination());
                edgePath.add(edge.getLabel());
                // recursive call with new params (new source node, updated nodePath, edgePath)
                checkPaths(edge.getDestination(), destination, visited, nodePath, edgePath);
                // remove node and edge from paths
                nodePath.remove(edge.getDestination());
                edgePath.remove(edge.getLabel());
            }
        }
        visited.replace(source, false);
        return;
    }
}
