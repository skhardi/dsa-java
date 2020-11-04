package edu.emory.cs.trie.autocomplete;

import java.util.*;
import java.util.stream.Collectors;

import edu.emory.cs.trie.TrieNode;

public class AutocompleteHW extends Autocomplete<List<String>> {

    public AutocompleteHW(String dict_file, int max) {
        super(dict_file, max);
    }

    @Override
    public List<String> getCandidates(String prefix) {
        int max = getMax();

        prefix = checkPrefix(prefix);

        TrieNode<List<String>> current = find(prefix);
        List<String> candidates = current.getValue();

        if (candidates != null && candidates.size() == max)
            return candidates;

        if (candidates == null)
            candidates = new ArrayList<>(max);

        candidates = breadthFirstSearch(current, prefix, candidates);

        find(prefix).setValue(candidates);
        return candidates;

    }

    private List<String> breadthFirstSearch(TrieNode<List<String>> preNode, String current, List<String> candidates) {
        Deque<TrieNode<List<String>>> nodes = new ArrayDeque<>();
        Map<Character, TrieNode<List<String>>> children;
        TrieNode<List<String>> nextNode, childNode, parent;
        int max = getMax();
        String partTmpCurr, tmpCurr;
        nodes.add(preNode);

        if (preNode.isEndState()) // check to add prefix
            candidates.add(current);

        // bfs traversal of trie to find candidates for current prefix

        while (!nodes.isEmpty() && candidates.size() < max) {
            nextNode = nodes.poll();
            partTmpCurr = "";
            parent = nextNode;
            while (parent != preNode && parent != null) {
                partTmpCurr = parent.getKey() + partTmpCurr ;
                parent = parent.getParent();
            }

            if (nextNode.hasChildren()) {
                children = nextNode.getChildrenMap();
                List<Character> childVals = children.keySet().stream().sorted().collect(Collectors.toList());
                for (Character child : childVals) {
                    if (candidates.size() == max) break;
                    tmpCurr = current + partTmpCurr + child;
                    childNode = find(tmpCurr);
                    if (childNode != null) {
                        nodes.add(childNode);
                        if (childNode.isEndState() && !candidates.contains(tmpCurr))
                            candidates.add(tmpCurr);
                    }
                }
            }
        }

        return candidates;
    }

    @Override
    public void pickCandidate(String prefix, String candidate) {
        int max = getMax();

        prefix = checkPrefix(prefix);

        if (!contains(candidate))
            put(candidate, null);

        List<String> candidates = get(prefix);

        if (candidates == null)
            candidates = new ArrayList<>();

        if (candidates.contains(candidate))
            candidates.remove(candidate);
        else if (candidates.size() == max)
            candidates.remove(candidates.size() - 1);


        candidates.add(0, candidate);
        find(prefix).setValue(candidates);
    }

    private String checkPrefix(String prefix) {
        prefix = prefix.strip();
        if (find(prefix) == null) {
            put(prefix, null);
            find(prefix).setEndState(false);
        }

        return prefix;
    }
}
