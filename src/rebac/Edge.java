package rebac;

public class Edge {

    // source and destination nodes connected by edge
    private Node source, destination;
    // the label (relationship) given to the edge
    private String label;

    Edge(Node source, Node destination, String label) {
        this.source = source;
        this.destination = destination;
        this.label = label;
    }

    Node getDestination() {
        return destination;
    }
    String getLabel() {
        return label;
    }
}
