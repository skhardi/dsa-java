package edu.emory.cs.sort.comparison;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public abstract class ShellSort<K extends Comparable<K>> extends InsertionSort<K> {
    List<Integer> sequence;

    ShellSort(Comparator<K> comp) {
        super(comp);
        sequence = new ArrayList<>();
        populateSequence(10000);
    }

    abstract void populateSequence(int n);
    abstract int getSequenceStartIndex(int n);

    @Override
    public void sort(K[] array, int beginIndx, int endIndx) {
        int n = endIndx - beginIndx;
        populateSequence(n);

        for (int i = getSequenceStartIndex(n); i >= 0; i--)
            sort(array, beginIndx, endIndx, sequence.get(i));
    }
}
