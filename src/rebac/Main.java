package rebac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // scanner reads csv's from file and maps names to respective nodes
        Scanner g = new Scanner(new File("EHR-system-graph.csv"));
        Map<String,Node> nodes = new HashMap<>();

        while (g.hasNextLine()) {
            String input = g.nextLine();
            // tokenizes each line into source, destination, label and adds unique nodes to hashmap
            String[] data = input.split(",");
            nodes.putIfAbsent(data[0], new Node(data[0]));
            nodes.putIfAbsent(data[1], new Node(data[1]));
            Node source = nodes.get(data[0]);
            Node destination = nodes.get(data[1]);
            // make new edge outgoing from source to destination with given label
            source.addEdge(new Edge(source, destination, data[2]));
        }

        // make new graph and add nodes from hashmap
        Graph graph = new Graph();
        for (Map.Entry<String,Node> entry : nodes.entrySet()) {
            graph.addNode(entry.getValue());
        }

        // read authorization rules from given file and add to arraylist of rules (strings)
        ArrayList<String> rules = new ArrayList<>();
        Scanner r = new Scanner(new File("EHR-rules"));
        while (r.hasNextLine()) {
            rules.add(r.nextLine().replaceAll("[']", ""));
        }
        // set rules for the graph
        graph.setRules(rules);

        // demonstration of functionality
        // notice access is only granted when the label between Bob and MedRecBob is "owner", not "referred", etc.
        graph.canAccess(nodes.get("MedRecBob"), nodes.get("Bob"));
        graph.canAccess(nodes.get("MedRecBob"), nodes.get("Carol"));
        graph.canAccess(nodes.get("MedRecBob"), nodes.get("Zoe"));
    }
}
