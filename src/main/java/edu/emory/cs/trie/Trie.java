package edu.emory.cs.trie;

public class Trie<K> {
    private final TrieNode<K> root;

    /*
     no-arg constructor creates new trie node w/
     null parent and dummy key
     */
    protected Trie() {
        root = new TrieNode<>(null, (char)0);
    }

    protected TrieNode<K> getRoot() { return root; }

    protected TrieNode<K> find(String key) {
        TrieNode<K> current = root;
        if (current != null) {
            for (int i = 0; i < key.length(); i++) {
                current = current.getChild(key.charAt(i));
                if (current == null) break;
            }
        }

        return current;
    }

    protected K get(String key) {
        TrieNode<K> node = find(key);

        if (node != null && node.isEndState())
            return node.getValue();
        else
            return null;
    }

    protected boolean contains(String key) {
        return get(key) != null;
    }

    protected K put(String key, K value) {
        TrieNode<K> node = root;

        for (int i = 0; i < key.length(); i++) {
            node = node.addChild(key.charAt(i));
        }

        node.setEndState(true);
        return node.setValue(value);
    }

    /**
     *
     * @param key key to be removed
     * @return false if key is not in trie, true otherwise
     * removes nodes with characters of key if without other children, otherwise sets end state to false
     */
    protected boolean remove(String key) {
        TrieNode<K> node = find(key);

        if (node == null || !node.isEndState()) return false;

        if (node.hasChildren()) {
            node.setEndState(false);
            return true;
        }

        TrieNode<K> parent = node.getParent();
        while (parent != null) {
            parent.removeChild(node.getKey());

            if (parent.hasChildren() || parent.isEndState()) break;

            else {
                node = parent;
                parent = node.getParent();
            }
        }

        return true;
    }
}
