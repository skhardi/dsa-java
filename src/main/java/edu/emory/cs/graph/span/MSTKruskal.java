package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Edge;
import java.util.PriorityQueue;
import edu.emory.cs.set.DisjointSet;

public class MSTKruskal implements MST {
    @Override
    public SpanningTree getMinimumSpanningTree(Graph graph) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(graph.getAllEdges());
        DisjointSet forest = new DisjointSet(graph.size());
        SpanningTree tree = new SpanningTree();
        Edge edge;

        while (!pq.isEmpty()) {
            edge = pq.poll();

            if (!forest.inSameSet(edge.getTarget(), edge.getSource())) {
                tree.addEdge(edge);
                if (tree.size() + 1 == graph.size()) break;
                forest.union(edge.getTarget(), edge.getSource());
            }
        }

        return tree;
    }
}
