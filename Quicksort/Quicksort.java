import java.lang.*;
import java.util.*;
import java.io.*;

public class Quicksort {

    //Input array is partitioned with given pivot value and returns the index of the pivot after partitioning.
    private static int partition(int [] arr, int left, int right, int pivot){

        int i = left + 1;

        for (int j = left + 1; j <= right; j++){
            if (arr[j] < pivot) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                i++;
            }
        }
        arr[left] = arr[i-1];
        arr[i-1] = pivot;
        return i-1;
    }

    /*Quicksort sorting method. Partitions array using partition method. Then array is recursively sorted 
    on left and right side of pivot. Total number of comparisons is returned.*/
    private static int sort(int [] arr, int left, int right){
        int length = right - left +1;
        int comparisons = length-1;
        if (length < 2){
            return 0;
        } else {
            //pivotIndex is chosen using choosePivotIndex method.
            int pivotIndex = choosePivotIndex(arr, left, right, length/2);

            //Swap pivot with first element
            int temp = arr[left];
            arr[left] = arr[pivotIndex];
            arr[pivotIndex] = temp;

            //Partition array, then recursively sort left and right side of pivot.
            int newPivotIndex = partition(arr, left, right, (int)arr[left]);
            comparisons += sort(arr, left, newPivotIndex-1);
            comparisons += sort(arr, newPivotIndex+1, right);
        }
        return comparisons;

    }

    //Pivot is chosen using "median-of-three" rule. Index of median between first, last, and middle values is returned.
    private static int choosePivotIndex(int [] arr, int left, int right, int mid){
        if (arr[left] < arr[mid] && arr[left] > arr[right]) {
            return left;
        } else if (arr[left] > arr[mid] && arr[left] < arr[right]){
            return left;
        } else if (arr[right] < arr[mid] && arr[right] > arr[left]) {
            return right;
        } else if (arr[right] > arr[mid] && arr[right] < arr[left]) {
            return right;
        } else {
            return mid;
        }

    }

    /*Main function reads text file and stores in array. Array is sorted via sort method. Total number
    of comparisons and sorted array is printed to command line.*/
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(new BufferedReader(new FileReader("QuickSort.txt")));
        int myArray[]= new int[10000];
        while(sc.hasNextLine()) {
           for (int i=0; i<myArray.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                myArray[i] = Integer.parseInt(line[0]);
              }
           }

        System.out.println("\n Number of comparisons is: " + sort(myArray, 0,myArray.length-1));
        
        System.out.println ("The sorted array is: ");
        for (int i = 0; i < myArray.length; i++){
            System.out.print (myArray[i] + "  ");
        }

    }
}