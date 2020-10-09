package edu.emory.cs.sort.hybrid;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/* sort each row first, then merge */
public class HybridSortHW<K extends Comparable<K>> implements HybridSort<K> {
    Comparator<K> comparator = Comparator.naturalOrder();

    @Override
    public K[] sort(K[][] input) {
        for (int i = 0; i < input.length; i++)
            sort(input[i], 0, input[i].length, getRecurLimit(0, input[i].length));

        return merge(input);
    }

    protected void sort(K[] array, int beginIndex, int endIndex, int recurDepth) {
        if ((endIndex - beginIndex) <= 10) InsertionSort(array, beginIndex, endIndex);

        else if (recurDepth == 0) HeapSort(array, beginIndex, endIndex);

        else QuickSort(array, beginIndex, endIndex, recurDepth);
    }

    protected void InsertionSort(K[] array, int beginIndex, int endIndex) {

        for (int i = beginIndex + 1;  i < endIndex; i++) {
            for (int j = i; j > beginIndex; j--) {
                if (compareTo(array[j], array[j-1]) >= 0) break;
                swap(array, j, j-1);
            }
        }
    }

    protected void QuickSort(K[] array, int beginIndex, int endIndex, int recurDepth) {
        if (beginIndex >= endIndex) return;

        int pivot = partition(array, beginIndex, endIndex);

        sort(array, beginIndex, pivot, recurDepth-1);
        sort(array, pivot+ 1, endIndex, recurDepth-1);
    }

    private int partition(K[] array, int beginIndex, int endIndex) {
        // find pivot : swap median of (1st, middle, last index) with 1st index key
        findMedianOfThree(array, beginIndex, (beginIndex + (endIndex - beginIndex - 1) / 2), endIndex - 1);

        int i = beginIndex + 1, j = endIndex - 1;

        while (i <= j) {
            // increase pointer i until element greater than pivot found
            while(i < endIndex && compareTo(array[beginIndex], array[i]) >= 0) i++;
            // decrease pointer j until element less than pivot found
            while(j > beginIndex && compareTo(array[beginIndex], array[j]) <= 0) j--;

            if (i >= j) break;
            swap(array, i, j);
        }

        swap(array, beginIndex, j);
        return j;
    }

    private void findMedianOfThree(K[] array, int i, int j, int k) {
        int i_j = compareTo(array[i], array[j]), i_k = compareTo(array[i], array[k]), j_k = compareTo(array[j], array[k]);

        if (i_j < 0) {
            if (i_k < 0 && j_k > 0) swap(array, i, k); // order: i,k,j
            else if (i_k < 0 && j_k < 0) swap(array, i, j); // i,j,k
        }
        else {      // i_j >= 0
            if (i_k > 0 && j_k < 0) swap(array, i, k); // order: j,k,i
            else if (i_k > 0 && j_k > 0) swap(array, i, j); // k,j,i
        }
        // swapped or k, i, j or j, i, k
    }

    protected void HeapSort(K[] array, int beginIndex, int endIndex) {
        for (int i = getParent(beginIndex, endIndex); i >= beginIndex; i--) {
            sink(array, i, beginIndex, endIndex);
        }

        while(endIndex - 1 > beginIndex) {
            swap(array, beginIndex, --endIndex);
            sink(array, beginIndex, beginIndex, endIndex);
        }
    }

    private void sink(K[] array, int k, int beginIndex, int endIndex) {
        for (int i = getLeftChild(beginIndex, k); i < endIndex; k = i, i = getLeftChild(beginIndex, k)) {
            if(i+1 < endIndex && compareTo(array[i], array[i+1]) < 0) i++;
            if(compareTo(array[k], array[i]) >= 0) break;
            swap(array, k, i);
        }
    }

    private int getParent(int beginIndex, int k) {
        return (k-beginIndex) / 2 - 1 + beginIndex;
    }

    private int getLeftChild(int beginIndex, int k) {
        return 2 * (k-beginIndex) + 1 + beginIndex;
    }

    private int compareTo(K i, K j) {
        return comparator.compare(i, j);
    }

    private void swap(K[] array, int i, int j) {
        K tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    private int getRecurLimit(int beginIndex, int endIndex) {
        int N = endIndex - beginIndex;
        return 2 * (int)Math.log(N);
    }

    protected K[] merge(K[][] array) {

        K[] output = to1DArray(array);
        K[] copy = (K[])Array.newInstance(output[0].getClass(), output.length);

        merge(array, output, copy, 0, array.length);

        return output;

    }

    private void merge(K[][] array, K[] merging, K[] copy, int firstRow, int endRow) {

        int midRow = (endRow + firstRow) / 2;

        if (firstRow < endRow - 1) {
            merge(array, merging, copy, firstRow,  midRow);
            merge(array, merging, copy, midRow,  endRow);
            merge(array, merging, copy, firstRow, midRow, endRow);
        }

    }

    private void merge(K[][] array, K[] merging, K[] copy, int firstRow, int midRow, int endRow) {

        if (firstRow >= endRow - 1) return;

        int[] indices = getMergeIndices(array, firstRow, midRow, endRow);

        int frst = indices[0], scnd = indices[1];
        System.arraycopy(merging, indices[0], copy, indices[0], indices[2] - indices[0]);

        for (int k = indices[0]; k < indices[2]; k++) {
            if (frst >= indices[1]) merging[k] = copy[scnd++];
            else if (scnd >= indices[2]) merging[k] = copy[frst++];
            else if (compareTo(copy[frst], copy[scnd]) < 0) merging[k] = copy[frst++];
            else merging[k] = copy[scnd++];
        }

    }

    private int[] getMergeIndices(K[][] array, int firstRow, int midRow, int endRow) {

        int beginIndex = 0;
        for (int i = 0; i < firstRow; i++) beginIndex += array[i].length;

        int middleIndex = beginIndex;
        for (int j = firstRow; j < midRow; j++) middleIndex += array[j].length;

        int endIndex = middleIndex;
        for (int k = midRow; k < endRow; k++) endIndex += array[k].length;

        return new int[]{beginIndex, middleIndex, endIndex};

    }

    protected K[] to1DArray(K[][] array) {

        int size = Arrays.stream(array).mapToInt(k -> k.length).sum();
        K[] output = (K[])Array.newInstance(array[0][0].getClass(), size);

        int beginIndex = 0;

        for (K[] k : array) {
            System.arraycopy(k, 0, output, beginIndex, k.length);
            beginIndex += k.length;
        }

        return output;
    }
}
