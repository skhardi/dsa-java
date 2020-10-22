package edu.emory.cs.tree.balanced;

import edu.emory.cs.tree.BinaryNode;

public class BalancedBinarySearchTreeQuiz<K extends Comparable<K>> extends AbstractBalancedBinarySearchTree<K, BinaryNode<K>> {
    @Override
    public BinaryNode<K> createNode(K key) {
        return new BinaryNode<>(key);
    }

    @Override
    protected void balance(BinaryNode<K> node) {
        if (node != null) {
            BinaryNode<K> parent = node.getParent();
            BinaryNode<K> uncle = node.getUncle();
            BinaryNode<K> grandparent = node.getGrandParent();
            if(parent != null && uncle != null && grandparent != null) {
                if (!parent.hasBothChildren() && grandparent.isRightChild(parent) && !uncle.hasBothChildren()) { // node is only child, parent is right child of grandparent, and uncle only has one child

                    if (parent.isLeftChild(node)) rotateRight(parent);
                    if (uncle.hasRightChild()) rotateLeft(uncle);

                    rotateLeft(grandparent);
                    rotateRight(grandparent);
                }
            }
        }
    }
}
