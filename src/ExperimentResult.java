public class ExperimentResult {
    String algorithm;
    String inputType;
    int n;
    int depth;
    long comparisons;
    long recursiveCalls;
    long time;

    ExperimentResult(String algorithm, String inputType, int n, int depth,
                     long comparisons, long recursiveCalls, long time) {
        this.algorithm = algorithm;
        this.inputType = inputType;
        this.n = n;
        this.depth = depth;
        this.comparisons = comparisons;
        this.recursiveCalls = recursiveCalls;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Algorithm: " + algorithm +
                "\nInput Type: " + inputType +
                "\nn: " + n +
                "\nDepth: " + depth +
                "\nComparisons: " + comparisons +
                "\nRecursive Calls: " + recursiveCalls +
                "\nTime: " + time + " ns";
    }
}