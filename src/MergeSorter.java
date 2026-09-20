public class MergeSorter {
    private int[] aux;
    private long comparisons;
    private int maxDepth;
    private static final int CUTOFF = 10;

    public void sort(int[] arr) {
        aux = new int[arr.length];
        comparisons = 0;
        maxDepth = 0;
        sortHelper(arr, 0, arr.length - 1, 0);
    }

    public long getComparisons() { return comparisons; }
    public int getMaxDepth() { return maxDepth; }

    private void sortHelper(int[] arr, int lo, int hi, int depth) {
        maxDepth = Math.max(maxDepth, depth);

        if (hi - lo + 1 <= CUTOFF) {
            insertionSort(arr, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sortHelper(arr, lo, mid, depth + 1);
        sortHelper(arr, mid + 1, hi, depth + 1);
        merge(arr, lo, mid, hi);
    }

    private void merge(int[] arr, int lo, int mid, int hi) {
        // копируем текущий рабочий диапазон в aux
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }

        int l = lo;        // указатель на левую половину [lo..mid]
        int r = mid + 1;   // указатель на правую половину [mid+1..hi]
        int i = lo;        // указатель записи в arr

        while (l <= mid && r <= hi) {
            comparisons++;
            if (aux[l] <= aux[r]) {
                arr[i] = aux[l];
                l++;
            } else {
                arr[i] = aux[r];
                r++;
            }
            i++;
        }
        while (l <= mid) {
            arr[i] = aux[l];
            l++;
            i++;
        }
        while (r <= hi) {
            arr[i] = aux[r];
            r++;
            i++;
        }
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