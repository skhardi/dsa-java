package edu.emory.cs.graph.flow;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class MaxFlow {
    private Map<Edge, Double> flow_map;
    private double maxflow;

    public MaxFlow(Graph graph) {
        init(graph);
    }

    // initialize all flows = 0
    public void init(Graph graph) {
        flow_map = new HashMap<>();
        maxflow = 0;

        for (Edge e : graph.getAllEdges())
            flow_map.put(e, 0d);
    }

    public double getMaxFlow() { return maxflow; }

    public double getResidual(Edge e) { return e.getWeight() - flow_map.get(e); }

    public void updateFlow(List<Edge> path, double flow) {
        path.forEach(e -> updateFlow(e, flow));
        maxflow += flow;
    }

    public void updateFlow(Edge e, double flow) {
        Double prev = flow_map.getOrDefault(e, 0d);
        flow_map.put(e, prev+flow);
    }
}
