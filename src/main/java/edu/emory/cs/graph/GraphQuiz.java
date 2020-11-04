package edu.emory.cs.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Set;
import java.util.HashSet;

public class GraphQuiz extends Graph {
    public GraphQuiz(int size) { super(size); }
    public GraphQuiz(Graph g) { super(g); }

    public int numberOfCycles() {
        int count = 0;
        /*
        start at target
        while has out going edge
            follow edge
            mark visited
            if already visited
                break
         */
//
//        if (!this.containsCycle()) return 0;
//
//        Set<Integer> visited = new HashSet<>();
//        for (int i = 0; i < size(); i ++) {
//            dfSearch();
//        }
        Deque<Integer> notVisited = IntStream.range(0, size()).boxed().collect(Collectors.toCollection(ArrayDeque::new));

        while (!notVisited.isEmpty()) {
            count += containsCycleAux(notVisited.poll(), notVisited, new HashSet<>(), count);
        }


        return count;
    }

    private int containsCycleAux(int target, Deque<Integer> notVisited, Set<Integer> visited, int count) {
        notVisited.remove(target);
        visited.add(target);

        for (Edge edge : getIncomingEdges(target)) {
            if (visited.contains(edge.getSource()))
                return ++count;

            if (containsCycleAux(edge.getSource(), notVisited, new HashSet<>(visited), ++count) > count)
                return count;
        }

        return count;
    }

}
