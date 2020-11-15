package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class SpanningTree implements Comparable<SpanningTree> {
    private final List<Edge> edges;
    private double total_weight;


    public SpanningTree() {
        edges = new ArrayList<>();
        total_weight = 0;
    }

    public SpanningTree(SpanningTree tree) {
        this.edges = tree.edges;
        this.total_weight += tree.total_weight;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public double getTotalWeight() {
        return total_weight;
    }

    public int size() {
        return edges.size();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
        total_weight += edge.getWeight();
    }

    @Override
    public int compareTo(SpanningTree tree) {
        double cmp = total_weight - tree.total_weight;

        if (cmp > 0) return 1;
        else if (cmp < 0) return -1;
        else return 0;
    }


    public Set<Integer> getTargets() {
        Set<Integer> set = new HashSet<>();

        for (Edge edge : edges)
            set.add(edge.getTarget());

        return set;
    }

    public List<List<Edge>> getCycles() {
        Map<Integer, List<Edge>> edgeMap = getEdgeMap();
        List<List<Edge>> cycles = new ArrayList<>();
        getCyclesAux(cycles, edgeMap, new ArrayList<>(), new HashSet<>(), getAnyEdge(edgeMap));
        return cycles;
    }

    /** @return Map whose keys are source vertices and keys are the edges. */
    private Map<Integer, List<Edge>> getEdgeMap() {
        Map<Integer, List<Edge>> map = new HashMap<>();
        List<Edge> tmp;

        for (Edge edge : edges) {
            tmp = map.computeIfAbsent(edge.getSource(), k -> new ArrayList<>());
            tmp.add(edge);
        }

        return map;
    }

    private Edge getAnyEdge(Map<Integer, List<Edge>> map) {
        for (List<Edge> edge : map.values())
            return edge.get(0);
        return null;
    }

    private void getCyclesAux(List<List<Edge>> cycles, Map<Integer, List<Edge>> edgeMap, List<Edge> cycle, Set<Integer> set, Edge curr) {
        if (edgeMap.isEmpty()) return;
        set.add(curr.getSource());
        cycle.add(curr);

        if (set.contains(curr.getTarget()))        // cycle
        {
            removeAll(edgeMap, set, cycle);
            cycles.add(cycle);
            getCyclesAux(cycles, edgeMap, new ArrayList<>(), new HashSet<>(), getAnyEdge(edgeMap));
        }
        else {
            List<Edge> tmp = edgeMap.get(curr.getTarget());

            if (tmp == null) {
                removeAll(edgeMap, set, cycle);
                getCyclesAux(cycles, edgeMap, new ArrayList<>(), new HashSet<>(), getAnyEdge(edgeMap));
            }
            else {
                for (Edge edge : new ArrayList<>(tmp))
                    getCyclesAux(cycles, edgeMap, new ArrayList<>(cycle), new HashSet<>(set), edge);
            }
        }
    }

    private void removeAll(Map<Integer, List<Edge>> map, Set<Integer> set, List<Edge> cycle) {
        List<Edge> tmp;

        for (int source : set) {
            tmp = map.get(source);

            if (tmp != null) {
                tmp.removeAll(cycle);
                if (tmp.isEmpty()) map.remove(source);
            }
        }
    }
}
