import java.util.Random;

public class QuickSorter {
    private long comparisons;
    private long swaps;
    private int maxDepth;
    private static final int CUTOFF = 10;
    private final Random random = new Random();

    public void sort(int[] arr) {
        comparisons = 0;
        swaps = 0;
        maxDepth = 0;
        sortHelper(arr, 0, arr.length - 1, 0);
    }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public int getMaxDepth() { return maxDepth; }

    private void sortHelper(int[] arr, int lo, int hi, int depth) {
        while (lo < hi) {
            maxDepth = Math.max(maxDepth, depth);

            if (hi - lo + 1 <= CUTOFF) {
                insertionSort(arr, lo, hi);
                return;
            }

            int p = partition(arr, lo, hi);


            int leftSize = p - lo;
            int rightSize = hi - p - 1;

            if (leftSize < rightSize) {
                sortHelper(arr, lo, p - 1, depth + 1);
                lo = p + 1;
            } else {
                sortHelper(arr, p + 1, hi, depth + 1);
                hi = p - 1;
            }
            depth++;
        }
    }

    private int partition(int[] arr, int lo, int hi) {
        // рандомный пивот: выбираем случайный индекс и переносим его в конец
        int pivotIndex = lo + random.nextInt(hi - lo + 1);
        swap(arr, pivotIndex, hi);
        int pivot = arr[hi];

        int i = lo - 1;

        for (int j = lo; j < hi; j++) {
            comparisons++;
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, hi);
        return i + 1;
    }

    private void swap(int[] arr, int a, int b) {
        if (a == b) return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        swaps++;
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