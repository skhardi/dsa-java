package edu.emory.cs.sort.distribution;

import edu.emory.cs.sort.AbstractSort;

import java.util.List;
import java.util.Deque;
import java.util.function.Function;
import java.util.stream.*;
import java.util.ArrayDeque;

public abstract class BucketSort<K extends Comparable<K>> extends AbstractSort<K> {
    List<Deque<K>> buckets;

    public BucketSort(int numBuckets) {
        super(null);
        buckets = Stream.generate(ArrayDeque<K>::new).limit(numBuckets).collect(Collectors.toList());
    }

    public void sort(K[] array, int beginIndx, int endIndx, Function<K, Integer> bucketIndx) {
        for (int i = beginIndx; i < endIndx; i++) {
            buckets.get(bucketIndx.apply(array[i])).add(array[i]);
        }

        for (Deque<K> bucket : buckets) {
            while (!bucket.isEmpty())
                array[beginIndx++] = bucket.remove();
        }
    }
}
