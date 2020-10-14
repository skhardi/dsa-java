package edu.emory.cs.tree.balanced;

import edu.emory.cs.tree.AbstractBinaryNode;

public class RedBlackNode<K extends Comparable<K>> extends AbstractBinaryNode<K, RedBlackNode<K>> {
    private boolean isRed;

    protected RedBlackNode(K key) {
        super(key);
        setToRed();
    }

    protected void setToRed() {
        isRed = true;
    }

    protected void setToBlack() {
        isRed = false;
    }

    protected boolean isRed() {
        return isRed;
    }

    protected boolean isBlack() {
        return !isRed;
    }
}
