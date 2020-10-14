package edu.emory.cs.tree;

public abstract class AbstractBinaryNode<K extends Comparable<K>, N extends AbstractBinaryNode<K, N>> {
    private K key;
    private N parent, leftChild, rightChild;

    public AbstractBinaryNode(K key) {
        setKey(key);
    }

    public boolean hasParent() { return parent != null; }

    public boolean hasLeftChild() { return leftChild != null; }

    public boolean hasRightChild() { return rightChild != null; }

    public boolean hasBothChildren() { return hasRightChild() && hasLeftChild(); }

    public boolean isLeftChild(N node) { return leftChild == node; }

    public boolean isRightChild(N node) { return rightChild == node; }

    public K getKey() { return key; }

    public N getParent() { return parent; }

    public N getLeftChild() { return leftChild; }

    public N getRightChild() { return rightChild; }

    public N getGrandParent() {
        return hasParent() ? parent.getParent() : null;
    }

    @SuppressWarnings("unchecked")
    public N getSibling() {
        if (hasParent()) {
            return parent.isLeftChild((N)this) ? parent.getRightChild() : parent.getLeftChild();
        }

        return null;
    }

    public N getUncle() {
        return hasParent() ? parent.getSibling() : null;
    }

    public void setKey(K key) { this.key = key; }

    public void setParent(N node) { parent = node; }

    public void setLeftChild(N node) { replaceParent(node); leftChild = node; }

    public void setRightChild(N node) { replaceParent(node); rightChild = node; }

    /** replaces parent of given node with this node
     * @param node whose parent to be replaced
     */
    @SuppressWarnings("unchecked")
    public void replaceParent(N node) {
        if (node != null) {
            if (node.hasParent())
                node.getParent().replaceChild(node, null);
            node.setParent((N) this);
        }
    }

    public void replaceChild(N oldChild, N newChild) {
        if (isLeftChild(oldChild)) setLeftChild(newChild);
        else if (isRightChild(oldChild)) setRightChild(newChild);
    }

    @Override
    public String toString() {
        return key + " -> (" + leftChild + ", " + rightChild + ")";
    }
}
