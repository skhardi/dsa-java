package edu.emory.cs.tree;

public class BinaryNode<K extends Comparable<K>> extends AbstractBinaryNode<K, BinaryNode<K>>  {
    public BinaryNode(K key) {
        super(key);
    }
}
