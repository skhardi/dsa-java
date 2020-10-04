package edu.emory.cs.sort.comparison;

import java.util.Comparator;
import edu.emory.cs.sort.AbstractSort;

public class HeapSort<K extends Comparable<K>> extends AbstractSort<K> {

    public HeapSort() {
        this(Comparator.naturalOrder());
    }

    public HeapSort(Comparator<K> comp) {
        super(comp);
    }

    @Override
    public void sort(K[] array, int beginIndx, int endIndx) {
        for (int k = getParentIndex(beginIndx, endIndx); k >= beginIndx; k--) {
            sink(array, k, beginIndx, endIndx);
        }

        while(endIndx - 1 > beginIndx) {
            swap(array, beginIndx, --endIndx);
            sink(array, beginIndx, beginIndx, endIndx);
        }

    }

    private void sink(K[] array, int k, int beginIndx, int endIndx) {
        for (int i = getLeftChildIndex(beginIndx, k); i < endIndx; k = i, i = getLeftChildIndex(beginIndx, k)) {
            if(i+1 < endIndx && compareTo(array, i, i+1) < 0) i++;
            if(compareTo(array, k, i) >= 0) break;
            swap(array, k, i);
        }
    }

    private int getParentIndex(int beginIndx, int k) {
        return (k-beginIndx) / 2 - 1 + beginIndx;
    }

    private int getLeftChildIndex(int beginIndx, int k) {
        return 2 * (k-beginIndx) + 1 + beginIndx;
    }
}
