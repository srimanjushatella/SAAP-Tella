import java.util.Scanner;

/**
  * file: Matrixdet.java
  * author: Sri Manjusha Tella
  * course: MSCS 630
  * assignment: lab 3 part 1
  * due date: February 28th, 2018
  *
  * This file determines the determinant of a matrix.
  */
 
public class Matrixdet {
  public static void main (String[] args) {
  Scanner input = new Scanner(System.in);
  System.out.println("enter the mod");
  int mod = input.nextInt();
  System.out.println("enter the size of the matrix");
  int size = input.nextInt();
  System.out.println("enter the elements of the matrix");
  int[][] A = new int[size][size];
  for (int i = 0; i < size; i++) {
    for (int j = 0; j < size; j++) {
      A[i][j] = input.nextInt();
    }
  }
  int determinant = cofModDet(mod, A);
  System.out.println(determinant);
  }
 
/**
 * This function calculates the determinant of the given matrix
 * and computes the result in terms of modulo.
 * @param mod: The modulo value
 * @param A: The matrix array to calculate the determinant of
 * @return - The determinant of matrix A in terms of modulo m.
 */
  public static int cofModDet(int mod, int[][] A) {
    int det = 0;
    if (A.length == 1) {
      det = A[0][0];
    }
    else if (A.length == 2) {
      det = A[0][0]*A[1][1] - A[0][1]*A[1][0];
    }
    else {
      int size = A.length - 1;
      for (int k = 0; k < A.length; k++) {
        int[][] temp = new int[size][size];
        for (int i = 1; i < size; i++) {
          for (int j = 0; j < size; j++) {
            if (j < k) {
              temp[i-1][j] = A[i][j];
            } else if (j > k) {
                temp[i - 1][j - 1] = A[i][j];
              }
          }
        }             
        det += (A[0][k] * Math.pow(-1, k) * cofModDet(mod, temp));
      }
    }
    return det % mod;
  }
}  
