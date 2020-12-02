package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Edge;
import edu.emory.cs.set.DisjointSet;

import java.util.*;

public class MSTAllHW implements MSTAll {

    @Override
    public List<SpanningTree> getMinimumSpanningTrees(Graph graph) {

        final int V = (byte)(graph.size() - 1);
        final int E = (byte)graph.getAllEdges().size();
        byte[] edgesRep = new byte[V];
        List<SpanningTree> allMsts = new ArrayList<>();
        SpanningTree curr;

        if (V <= 0) return allMsts;

        for (byte i = 0; i < V; i++) {
            edgesRep[i] = i;
        }

        while (edgesRep[V-1] < E) {
            Graph g = new Graph(graph.size());
            for (int i : edgesRep) {
                Edge e = graph.getAllEdges().get(i);
                g.setDirectedEdge(e.getSource(),e.getTarget(),e.getWeight());
            }
            if ((curr = getPrimMST(g)) != null)
                allMsts.add(curr);

            byte tmp = (byte)(V - 1);
            while (tmp > 0 && edgesRep[tmp] == E - V + tmp) {
                tmp--;
            }
            edgesRep[tmp] = (byte)(edgesRep[tmp] + 1);
            for (byte i = (byte) (tmp + 1); i < V; i++) {
                edgesRep[i] = (byte) (edgesRep[i - 1] + 1);
            }
        }

        return filterMSTs(allMsts);
    }

    public SpanningTree getPrimMST(Graph graph) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        SpanningTree tree = new SpanningTree();
        Set<Integer> visited = new HashSet<>();
        Edge edge;

        add(queue, visited, graph, 0);

        while (!queue.isEmpty()) {
            edge = queue.poll();

            if (!visited.contains(edge.getSource())) {
                tree.addEdge(edge);
                if (tree.size() + 1 == graph.size()) return tree;
                add(queue, visited, graph, edge.getSource());
            }
        }

        return null;
    }

    private void add(PriorityQueue<Edge> queue, Set<Integer> visited, Graph graph, int target) {
        visited.add(target);
        for (Edge edge : graph.getIncomingEdges(target)) {
            if (!visited.contains(edge.getSource()))
                queue.add(edge);
        }
    }

    /**
     *
     * @param MST_list list of all possible mst's
     * @return filtered list of msts, removed of duplicate msts and possible incorrect ones
     */
    private List<SpanningTree> filterMSTs(List<SpanningTree> MST_list) {
        if (MST_list.isEmpty()) return MST_list;
        double min = MST_list.get(0).getTotalWeight();
        for (int i = 0; i < MST_list.size(); i++) {
            String sequence = MST_list.get(i).getUndirectedSequence();
            min = Math.min(min, MST_list.get(i).getTotalWeight());
            for (int j = i+1; j < MST_list.size() && j > 0; j++) {
                if (sequence.compareTo(MST_list.get(j).getUndirectedSequence()) == 0) {
                    MST_list.remove(j--);
                }
            }
        }


        for (int i = 0; i < MST_list.size(); i++) {
            if (min < MST_list.get(i).getTotalWeight())
                MST_list.remove(i--);
        }

        return MST_list;
    }
}



