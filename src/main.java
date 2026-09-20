import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main {
    static final int[] SIZES = {100, 1000, 10000, 100000};
    static final String[] INPUT_TYPES = {"random", "sorted", "reverse", "duplicate"};
    static final Random random = new Random();

    public static void main(String[] args) throws IOException {
        List<ExperimentResult> results = new ArrayList<>();

        for (int n : SIZES) {
            for (String type : INPUT_TYPES) {
                int[] data = generate(type, n);

                results.add(Experiment.runMergeSort(data, type));
                results.add(Experiment.runQuickSort(data, type));
                results.add(Experiment.runSelect(data, type));
            }
        }

        for (int n : SIZES) {
            Point[] points = randomPoints(n);
            results.add(Experiment.runClosestPair(points, "random"));
        }

        for (ExperimentResult r : results) {
            System.out.println(r);
            System.out.println("---");
        }

        writeCsv(results, "results/results.csv");
    }

    static int[] generate(String type, int n) {
        switch (type) {
            case "random": return randomArray(n);
            case "sorted": return sortedArray(n);
            case "reverse": return reverseArray(n);
            case "duplicate": return duplicateArray(n);
            default: throw new IllegalArgumentException(type);
        }
    }

    static int[] randomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = random.nextInt(1_000_000);
        return arr;
    }

    static int[] sortedArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        return arr;
    }

    static int[] reverseArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = n - i;
        return arr;
    }

    static int[] duplicateArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = random.nextInt(10);
        return arr;
    }

    static Point[] randomPoints(int n) {
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(random.nextDouble() * 1_000_000, random.nextDouble() * 1_000_000);
        }
        return points;
    }

    static void writeCsv(List<ExperimentResult> results, String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        writer.write("algorithm,inputType,n,depth,comparisons,recursiveCalls,timeNs\n");
        for (ExperimentResult r : results) {
            writer.write(r.algorithm + "," + r.inputType + "," + r.n + "," + r.depth + "," +
                    r.comparisons + "," + r.recursiveCalls + "," + r.time + "\n");
        }
        writer.close();
    }
}