package edu.emory.cs.queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class TernaryHeapQuiz<K extends Comparable<K>> extends AbstractPQ<K> {
    protected final List<K> keys;

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
        return keys.size() - 1;
    }

    @Override
    public void add(K key) {
        keys.add(key);
        swim(size());
    }

    @Override
    public K remove() {
        if (isEmpty())
            throw new NoSuchElementException("Empty List");

        swap(1, size());
        K removed = keys.remove(size());
        sink();

        return removed;
    }

    private void swim(int index) {
        for (; index > 3 && compare(getParent(index), index) < 0; index = getParent(index)) {
            swap(index,getParent(index));
        }
        if(index < 4) {
            if(compare(index, 1) > 0) swap(index, 1);
        }
    }

    private int getParent(int index){
        if (index%3 == 2) {
            return (index+1) / 3; // this is because the right child will
        }							//result in a one-off error due to being divisible by 3
        if(index%3 == 1)
            return (index-1)/3 ;
        return index/3; //same as floor (index/3)
    }

    private void sink() {
        int maxchild;
        for (int k = 1, i = 3; i <= size(); k = maxchild, i = (maxchild* 3)) {
            maxchild = i-1;
            if (i <= size() && compare(i, i-1) > 0) {
                maxchild = i;
            }
            if (i < (size()-1) && compare(i+1, maxchild) > 0){
                    maxchild = i+1;
            }
            if (compare(k, maxchild) >= 0) {break;}
          swap(k, maxchild);

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
