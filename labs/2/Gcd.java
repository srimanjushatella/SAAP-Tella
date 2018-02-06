
import java.util.Scanner;

/**
  * file: Gcd.java
  * author: Sri Manjusha Tella
  * course: MSCS 630
  * assignment: lab 2
  * due date: February 7th, 2018
  *
  * This file determines the greatest common divider of two numbers
  * using the division algorithm.
  */
public class Gcd {
	
  public static void main(String[] args) {
	  
  Scanner input = new Scanner(System.in);
  System.out.println("enter the two numbers");
  long a = input.nextLong();
  long b = input.nextLong();
  long result = euclidAlg(a,b);
  System.out.println("gcd is " +result);
  
 }
 
 /**
  * This method implements the Euclidian algorithm
  * @param a - Dividend    
  * @param b - Divisor   
  * @return     
  */
  public static long euclidAlg(long a, long b) {
  /** The Division Algorithm 
   a = qd + r (here d refers to b in our method)
   q = floor(a/b)
   r = a - qd
  */
  // condition to check (a >= b) or else swap them.
    if (a < b) {
      long temp = a;
      a = b;
      b = temp;
    }
    while (b != 0) {
      long q1 = Math.floorDiv(a, b);
      long r1 = a - (q1 * b);  
      a = b ;
      b = r1;
    }
  return a;
        
  }
}
