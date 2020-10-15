package edu.emory.cs.tree;

public abstract class AbstractBinarySearchTree<K extends Comparable<K>, N extends AbstractBinaryNode<K, N>> {
    protected N root;

    protected AbstractBinarySearchTree() {
        setRoot(null);
    }

    protected abstract N createNode(K key);

    boolean isRoot(N node) { return root == node; }

    protected N getRoot() { return root; }

    protected void setRoot(N node) {
        if (node != null) node.setParent(null);
        root = node;
    }

    protected N get(K key) {
        return findNode(root, key);
    }

    protected N findNode(N node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.getKey());
        if (cmp < 0) return findNode(node.getLeftChild(), key);
        else if (cmp > 0) return findNode(node.getRightChild(), key);
        else return node;
    }

    protected N findMinNode(N node) {
            return node.hasLeftChild() ? findMinNode(node.getLeftChild()) : node;
    }

    protected N findMaxNode(N node) {
        return node.hasRightChild() ? findMaxNode(node.getRightChild()) : node;
    }

    protected N add(K key) {
        N node;

        if (root == null) {
            node = createNode(key);
            setRoot(node);
        }
        else {
            node = addAux(root, key);
        }

        return node;
    }

    private N addAux(N node, K key) {
        N child, newNode = null;

        int cmp = key.compareTo(node.getKey());
        if (cmp > 0) {
            if ((child = node.getRightChild()) == null)
                node.setRightChild(newNode = createNode(key));
            else
                newNode = addAux(child, key);
        }
        else if (cmp < 0) {
            if ((child = node.getLeftChild()) == null)
                node.setLeftChild(newNode = createNode(key));
            else
                newNode = addAux(node.getLeftChild(), key);
        }

        return newNode;
    }

    protected N remove(K key) {
        N node = get(key);
        if (node != null) {
            if (node.hasBothChildren())
                removeHibbard(node);
            else removeSelf(node);
        }

        return node;
    }

    protected N removeSelf(N node) {
        N parent = node.getParent();
        N child = null;

        if (node.hasRightChild()) child = node.getRightChild();
        else if (node.hasLeftChild()) child = node.getLeftChild();
        replaceChild(node, child);

        return parent;
    }

    protected N removeHibbard(N node) {
        N successor = node.getRightChild();
        N min = findMinNode(successor);
        N parent = min.getParent();

        min.setLeftChild(node.getLeftChild());

        if (min != successor) {
            parent.setLeftChild(min.getRightChild());
            min.setRightChild(successor);
        }

        replaceChild(node, min);
        return parent;
    }

    private void replaceChild(N oldNode, N newNode) {
        if (isRoot(oldNode)) setRoot(newNode);

        else
            oldNode.getParent().replaceChild(oldNode, newNode);
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public K min() {
        return (root != null) ? findMinNode(root).getKey() : null;
    }

    public K max() {
        return (root != null) ? findMaxNode(root).getKey() : null;
    }

    public String toString() {
        return (root != null) ? root.toString() : "null";
    }
}
