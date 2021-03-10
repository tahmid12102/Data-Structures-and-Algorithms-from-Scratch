import java.lang.*;
import java.util.*;
import java.io.*;

public class CountingInversions {

    //Algorithm counts number of inversions using merge operation.
    private static long sortAndCount(int [] arr, int first, int last){
        
        //Total number of inversions.
        long inversions = 0;

        if (first < last) {
            //Compute middle value of array.
            int mid = (last+first)/2;

            /*The total number of inversions = # of inversions in left subarray +  
                # of inversions in right subarray + # of inversions in merge operation of entire array*/
            inversions += sortAndCount(arr, first, mid);
            inversions += sortAndCount(arr, mid+1, last);
            inversions += countSplitInv(arr, first, mid, last);
        }

        return inversions;
    }

    //Merge operation with inversion counting
    private static long countSplitInv(int [] arr, int first, int mid, int last) {

        //Create left and right subarrays of original array.
        int [] leftArr = Arrays.copyOfRange(arr, first, mid+1); //+1 since second index is exclusive.
        int [] rightArr = Arrays.copyOfRange(arr,mid+1, last+1);

        //Number of inversions.
        long inversions = 0;

        //Loop iterator variables outside of loop.
        int i = 0, j = 0, k=first;

        while (i < leftArr.length && j < rightArr.length){
            if (leftArr[i] <= rightArr[j]){
                arr[k] = leftArr[i];
                i++; k++;
            } else {
                arr[k] = rightArr[j];
                j++; k++; 
                inversions += (mid + 1) - (first + i);
            }
        }

        while (i < leftArr.length){
            arr[k] = leftArr[i];
            i++; k++;
        }
        while (j < rightArr.length){
            arr[k] = rightArr[j];
            j++; k++;
        }

        return inversions;
    }

    //Main function to read file, store values in array, and calculate inversions using sortAndCount.
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(new BufferedReader(new FileReader("IntegerArray.txt")));
        int myArray[]= new int[100000];
        while(sc.hasNextLine()) {
           for (int i=0; i<myArray.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                myArray[i] = Integer.parseInt(line[0]);
              }
           }

        System.out.println("The input array is: ");
        for (int element: myArray){
            System.out.print(element + " ");
        }

        System.out.println("\n Number of inversions is: " + sortAndCount(myArray, 0,myArray.length-1));

    }
}