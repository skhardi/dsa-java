package edu.emory.cs.trie.autocomplete;

import edu.emory.cs.trie.TrieNode;

import java.util.*;
import java.util.stream.Collectors;

public class AutocompleteHWExtra extends Autocomplete<List<Candidate>> {
     private int numPicked = 0;

     AutocompleteHWExtra(String dict_file, int max) {
          super(dict_file, max);
     }

     @Override
     public List<Candidate> getCandidates(String prefix) {
          int max = getMax();

          prefix = checkPrefix(prefix);

          TrieNode<List<Candidate>> current = find(prefix);
          List<Candidate> candidates = current.getValue();

          if (candidates != null && candidates.size() == max)
               return candidates;

          if (candidates == null)
               candidates = new ArrayList<>(max);

          candidates = breadthFirstSearch(current, prefix, candidates);

          find(prefix).setValue(candidates);
          return candidates;
     }

     @Override
     public void pickCandidate(String prefix, String candidate) {
          int max = getMax();
          Candidate cand;

          prefix = checkPrefix(prefix);

          if (find(candidate) == null)
               put(candidate, null);

          List<Candidate> listOfCandidates = get(prefix);

          if (listOfCandidates == null)
               listOfCandidates = new ArrayList<>();

          if ((cand = contains(listOfCandidates, candidate)) != null) {
               listOfCandidates.remove(cand);
          }
          else {
               if (listOfCandidates.size() == max)
                    listOfCandidates.remove(listOfCandidates.size() - 1);
               cand = new Candidate(candidate);
          }

          cand.recency = ++numPicked;
          cand.frequency++;

          int index = 0;
          for (Candidate c : listOfCandidates) {
               if (cand.compareTo(c) > 0) break;
               else if (cand.compareTo(c) == 0) {
                    if(Integer.compare(cand.recency, c.recency) > 0) break;
               }
               else index++;
          }
          listOfCandidates.add(index, cand);

          find(prefix).setValue(listOfCandidates);
     }

     private String checkPrefix(String prefix) {
          prefix = prefix.strip();
          if (find(prefix) == null) {
               put(prefix, null);
               find(prefix).setEndState(false);
          }

          return prefix;
     }

     private List<Candidate> breadthFirstSearch(TrieNode<List<Candidate>> preNode, String current, List<Candidate> candidates) {
          Deque<TrieNode<List<Candidate>>> nodes = new ArrayDeque<>();
          Map<Character, TrieNode<List<Candidate>>> children;
          TrieNode<List<Candidate>> nextNode, childNode, parent;
          int max = getMax();
          String partTmpCurr, tmpCurr;

          nodes.add(preNode);
          if (preNode.isEndState()) // check to add prefix
               candidates.add(new Candidate(current));

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
                                   candidates.add(new Candidate(tmpCurr));
                         }
                    }
               }
          }

          return candidates;
     }

     private Candidate contains(List<Candidate> list, String candidate) {
          for (Candidate c : list) {
               if (c.match(candidate))
                    return c;
          }
          return null;
     }
}
