package edu.emory.cs.sort.distribution;

import java.util.Arrays;


public abstract class RadixSort extends BucketSort<Integer> {

    protected RadixSort() {
        super(10);
    }

    protected int getMaxBit(Integer[] array, int beginIndx, int endIndx) {
        Integer max = Arrays.stream(array, beginIndx, endIndx).reduce(Integer::max).orElse(null);
        return (max != null && max > 0) ? (int)Math.log10(max) + 1 : 0;
    }

}
