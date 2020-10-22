package edu.emory.cs.tree;

import edu.emory.cs.tree.balanced.BalancedBinarySearchTreeQuiz;
import org.junit.Test;

public class BalancedBSTTest  {
    @Test
    public void testBalancedBST() {
        BalancedBinarySearchTreeQuiz<Integer> bbst = new BalancedBinarySearchTreeQuiz<>();

        // Tree 1
        bbst.add(3);
        bbst.add(2);
        bbst.add(1);
        bbst.add(5);
        System.out.println(bbst.toString());
        bbst.add(4);
        System.out.println(bbst.toString() + "\n");

        // Tree 2
        bbst = new BalancedBinarySearchTreeQuiz<>();
        bbst.add(3);
        bbst.add(1);
        bbst.add(2);
        bbst.add(5);
        System.out.println(bbst.toString());
        bbst.add(4);
        System.out.println(bbst.toString() + "\n");

        // Tree 3
        bbst = new BalancedBinarySearchTreeQuiz<>();
        bbst.add(3);
        bbst.add(1);
        bbst.add(2);
        bbst.add(4);
        System.out.println(bbst.toString());
        bbst.add(5);
        System.out.println(bbst.toString() + "\n");

        // Tree 4
        bbst = new BalancedBinarySearchTreeQuiz<>();
        bbst.add(3);
        bbst.add(2);
        bbst.add(1);
        bbst.add(4);
        System.out.println(bbst.toString());
        bbst.add(5);
        System.out.println(bbst.toString() + "\n");
    }
}