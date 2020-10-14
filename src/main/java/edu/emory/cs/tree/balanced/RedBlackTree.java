package edu.emory.cs.tree.balanced;

public class RedBlackTree<K extends Comparable<K>> extends AbstractBalancedBinarySearchTree<K, RedBlackNode<K>> {
    @Override
    protected RedBlackNode<K> createNode(K key) {
        return new RedBlackNode<>(key);
    }

    @Override
    protected void balance(RedBlackNode<K> node) {
        if (node == root) node.setToBlack();

        else if (node.getParent().isRed()) {
            RedBlackNode<K> uncle = node.getUncle();
            if(uncle != null && uncle.isRed())
                balanceWithRedUncle(node, node.getUncle());
            else
                balanceWithBlackUncle(node);
        }
    }

    private void balanceWithRedUncle(RedBlackNode<K> node, RedBlackNode<K> uncle) {
        node.getParent().setToBlack();
        uncle.setToBlack();

        node.getGrandParent().setToRed();
        balance(node.getGrandParent());
    }

    private void balanceWithBlackUncle(RedBlackNode<K> node) {
        RedBlackNode<K> grandParent = node.getGrandParent();

        if (grandParent != null) {
            RedBlackNode<K> parent = node.getParent();

            if (grandParent.isLeftChild(parent) && parent.isRightChild(node)) {
                rotateLeft(parent);
                node = parent;
            }
            else if (grandParent.isRightChild(parent) && parent.isLeftChild(node)) {
                rotateRight(parent);
                node = parent;
            }

            node.getParent().setToBlack();
            grandParent.setToRed();

            if (node.getParent().isLeftChild(node))
                rotateRight(grandParent);
            else
                rotateLeft(grandParent);
        }
    }
}
