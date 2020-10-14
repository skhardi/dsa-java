package edu.emory.cs.tree.balanced;

public class AVLTree<K extends Comparable<K>> extends AbstractBalancedBinarySearchTree<K, AVLNode<K>> {

    @Override
    protected AVLNode<K> createNode(K key) {
        return new AVLNode<>(key);
    }

    @Override
    protected void rotateLeft(AVLNode<K> node) {
        super.rotateLeft(node);
        node.resetHeights();
    }

    @Override
    protected void rotateRight(AVLNode<K> node) {
        super.rotateRight(node);
        node.resetHeights();
    }

    @Override
    protected void balance(AVLNode<K> node) {
        /* Rotation cases
         * Case 1: left zig-zag
         * Case 2: left linear
         * Case 3: right zig-zag
         * Case 4: right linear
         */

        if (node == null) return;
        int bf = node.getBalanceFactor();

        if (bf == 2) {
            AVLNode<K> child = node.getLeftChild();

            if (child.getBalanceFactor() == -1)
                rotateLeft(child);

            rotateRight(node);
        }
        else if (bf == -2) {
            AVLNode<K> child = node.getRightChild();

            if (child.getBalanceFactor() == 1)
                rotateRight(child);

            rotateLeft(node);
        }
        else
            balance(node.getParent());
    }
}
