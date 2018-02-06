
import java.util.Scanner;

/**
  * file: GcdExtended.java
  * author: Sri Manjusha Tella
  * course: MSCS 630
  * assignment: lab 2
  * due date: February 7th, 2018
  *
  * This file implements the extended Euclidean algorithm.
  */
public class GcdExtended {
    
  public static void main(String[] args) {
  Scanner input = new Scanner(System.in);
  System.out.println("enter the two numbers");
  long a = input.nextLong();
  long b = input.nextLong();
  long[] result = euclidAlgExt(a, b);

  for (int i = 0; i < result.length; i++) {
    System.out.print(result[i] + " ");
    }
  System.out.println();
  }

/**
  * This function implements the extended Euclidian algorithm.
  * This algorithm uses vector math.
  * @param a - Dividend
  * @param b - Divisor 
  * @return - An array of gcd, a and b.
  */
  static long[] euclidAlgExt(long a, long b) {  
    long[] U = {a, 1, 0};
    long[] V = {b, 0, 1};
    long[] W = new long[3];
	
    while (V[0] > 0) {
	  long tmp = U[0] / V[0];
	  for (int k = 0; k < W.length; k++) {
        W[k] = U[k] - (tmp*V[k]);
      }
      U = V.clone();
      V = W.clone();
    }
    return U;
  }  
}
