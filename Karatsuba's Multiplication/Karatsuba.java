import java.lang.*;
import java.util.*;

//Improvements: Handle integers beyond long's limit 2^64 -1.

class Karatsuba {

    //Recursive algorithm method.
    public static long karatsubaAlgo (long x, long y) {
        
        int maxLength;

        //Get maximum number length between x and y.
        if (length(x) < length(y)){maxLength = length(y);}
        else {maxLength = length(x);}

        //Base case
        if (maxLength <= 1){
            return x * y;
        }
        

        //Divide maxLength by 2 and add 1 if odd.
        maxLength = (maxLength / 2) + (maxLength % 2);

        //Create multiplier by taking 10^(maxLength) to pad ad_bc with 0's
        long multiplier = (long)Math.pow(10, (maxLength));

        //Compute a,b,c,d by using multiplier. 
        // a = x / multiplier, ignoring remainder.
        // b = x % multplier , receiving remainder
        // c = y / multiplier, ignoring remainder.
        // d = y % multiplier, receiving remainder.
        long a = x / multiplier;
        long b = x % multiplier;
        long c = y / multiplier;
        long d = y % multiplier;

        //Compute ac, bd, and (a+b)(c+d) recursively.
        long ac = karatsubaAlgo(a,c);
        long bd = karatsubaAlgo(b,d);
        long ac_ad_bc_bd = karatsubaAlgo(a+b,c+d);
        long ad_bc = ac_ad_bc_bd - ac - bd;

        //Return final result.
        return (ac * (long)Math.pow(10, maxLength *2)) + bd + (ad_bc * multiplier);
    }

    //Method returns length of digit using repeated multiplication.
    //Simple and faster than converting to string or using logarithmic approach.
    public static int length(long n) {
        int length = 0;
        long temp = 1;
        while (temp < n) {
            length++;
            temp *=10;
        }
        return length;
    }
    
    //Main function for test cases.
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter two values to multiply: "); 
        long x = Long.parseLong(sc.nextLine());
        long y = Long.parseLong(sc.nextLine());
        System.out.println("The product between the two is: " + karatsubaAlgo(x,y));
    }
}