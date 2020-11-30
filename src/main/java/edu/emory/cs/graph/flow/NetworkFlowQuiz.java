package edu.emory.cs.graph.flow;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Subgraph;
import edu.emory.cs.graph.Graph;

import java.util.HashSet;
import java.util.Set;

public class NetworkFlowQuiz {
    public Set<Subgraph> getAugmentingPaths(Graph graph, int source, int target) {
        Set<Subgraph> setSubs = new HashSet<>();

        if (graph != null)
            getAugmentingPath(setSubs, graph, new Subgraph(), source, target);

        return setSubs;
    }

    private Subgraph getAugmentingPath(Set<Subgraph> set, Graph graph, Subgraph sub, int source, int target) {
        if (source == target) return sub;
        Subgraph tmp;

        for (Edge edge : graph.getIncomingEdges(target)) {
            if (sub.contains(edge.getSource())) continue;
            if (edge.getWeight() <= 0) continue;
            tmp = new Subgraph(sub);
            tmp.addEdge(edge);
            tmp = getAugmentingPath(set,graph, tmp, source, edge.getSource());
            if (tmp != null) set.add(tmp);
        }

        return null;
    }
}
