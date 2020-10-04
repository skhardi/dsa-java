package edu.emory.cs.sort.distribution;

public class IntegerBucketSort extends BucketSort<Integer> {
    private final int MIN; //smallest integer in range

    public IntegerBucketSort(int min, int max) {
        super(max - min);
        MIN = min;
    }

    @Override
    public void sort(Integer[] array, int beginIndx, int endIndx) {
        sort(array, beginIndx, endIndx, key -> key-MIN);
    }
}
