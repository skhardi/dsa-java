package edu.emory.cs.graph.flow;

import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public class NetworkFlowQuizTest {
    @Test
    public void test() {
        NetworkFlowQuiz nfq = new NetworkFlowQuiz();
//        NetworkFlowQuizExtra nfq = new NetworkFlowQuizExtra();

        Set<Subgraph> set = nfq.getAugmentingPaths(getGraph0(), 0, 5);
        set.stream().forEach(sub -> System.out.println(sub.getEdges()));

        System.out.println();

        set = nfq.getAugmentingPaths(getGraph1(), 0, 3);
        set.stream().forEach(sub -> System.out.println(sub.getEdges()));

        System.out.println();

        set = nfq.getAugmentingPaths(getUndirectedGraph1(), 0, 3);
        set.stream().forEach(sub -> System.out.println(sub.getEdges()));

        System.out.println();

        set = nfq.getAugmentingPaths(getNullGraph(), 0, 2);
        set.stream().forEach(sub -> System.out.println(sub.getEdges()));

        System.out.println();

        set = nfq.getAugmentingPaths(getGraph1(), 2, 2);
        set.stream().forEach(sub -> System.out.println(sub.getEdges()));

        System.out.println();

        set = nfq.getAugmentingPaths(getGraph0(), 2, 0);
        set.stream().forEach(sub -> System.out.println(sub.getEdges()));

        System.out.println();


        set = nfq.getAugmentingPaths(getCompleteGraph(4), 1, 3);
        set.stream().forEach(sub -> System.out.println(sub.getEdges()));

    }

    Graph getGraph0() {
        Graph graph = new Graph(6);
        int s = 0, t = 5;

        graph.setDirectedEdge(s, 1, 4);
        graph.setDirectedEdge(s, 2, 2);
        graph.setDirectedEdge(1, 3, 3);
        graph.setDirectedEdge(2, 3, 2);
        graph.setDirectedEdge(2, 4, 3);
        graph.setDirectedEdge(3, 2, 1);
        graph.setDirectedEdge(3, t, 2);
        graph.setDirectedEdge(4, t, 4);

        return graph;
    }

    public Graph getGraph1() {
        Graph graph = new Graph(4);
        int s = 0, t = 3;

        graph.setDirectedEdge(2, t, 1);
        graph.setDirectedEdge(1, t, 1);
        graph.setDirectedEdge(1, 2, 1);
        graph.setDirectedEdge(s, 2, 1);
        graph.setDirectedEdge(s, 1, 1);

        return graph;
    }
    public Graph getUndirectedGraph1() {
        Graph graph = new Graph(4);
        int s = 0, t = 3;

        graph.setUndirectedEdge(2, t, 1);
        graph.setUndirectedEdge(1, t, 1);
        graph.setUndirectedEdge(1, 2, 1);
        graph.setUndirectedEdge(s, 2, 1);
        graph.setUndirectedEdge(s, 1, 1);

        return graph;
    }
//    public Graph getGraph2() {
//        Graph graph = new Graph(8);
//        int s = 0, t = 7;
//
//        graph.setUndirectedEdge(0,1,1);
//        graph.setUndirectedEdge(0,2,1);
//        graph.setUndirectedEdge(1,2,2);
//        graph.setUndirectedEdge(2,3,3);
//        graph.setUndirectedEdge(2,5,3);
//        graph.setUndirectedEdge(3,4,2);
//        graph.setUndirectedEdge(3,6,2);
//        graph.setUndirectedEdge();
//
//        return graph;
//    }

    public Graph getNullGraph() {
        Graph graph = null;

        return graph;
    }

    Graph getCompleteGraph(int V) {
        Graph graph = new Graph(V);

        for (int i = 0; i < V - 1; i++)
            for (int j = i + 1; j < V; j++)
                graph.setUndirectedEdge(i, j, 1);

        return graph;
    }
}
