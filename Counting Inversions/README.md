### Abstract

The inversion problem takes an input array, A, of size n in some arbitrary order and outputs the number of inversions, equal to the number of pairs (i,j) of array indices such that i < j and A[i] > A[j]. The straightforward naive algorithm takes n(n-1) = O(n^2) time complexity. A more clever algorithm takes the left and right subarray recursively, and uses mergesort to count the number of inversions. Every time the merge function copies a value from the right subarray as opposed to the left subarray, it shows that A[i] > A[j], indicating an inversion. Therefore, the algorithm reduces the running time complexity to O(n lg n). The motivation to such an algorithm allows numerical similarity measure between two ranked lists. One such application is the use of one's search history compared to others' to recommend new products i.e collaborative filtering. 

### API

**private static long sortAndCount(int [] arr, int first, int last)** - Returns the number of inversions by recursively calling itself until array size is 1. Then it calls the countSplitInv method to merge and count the number of inversions.

**private static long countSplitInv(int [] arr, int first, int mid, int last)** - Splits input array to left and right subarrays, sorts by merging, and returns number of inversions in the process.

