package edu.emory.cs.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode<K> {
    private final Map<Character, TrieNode<K>> children;
    private TrieNode<K> parent;
    private boolean endState;
    private char key;
    private K value;

    protected TrieNode(TrieNode<K> parent, char key) {
        children = new HashMap<>();
        setEndState(false);
        setParent(parent);
        setKey(key);
        setValue(null);
    }

    // getter methods:

    protected TrieNode<K> getParent() { return parent; }

    protected char getKey() { return key; }

    protected K getValue() { return value; }

    protected TrieNode<K> getChild(char key) { return children.get(key); }

    protected Map<Character, TrieNode<K>> getChildrenMap() { return children; }

    // setter methods:

    protected void setParent(TrieNode<K> parent) { this.parent = parent; }

    protected void setEndState(boolean endState) { this.endState = endState; }

    protected void setKey(char key) { this.key = key; }

    protected K setValue(K value) {
        K tmp = this.value;
        this.value = value;
        return tmp;
    }

    /**
     * @param key key of child to be added to this
     * @return new or preexisting TrieNode child w/ given key
     */
    protected TrieNode<K> addChild(char key) {
        TrieNode<K> child;

        if ((child = getChild(key)) == null) {
            children.put(key, child = new TrieNode<>(this, key));
        }

        return child;
    }

    protected TrieNode<K> removeChild(char key) {
        return children.remove(key);
    }

    // boolean statements:

    protected boolean isEndState() { return endState; }

    protected boolean hasValue() { return value != null; }

    protected boolean hasChildren() { return !children.isEmpty(); }
}
