Assignment 1 - Divide and Conquer
Overview
This project have 4 algorithms: MergeSort, QuickSort, Deterministic Select (median of medians) and Closest Pair of Points. We implement them, test them and measure time/depth/comparisons.
Algorithms
MergeSort - splits array in half, sorts both halfs, merges them back with a buffer array. Complexity Θ(n log n). Recurrence T(n) = 2T(n/2) + n.
QuickSort - picks random pivot, partitions array around it, recurse into smaller side and loop for bigger side so depth stays small. Best/avg case Θ(n log n), worst case O(n^2) if pivot is bad.
Deterministic Select - like quickselect but pivot is chosen using median of medians (groups of 5) so its guaranteed good pivot. This makes it Θ(n) worst case instead of O(n^2). Recurrence is T(n) = T(n/5) + T(7n/10) + n, since n/5 + 7n/10 is less then n it stays linear.
Closest Pair - sort points by x, split in half recursivly, find closest pair in each half, then check a strip of points near the middle line for closer pairs. Θ(n log n) because strip check is fast.
Results
Tested with n = 100, 1000, 10000, 100000 and 4 input types: random, sorted, reverse, duplicate.
Some numbers from n=100000:
Algorithm	Input	Depth	Comparisons	Time(ms)
MergeSort	random	14	1,498,529	91.4
MergeSort	sorted	14	703,056	3.1
QuickSort	random	37	1,961,834	7.3
QuickSort	duplicate	10105	500,378,930	307.6
Select	random	-	642,143	3.6
Select	duplicate	-	10,598,945,033	23290.7
Full data is in results/results.csv. Plots (time vs n and depth vs n) are in docs/plots folder.
Discussion
Mostly matches theory. MergeSort depth grows like log(n) no matter what input, so thats Θ(n log n) confirmed. QuickSort also stays around log(n) depth for random/sorted/reverse.
BUT for duplicate input, QuickSort and Select both blow up bad. QuickSort depth goes to 10000+ and Select does like 10 billion comparisons and takes 23 seconds. This is because the partition function uses strict < so if alot of elements are equal to pivot they all go on the same side, making unbalanced partitions. Basically same reason QuickSort worst case is O(n^2) - happens here because duplicate array only has 10 different values. A 3-way partition would probably fix this but didnt have time to do it.
Why smaller-first recursion helps QuickSort: it keeps recursion depth O(log n) even when split is bad, since you only recurse into the smaller half.
Why Median of medians is O(n): pivot always removes atleast ~30% of array each time cuz of how median of medians works, so recursion shrinks fast enough that total work stays linear.
Why closest pair beats O(n^2): brute force checks every pair, this algorithm only checks a small strip near the middle line instead of all points, so its n log n not n^2.
Practical factors: MergeSort random at n=100000 took way longer (91ms) then the other input types at same n (3-6ms) even tho comparisons were similar - probably JVM warm up since it was maybe the first heavy run and JIT hasnt kicked in yet. Only did 1 trial so there could be more noise like this we didnt catch.
Reflection
(write your own paragraph here about what you learned / what was hard - dont forget this part)
Screenshots
(add screenshots here - program output, tests passing, plots)
