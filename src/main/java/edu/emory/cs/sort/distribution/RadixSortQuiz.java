package edu.emory.cs.sort.distribution;

public class RadixSortQuiz extends RadixSort {

    public RadixSortQuiz() {
        super();
    }

    @Override
    public void sort(Integer[] array, int beginIndx, int endIndx) {
        int maxBit = getMaxBit(array, beginIndx, endIndx);
        int div = (int)Math.pow(10, maxBit - 1);
        sort(array, beginIndx, endIndx, key -> (key / div)% 10); // sort by max digit using BucketSort's sort()

        // sort by remaining digits within each bucket using BucketSort's sort()
        int y = div;
        while(--maxBit > 0) {
            int i = beginIndx, j = beginIndx + 1;
            while (i < endIndx) {
                while (j < endIndx && ((array[i] / y) % 10) == ((array[j] / y) % 10)) j++;
                int x = y/10;
                sort(array, i, j, key -> (key / x) % 10);
                i = j;
                j++;
            }
            y /= 10;
            maxBit--;
        }
    }
}
