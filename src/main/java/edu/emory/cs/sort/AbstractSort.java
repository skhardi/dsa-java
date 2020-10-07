package edu.emory.cs.sort;

import java.util.Comparator;

public abstract class AbstractSort<K extends Comparable<K>> {
    final Comparator<K> comparator;
    protected long comparisons, assignments;

    public AbstractSort(Comparator<K> comp) {
        comparator = comp;
        resetCounts();
    }

    abstract public void sort(K[] array, int i, int j);

    public long getComparisonCount() {
        return comparisons;
    }

    public long getAssignmentCount() {
        return assignments;
    }

    public void resetCounts() {
        comparisons = 0;
        assignments = 0;
    }

    public void sort(K[] array) {
        sort(array, 0, array.length);
    }

    protected int compareTo(K[] array, int i, int j) {
        comparisons++;
        return comparator.compare(array[i], array[j]);
    }

    protected void swap(K[] array, int i, int j) {
        K tmp = array[i];
        assign(array, i, array[j]);
        assign(array, j, tmp);
    }

    protected void assign(K[] array, int i, K val) {
        assignments++;
        array[i] = val;
    }
}
