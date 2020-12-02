package edu.emory.cs.graph.span;

import edu.emory.cs.graph.Graph;
import org.junit.Test;

import java.util.List;

public class MyMSTAllHWTest {
    @Test
    public void testMSTAllHW() {
        MSTAll gold = new MSTAllHW();
        long st, et;

        st = System.currentTimeMillis();
        List<SpanningTree> list = gold.getMinimumSpanningTrees(getEasyGraph());
        et = System.currentTimeMillis();
        System.out.printf("%8d, %d\n", list.size(), et - st);

        for (SpanningTree tree : list) {
            System.out.println(tree.toString());
            System.out.println();
        }

        // test graph 1
            st = System.currentTimeMillis();
            list = gold.getMinimumSpanningTrees(getGraph1());
            et = System.currentTimeMillis();
            System.out.printf("%8d, %d\n", list.size(), et - st);

            for (SpanningTree tree : list) {
                System.out.println(tree.toString());
                System.out.println();
            }

        System.out.println();
        // test graph 2
            st = System.currentTimeMillis();
            list = gold.getMinimumSpanningTrees(getGraph2());
            et = System.currentTimeMillis();
            System.out.printf("%8d, %d\n", list.size(), et - st);

        for (SpanningTree tree : list) {
            System.out.println(tree.toString());
            System.out.println();
        }
    }

    public Graph getEasyGraph() {
        Graph graph = new Graph(3);
        graph.setUndirectedEdge(0,1,2);
        graph.setUndirectedEdge(1,2,1);
        graph.setUndirectedEdge(2,0,1);
        return graph;
    }

    public Graph getGraph1() {
        Graph graph = new Graph(5);
        graph.setUndirectedEdge(0,1,3);
        graph.setUndirectedEdge(0,4,2);
        graph.setUndirectedEdge(0,2,4);
        graph.setUndirectedEdge(0,3,1);
        graph.setUndirectedEdge(1,2,2);
        graph.setUndirectedEdge(3,4,3);
        graph.setUndirectedEdge(2,3,4);

        return graph;
    }

    public Graph getGraph2() {
        Graph graph = new Graph(5);
        graph.setUndirectedEdge(0,1,1);
        graph.setUndirectedEdge(0,2,3);
        graph.setUndirectedEdge(1,2,4);
        graph.setUndirectedEdge(2, 3,3);
        graph.setUndirectedEdge(2,4,3);
        graph.setUndirectedEdge(3,4,2);

        return graph;
    }

//    public Graph getGraph2() {
//        Graph graph = new Graph(5);
//        graph.setUndirectedEdge(0,1,75);
//        graph.setUndirectedEdge(0,2,9);
//        graph.setUndirectedEdge(0,4,9);
//        graph.setUndirectedEdge(1,2,95);
//        graph.setUndirectedEdge(1,3,51);
//        graph.setUndirectedEdge(2,3,19);
//        graph.setUndirectedEdge(2,4,42);
//        graph.setUndirectedEdge(3,4,31);
//
//        return graph;
//    }
}
