## Abstract
If you were to ask most computer scientists what they believe to be one of the greatist algorithms of all time, many will say, or at least consider, QuickSort. QuickSort is superior to MergeSort, in that it does not require any extra memory. The algorithm is performed in-place. On average, the running time is O (n lg n), just like MergeSort. What makes this algorithm so beautiful is it's simplicity and elegance. It is simple yet so effective, most wish they came up with it themselves. The algorithm involves partitioning around a pivot element where the array is rearranged such that the left of the pivot has values all less than it, and the right of the pivot has values all greater than it. This puts the pivot value in its rightful sorted position. This is recursively done to the subarray left and right of the pivot until the entire array is sorted. The key to allowing the algorithm to run at O(n lg n) on average is selecting the pivot correctly. Consider a sorted array where the first or last element is selected at the pivot for each recursive call. This would result in one recursive call on the left or right side of the pivot with size n-1. The other right or left side of the pivot, respectively,  would be empty, thus the recursive call simply returns immediately. This would result in a run time of O(n^2), an undesirable result. Thus, we need to choose a pivot such that we always get a 25-75 split or better, resulting in an O(n lg n) run time. There are two main methods commonly used:

1. Random pivots, or
2. Median-of-three rule.

In this implementation, we use the median of three rule, where we take the index of the median between the first, last, and middle values as our pivot. This will result in our desired O(n lg n) average run time. This program uses QuickSort to sort an array, print the total number of comparisons made and the sorted array to the command line.

## API

**private static int partition(int [] arr, int left, int right, int pivot)** - Partitions input array with given input value and returns the index of the pivot after partitioning.

**private static int sort(int [] arr, int left, int right)** - Main QuickSort sorting method. The method selects the pivot using choosePivotIndex method, paritions the array using partition method, then recursively sorts the left and right side of the pivot. The total number of comparisons is returned.

**private static int choosePivotIndex(int [] arr, int left, int right, int mid)** - Method chooses pivot using the median-of-three rule as described in the abstract.

**public static void main(String[] args)** - Main function reads "QuickSort.txt" and stores values in an array. The array is sorted using the sort method and the total number of comparisons and the sorted array is printed to the command line.

## Improvements

The current implementation assumes there are no repeats in the array. A quick fix can be made to handle repeats by swapping values to the left partitoned side if the values are less than or equal to the pivot. This will result in an unstable algorithm. 