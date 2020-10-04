package edu.emory.cs.sort.comparison;

import java.util.Collections;
import java.util.Comparator;

public class ShellSortQuiz<K extends Comparable<K>> extends ShellSort<K> {

    public ShellSortQuiz() {
        this(Comparator.naturalOrder());
    }

    public ShellSortQuiz(Comparator<K> comp) {
        super(comp);
    }

    @Override
    protected void populateSequence(int n) {
        n /= 2;

        for (int t = sequence.size() + 1; ; t++) {
            int h = (int) (Math.pow(2,t) - 1);
            if (h <= n) sequence.add(h);
            else break;
        }
    }

    @Override
    protected int getSequenceStartIndex(int n) {
        int index = Collections.binarySearch(sequence, n / 2);
        if (index < 0) index = -(index + 1);
        if (index == sequence.size()) index--;
        return index;
    }
}
