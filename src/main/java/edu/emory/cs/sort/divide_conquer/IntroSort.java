package edu.emory.cs.sort.divide_conquer;

import java.util.Comparator;
import edu.emory.cs.sort.AbstractSort;

public class IntroSort<K extends Comparable<K>> extends QuickSort<K> {
    private final AbstractSort<K> engine; // sorting alg for worst cases

    public IntroSort(AbstractSort<K> engine) {
        this(engine, Comparator.naturalOrder());
    }

    public IntroSort(AbstractSort<K> engine, Comparator<K> comp) {
        super(comp);
        this.engine = engine;
    }

    @Override
    public void resetCounts() {
        super.resetCounts();
        if (engine != null) engine.resetCounts();
    }

    @Override
    public void sort(K[] array, int beginIndx, int endIndx) {
        final int maxDepth = getMaxDepth(beginIndx, endIndx);
        sort(array, beginIndx, endIndx, maxDepth);
        comparisons += engine.getComparisonCount();
        assignments += engine.getAssignmentCount();
    }

    protected int getMaxDepth(int beginIndx, int endIndx) {
        return 2 * (int)Math.log(endIndx - beginIndx);
    }

    private void sort(K[] array, int beginIndx, int endIndx, int maxDepth) {
        if (beginIndx >= endIndx) return;

        if (maxDepth == 0)    // encounter the worst case
            engine.sort(array, beginIndx, endIndx);
        else {
            int pivotIndex = partition(array, beginIndx, endIndx);
            sort(array, beginIndx, pivotIndex, maxDepth - 1);
            sort(array, pivotIndex + 1, endIndx, maxDepth - 1);
        }
    }
}
