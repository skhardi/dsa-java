package edu.emory.cs.sort.distribution;

public class LSDRadixSort extends RadixSort {

    public LSDRadixSort() {
        super();
    }

    @Override
    public void sort(Integer[] array, int beginIndx, int endIndx) {
        int maxBit = getMaxBit(array, beginIndx, endIndx);
        for (int bit = 0; bit < maxBit; bit++) {
            int div = (int)Math.pow(10, bit);
            sort(array, beginIndx, endIndx, key -> (key / div) % 10);
        }
    }
}
