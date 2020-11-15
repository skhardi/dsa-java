package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Edge;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

public class MSTPrim implements MST {
    @Override
    public SpanningTree getMinimumSpanningTree(Graph graph) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        SpanningTree tree = new SpanningTree();
        Set<Integer> visited = new HashSet<>();
        Edge edge;

        add(pq, visited, graph, 0);

        while (!pq.isEmpty()) {
            edge = pq.poll();

            if (!visited.contains(edge.getSource()))
                tree.addEdge(edge);
                if (tree.size() + 1 == graph.size()) break;
                add(pq, visited, graph, edge.getSource());
        }

        return tree;
    }

    private void add(PriorityQueue pq, Set<Integer> visited, Graph graph, int target) {
        visited.add(target);

        for (Edge edge : graph.getIncomingEdges(target)) {
            if (!visited.contains(edge.getSource()))
                pq.add(edge);
        }
    }
}
