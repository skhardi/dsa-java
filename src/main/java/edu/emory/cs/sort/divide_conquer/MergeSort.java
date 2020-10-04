package edu.emory.cs.sort.divide_conquer;

import edu.emory.cs.sort.AbstractSort;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.lang.reflect.Array;

public class MergeSort<K extends Comparable<K>> extends AbstractSort<K> {
    private K[] temp;

    public MergeSort() {
        this(Comparator.naturalOrder());
    }

    public MergeSort(Comparator<K> comp) {
        super(comp);
    }

    @Override
    public void sort(K[] array, int beginIndx, int endIndx) {
        if (temp == null || temp.length < array.length)
            temp = (K[])Array.newInstance(array[0].getClass(), array.length);

        sort(array, temp, beginIndx, endIndx);
    }

    protected void sort(K[] input, K[] copy, int beginIndx, int endIndx) {
        if (beginIndx + 1 >= endIndx) return;
        int middleIndx = beginIndx + (endIndx-beginIndx) / 2;

        sort(input, copy, beginIndx, middleIndx);
        sort(input, copy, middleIndx, endIndx);
        merge(input, copy, beginIndx, middleIndx, endIndx);
    }

    protected void merge(K[] input, K[] copy, int beginIndx, int middleIndx, int endIndx) {
        int frst = beginIndx, scnd = middleIndx, n = endIndx - beginIndx;
        System.arraycopy(input, beginIndx, copy, beginIndx, n);
        assignments += n;

        for (int k = beginIndx; k < endIndx; k++) {
            if (frst >= middleIndx) assign(input, k, copy[scnd++]);
            else if (scnd >= endIndx) assign(input, k, copy[frst++]);
            else if (compareTo(copy, frst, scnd) < 0) assign(input, k, copy[frst++]);
            else assign(input, k, copy[scnd++]);
        }
    }
}
