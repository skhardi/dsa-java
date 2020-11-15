package edu.emory.cs.graph;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraphQuiz extends Graph {
    private int count = 0;

    public GraphQuiz(int size) { super(size); }
    public GraphQuiz(Graph g) { super(g); }

    public int numberOfCycles() {

        if (!containsCycle()) return 0;

        boolean[] visited = new boolean[size()];
        boolean[] partVisited = new boolean[size()];

        List<Deque<Edge>> oge = getOutgoingEdges();

        for(int i = 0; i < oge.size(); i++)
            for (Edge e : oge.remove(i))
                numberOfCyclesAux(e, visited, partVisited);

        return count;
    }

    private void numberOfCyclesAux(Edge edge, boolean visited[], boolean partVisited[]) {
        if (visited[edge.getSource()]) {
            count++;
            return;
        }

        visited[edge.getSource()] = true;
        if (partVisited[edge.getSource()]) {
            return;
        }

        for (Edge e : getIncomingEdges(edge.getSource())) {
            numberOfCyclesAux(e, visited, partVisited);
        }

        partVisited[edge.getTarget()] = true;
        visited[edge.getTarget()] = false;
    }
}


