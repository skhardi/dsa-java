package edu.emory.cs.graph;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class Subgraph {
    private final List<Edge> edges;
    private final Set<Integer> vertices;

    public Subgraph() {
        edges = new ArrayList<>();
        vertices = new HashSet<>();
    }

    public Subgraph(Subgraph graph) {
        edges = new ArrayList<>(graph.getEdges());
        vertices = new HashSet<>(graph.getVertices());
    }

    public List<Edge> getEdges() { return edges; }

    public Set<Integer> getVertices() { return vertices; }

    public void addEdge(Edge e) {
        edges.add(e);
        vertices.add(e.getSource());
        vertices.add(e.getTarget());
    }

    public boolean contains(int vertex) { return vertices.contains(vertex); }

    public String toString() {
        StringBuilder build = new StringBuilder();

        for (int i = 0; i < edges.size(); i++) {
//            build.append(i);
//            build.append(" <- ");
            build.append(edges.get(i).toString());
            build.append("\n");
        }

        return build.toString();
    }
}
