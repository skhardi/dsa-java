package edu.emory.cs.queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class TernaryHeapQuiz<K extends Comparable<K>> extends AbstractPQ<K> {
    protected final List<K> keys;
    private int size;

    TernaryHeapQuiz() {
        super(Comparator.naturalOrder());
        keys = new ArrayList<>();
        keys.add(null);
    }

    TernaryHeapQuiz(Comparator<K> priority) {
        super(priority);
        keys = new ArrayList<>();
        keys.add(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(K key) {
        keys.add(key);
        swim(++size);
    }

    @Override
    public K remove() {
        if (isEmpty())
            throw new NoSuchElementException("Empty List");

        swap(1, size);
        K removed = keys.remove(size--);
        sink();

        return removed;
    }

    private void swim(int index) {
        for (; index > 2 && compare(index, index/3) > 0; index /=3) {
            swap(index,index/3);
        }
//        if(compare(index, index-1) > 0) swap(index, index-1);
    }

    private void sink() {
        for (int k = 1, i = 2; i <= size; k = i, i *= 3) {
            if (i < size && compare(i, i + 1) < 0) i++;
            if (i < size && compare(i, i + 1) < 0) i++;
            if (compare(k, i) >= 0) break;
            swap(k, i);
        }
    }

    private void swap(int index1, int index2) {
        K tmp = keys.get(index1);
        keys.set(index1, keys.get(index2));
        keys.set(index2, tmp);
    }

    private int compare(int index1, int index2) {
        return priority.compare(keys.get(index1), keys.get(index2));
    }
}
