package edu.emory.cs.sort.comparison;

import edu.emory.cs.sort.AbstractSort;

import java.util.Comparator;

public class InsertionSort<K extends Comparable<K>> extends AbstractSort<K> {

    public InsertionSort() {
        this(Comparator.naturalOrder());
    }

    public InsertionSort(Comparator<K> comp) {
        super(comp);
    }

    public void sort(K[] array, int beginIndx, int endIndx) {
        sort(array, beginIndx, endIndx, 1);
    }

    protected void sort(K[] array, int beginIndx, int endIndx, final int h) {
        for (int i = beginIndx + h; i < endIndx; i++) {
            for (int j = i; j-h >= beginIndx; j-=h) {
                if (compareTo(array, j, j-h) >= 0) break;
                swap(array, j, j-h);
            }
        }
    }

}
