import java.util.Arrays;

public class DeterministicSelector {
    private long comparisons;
    private long recursiveCalls;
    private static final int GROUP_SIZE = 5;

    public int select(int[] arr, int k) {
        comparisons = 0;
        recursiveCalls = 0;
        int[] copy = Arrays.copyOf(arr, arr.length);
        return selectHelper(copy, 0, copy.length - 1, k);
    }

    public long getComparisons() { return comparisons; }
    public long getRecursiveCalls() { return recursiveCalls; }

    private int selectHelper(int[] arr, int lo, int hi, int k) {
        recursiveCalls++;

        if (lo == hi) return arr[lo];

        int pivotIndex = medianOfMedians(arr, lo, hi);
        pivotIndex = partition(arr, lo, hi, pivotIndex);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return selectHelper(arr, lo, pivotIndex - 1, k);
        } else {
            return selectHelper(arr, pivotIndex + 1, hi, k);
        }
    }

    private int medianOfMedians(int[] arr, int lo, int hi) {
        int n = hi - lo + 1;

        if (n <= GROUP_SIZE) {
            insertionSort(arr, lo, hi);
            return lo + n / 2;
        }

        int numGroups = (n + GROUP_SIZE - 1) / GROUP_SIZE;
        for (int i = 0; i < numGroups; i++) {
            int groupLo = lo + i * GROUP_SIZE;
            int groupHi = Math.min(groupLo + GROUP_SIZE - 1, hi);
            insertionSort(arr, groupLo, groupHi);
            int medianIndex = groupLo + (groupHi - groupLo) / 2;

            swap(arr, lo + i, medianIndex);
        }

        return selectHelper2(arr, lo, lo + numGroups - 1, (lo + numGroups - 1 - lo) / 2 + lo);
    }

    private int selectHelper2(int[] arr, int lo, int hi, int k) {
        recursiveCalls++;
        if (lo == hi) return lo;

        int pivotIndex = medianOfMedians(arr, lo, hi);
        pivotIndex = partition(arr, lo, hi, pivotIndex);

        if (k == pivotIndex) return pivotIndex;
        else if (k < pivotIndex) return selectHelper2(arr, lo, pivotIndex - 1, k);
        else return selectHelper2(arr, pivotIndex + 1, hi, k);
    }

    private int partition(int[] arr, int lo, int hi, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, hi);
        int storeIndex = lo;

        for (int i = lo; i < hi; i++) {
            comparisons++;
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, hi);
        return storeIndex;
    }

    private void swap(int[] arr, int a, int b) {
        if (a == b) return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private void insertionSort(int[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo && arr[j] > key) {
                comparisons++;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}