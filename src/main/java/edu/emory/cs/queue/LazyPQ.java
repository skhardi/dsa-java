package edu.emory.cs.queue;

import java.util.*;

public class LazyPQ<K extends Comparable<K>> extends AbstractPQ<K> {
    protected final List<K> keys;
    private int size = 0;

    LazyPQ() {
        super(Comparator.naturalOrder());
        keys = new ArrayList<>();
    }

    LazyPQ(Comparator<K> priority) {
        super(priority);
        keys = new ArrayList<>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(K key) {
        keys.add(size++,key);
    }

    @Override
    public K remove() {
        if (isEmpty())
            throw new NoSuchElementException("Empty List");
        K m = keys.get(0);
        int mIndex = 0;

        for (int i = 1; i < size(); i++) {
            if (priority.compare(m, keys.get(i)) < 0) {
                m = keys.get(i);
                mIndex = i;
            }
        }
        keys.remove(mIndex); size--;
        return m;
    }
}
