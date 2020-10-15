package edu.emory.cs.tree;

public class BinarySearchTree<K extends Comparable<K>> extends AbstractBinarySearchTree<K, BinaryNode<K>> {
    @Override
    protected BinaryNode<K> createNode(K key) {
        return new BinaryNode<>(key);
    }
}
