package edu.emory.cs.queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EagerPQ<K extends Comparable<K>> extends AbstractPQ<K> {
    protected final List<K> keys;
    private int size;

    EagerPQ() {
        super(Comparator.naturalOrder());
        keys = new ArrayList<>();
    }

    EagerPQ(Comparator<K> priority) {
        super(priority);
        keys = new ArrayList<>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(K key) {
        if (isEmpty()) {
            keys.add(size++, key);
            return;
        }
        else {
            for (int i = 0; i < size(); i++) {
                if (priority.compare(key, keys.get(i)) > 0) {
                    keys.add(i,key);
                    size++; return;
                }
            }
            keys.add(size++, key);
        }
    }

    @Override
    public K remove() {
        if (isEmpty()) throw new NoSuchElementException("Empty List");

        K key = keys.remove(0); size--;
        return key;
    }
}
