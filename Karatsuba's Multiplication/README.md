## Abstract
The naive way of multiplying two numbers together taught in elementary school serves well for calculating the product by hand using pen and paper, but can become slow very quickly when replicated on a computer with large digit numbers. Computer scientists have developed many new methods to create a faster and more efficient way for multiplication. One such method is Karatsuba's algorithm. The algorithm can be described as follows:

1. Seperate the two multiplicands' digits in half and set them equal to 4 new variables, a, b, c, d.
2. Recursively compute ac.
3. Recursively compute bd.
4. Recursively compute (a+b)(c+d).
5. Compute (a+b)(c+d) - bd - ac = ad + bc.
6. Pad ac with n or n/2 0's (where n is the maximum number of digits between both multiplicands) if n is even or odd, respectively. Pad (ad + bc) with n/2 or (n+1)/2 0's if n is even or odd, respectively. 
7. Compute ac + bd + (ad + bc) with their respective padded 0s.

The algorithm has a run time complexity of O(n^lg 3) = O (n^1.585), as opposed to the naive algorithm's O(n^2) run time complexity.

## API

**public static long karatsubaAlgo (long x, long y)** - Karatsuba's algorithm that takes two multiplicands as input parameters and returns the product.

**public static int length(long n)** - Returns the number of digits for a long value. 

**public static void main(String[] args)** - Main method for test purposes.

## Potential Improvements
The current version of the algorithm cannot handle values beyond the long data type limitations (2^64 - 1).

