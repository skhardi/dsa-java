package edu.emory.cs.sort.comparison;

import java.util.Comparator;
import edu.emory.cs.sort.AbstractSort;

public class SelectionSort<K extends Comparable<K>> extends AbstractSort<K> {

    public SelectionSort() {
        this(Comparator.naturalOrder());
    }

    public SelectionSort(Comparator<K> comp) {
        super(comp);
    }

    @Override
    public void sort(K[] array, final int beginIndx, final int endIndx) {
        for (int i = endIndx; i > beginIndx; i--) {
            int m = 0;

            for (int j = beginIndx + 1; j < i; j++) {
                if (compareTo(array, j, m) > 0)
                    m = j;
            }

            swap(array, m, i - 1);
        }
    }
}
