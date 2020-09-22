package edu.emory.cs.queue;

import java.util.Comparator;

public abstract class AbstractPQ<K extends Comparable<K>>{
    protected final Comparator<K> priority;

    AbstractPQ(Comparator<K> priority) {
        this.priority = priority;
    }

    abstract void add(K key);

    abstract K remove();

    abstract int size();

    protected boolean isEmpty() {
        return this.size() == 0;
    }
}
