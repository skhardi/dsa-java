package edu.emory.cs.graph.flow;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;

import java.util.*;

public class NetworkFlowQuizExtra {
    public Set<Subgraph> getAugmentingPaths(Graph graph, int source, int target) {
        Set<Subgraph> setSubs = new HashSet<>();

        if (graph != null)
            getAugmentingPath(setSubs, graph, new Subgraph(), source, target);

        return setSubs;
    }

    private Subgraph getAugmentingPath(Set<Subgraph> set, Graph graph, Subgraph sub, int source, int target) {
        if (source == target) return sub;

        List<Subgraph> subgraph_list = new ArrayList<>();
        List<Edge> edge_list = new ArrayList<>();
        Subgraph tmp;

        for (Edge edge : graph.getIncomingEdges(target)) {
            if (sub.contains(edge.getSource())) continue;
            if (edge.getWeight() <= 0) continue;
            tmp = new Subgraph(sub);
            tmp.addEdge(edge);
            subgraph_list.add(tmp);
            edge_list.add(edge);
        }

        for (Edge edge : edge_list) {
            if (!subgraph_list.isEmpty()) {
                tmp = getAugmentingPath(set, graph, subgraph_list.remove(0), source, edge.getSource());
                if (tmp != null) set.add(tmp);
            }
        }
        return null;
    }
}
