package rebac;

import java.util.*;

public class Node {
    String name; // name of person
    List<Edge> edges = new ArrayList<>(); // outgoing edges

    Node(String name) {
        this.name = name;
    }

    // add adjacent edge
    void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override // override toString for readability
    public String toString() {
        return name;
    }
}
