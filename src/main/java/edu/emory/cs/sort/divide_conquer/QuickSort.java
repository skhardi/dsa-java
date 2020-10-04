package edu.emory.cs.sort.divide_conquer;

import edu.emory.cs.sort.AbstractSort;
import java.util.Comparator;

public class QuickSort<K extends Comparable<K>> extends AbstractSort<K> {

    public QuickSort() {
        this(Comparator.naturalOrder());
    }

    public QuickSort(Comparator<K> comp) {
        super(comp);
    }

    @Override
    public void sort(K[] array, int beginIndx, int endIndx) {
        if (beginIndx >= endIndx) return;

        int pivotIndx = partition(array, beginIndx, endIndx);

        sort(array, beginIndx, pivotIndx);
        sort(array, pivotIndx + 1, endIndx);
    }

    protected int partition(K[] array, int beginIndx, int endIndx) {
        int fst = beginIndx, snd = endIndx;

        while (true) {
            // find where endIndex > fst > pivot
            while (++fst < endIndx && compareTo(array, beginIndx, fst) >= 0);
            // find where beginIndex < snd < pivot
            while (--snd > beginIndx && compareTo(array, beginIndx, snd) <= 0);
            // pointers crossed
            if (fst >= snd) break;
            // exchange
            swap(array, fst, snd);
        }

        swap(array, beginIndx, snd);
        return snd;
    }
}
