package edu.emory.cs.tree.balanced;

import edu.emory.cs.tree.AbstractBinaryNode;

public class AVLNode<K extends Comparable<K>> extends AbstractBinaryNode<K, AVLNode<K>> {
    private int height;

    public AVLNode(K key) {
        super(key);
        height = 1;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setLeftChild(AVLNode<K> node) {
        super.setLeftChild(node);
        resetHeights();
    }

    @Override
    public void setRightChild(AVLNode<K> node) {
        super.setRightChild(node);
        resetHeights();
    }

    public void resetHeights() {
        resetHeightsAux(this);
    }

    private void resetHeightsAux(AVLNode<K> node) {
        if (node != null) {
            int lh = node.hasLeftChild() ? node.getLeftChild().getHeight() : 0;
            int rh = node.hasRightChild() ? node.getRightChild().getHeight() : 0;
            int height = Math.max(lh, rh) + 1;

            if (height != node.getHeight()) {
                node.setHeight(height);
                resetHeightsAux(node.getParent());  // recursively update parent height
            }
        }
    }

    public int getBalanceFactor() {
        if (hasBothChildren())
            return getRightChild().getHeight() - getLeftChild().getHeight();
        else if (hasLeftChild())
            return getLeftChild().getHeight();
        else if (hasRightChild())
            return -getRightChild().getHeight();
        else
            return 0;
    }
}
