package edu.emory.cs.graph.flow;

import edu.emory.cs.graph.Graph;

public interface NetworkFlow {
    MaxFlow getMaximumFlow(Graph graph, int source, int target);
}
