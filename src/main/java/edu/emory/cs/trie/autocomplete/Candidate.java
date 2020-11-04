package edu.emory.cs.trie.autocomplete;

public class Candidate implements Comparable<Candidate> {
    int frequency = 0, recency;
    final String word;

    public Candidate(String word) {
        this.word = word;
    }

    @Override
    public int compareTo(Candidate x) {
        return Integer.compare(frequency, x.frequency);
    }

    public void print() {
       System.out.print(word);
    }

    public boolean match(String word) {
        return this.word.equals(word);
    }
}
