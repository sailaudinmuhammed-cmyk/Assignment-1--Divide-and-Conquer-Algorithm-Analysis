import java.util.Arrays;
import java.util.Comparator;

public class ClosestPairSolver {
    private long distanceCalculations;
    private long recursiveCalls;
    private int maxDepth;

    public double findClosestPair(Point[] points) {
        distanceCalculations = 0;
        recursiveCalls = 0;
        maxDepth = 0;

        Point[] byX = Arrays.copyOf(points, points.length);
        Arrays.sort(byX, Comparator.comparingDouble(p -> p.x));

        return closestHelper(byX, 0, byX.length - 1, 0);
    }

    public long getDistanceCalculations() { return distanceCalculations; }
    public long getRecursiveCalls() { return recursiveCalls; }
    public int getMaxDepth() { return maxDepth; }

    private double closestHelper(Point[] byX, int lo, int hi, int depth) {
        recursiveCalls++;
        maxDepth = Math.max(maxDepth, depth);

        int n = hi - lo + 1;
        if (n <= 3) {
            return bruteForce(byX, lo, hi);
        }

        int mid = lo + (hi - lo) / 2;
        double midX = byX[mid].x;

        double leftMin = closestHelper(byX, lo, mid, depth + 1);
        double rightMin = closestHelper(byX, mid + 1, hi, depth + 1);
        double delta = Math.min(leftMin, rightMin);

        Point[] strip = buildStrip(byX, lo, hi, midX, delta);

        double stripMin = stripClosest(strip, delta);
        return Math.min(delta, stripMin);
    }

    private Point[] buildStrip(Point[] byX, int lo, int hi, double midX, double delta) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (Math.abs(byX[i].x - midX) < delta) count++;
        }
        Point[] strip = new Point[count];
        int idx = 0;
        for (int i = lo; i <= hi; i++) {
            if (Math.abs(byX[i].x - midX) < delta) strip[idx++] = byX[i];
        }
        Arrays.sort(strip, Comparator.comparingDouble(p -> p.y));
        return strip;
    }

    private double stripClosest(Point[] strip, double delta) {
        double min = delta;
        for (int i = 0; i < strip.length; i++) {
            // only need to check next few points (bounded by geometry, typically < 7)
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                distanceCalculations++;
                double d = strip[i].distanceTo(strip[j]);
                if (d < min) min = d;
            }
        }
        return min;
    }

    private double bruteForce(Point[] byX, int lo, int hi) {
        double min = Double.MAX_VALUE;
        for (int i = lo; i <= hi; i++) {
            for (int j = i + 1; j <= hi; j++) {
                distanceCalculations++;
                double d = byX[i].distanceTo(byX[j]);
                if (d < min) min = d;
            }
        }
        return min;
    }

    public static double bruteForceFull(Point[] points) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double d = points[i].distanceTo(points[j]);
                if (d < min) min = d;
            }
        }
        return min;
    }
}