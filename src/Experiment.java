import java.util.Arrays;

public class Experiment {

    static ExperimentResult runMergeSort(int[] arr, String inputType) {
        int[] data = Arrays.copyOf(arr, arr.length);
        MergeSorter mergeSorter = new MergeSorter();

        long start = System.nanoTime();
        mergeSorter.sort(data);
        long end = System.nanoTime();

        return new ExperimentResult("MergeSort", inputType, arr.length,
                mergeSorter.getMaxDepth(), mergeSorter.getComparisons(), -1, end - start);
    }

    static ExperimentResult runQuickSort(int[] arr, String inputType) {
        int[] data = Arrays.copyOf(arr, arr.length);
        QuickSorter quickSorter = new QuickSorter();

        long start = System.nanoTime();
        quickSorter.sort(data);
        long end = System.nanoTime();

        return new ExperimentResult("QuickSort", inputType, arr.length,
                quickSorter.getMaxDepth(), quickSorter.getComparisons(), -1, end - start);
    }

    static ExperimentResult runSelect(int[] arr, String inputType) {
        int[] data = Arrays.copyOf(arr, arr.length);
        DeterministicSelector selector = new DeterministicSelector();
        int k = data.length / 2;

        long start = System.nanoTime();
        selector.select(data, k);
        long end = System.nanoTime();

        return new ExperimentResult("Select", inputType, arr.length,
                -1, selector.getComparisons(), selector.getRecursiveCalls(), end - start);
    }

    static ExperimentResult runClosestPair(Point[] points, String inputType) {
        ClosestPairSolver solver = new ClosestPairSolver();

        long start = System.nanoTime();
        solver.findClosestPair(points);
        long end = System.nanoTime();

        return new ExperimentResult("ClosestPair", inputType, points.length,
                solver.getMaxDepth(), solver.getDistanceCalculations(), solver.getRecursiveCalls(), end - start);
    }
}