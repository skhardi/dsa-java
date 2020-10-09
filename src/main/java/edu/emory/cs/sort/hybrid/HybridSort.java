package edu.emory.cs.sort.hybrid;

import edu.emory.cs.sort.AbstractSort;

public interface HybridSort<K extends Comparable<K>> {
    K[] sort(K[][] input);
}
