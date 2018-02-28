import java.util.Scanner;

/**
  * file: HexMatConverter.java
  * author: Sri Manjusha Tella
  * course: MSCS 630
  * assignment: lab 3 part 2
  * due date: February 28th, 2018
  *
  * This file gives a padding matrix which contains the corresponding ASCII
  * values in a 4 * 4 matrix representation.
  */
public class HexMatConverter {
  public static void main (String[] args) {
    Scanner input = new Scanner(System.in);
    char s = input.nextLine().charAt(0);
    String plaintext = input.nextLine();
    int strlen = plaintext.length();
    int cal = (int) Math.ceil((double) strlen / 16);
    int value = 16 * cal;
    int padding = value - strlen;
    String newPlaintext = plaintext;
    if(padding <= 0) { }
    else {
      for(int i = 0; i < padding; i++)
      newPlaintext += s;
    }
      for (int i =0; i < newPlaintext.length(); i += 16) {
        String str = newPlaintext.substring(i, i + 16);
        int[][] matrix = getHexMatP(s, str);
        for( int j = 0; j < 4; j++) {
          System.out.println(decToHex(matrix[j][0])+ " " + decToHex(matrix[j][1]) + " " + decToHex(matrix[j][2])+ " " + decToHex(matrix[j][3]));
        }
      System.out.println();
      }       
    }  
	
    /**
      * This function takes an integer value and returns its hexadecimal value.
      * @param n: integer value
      * @return - A string value which represents the hexadecimal value of n. 
      */
    public static String decToHex(int n) {
      return Integer.toHexString(n).toUpperCase();
    }
	
    /**
    * This function takes two inputs, a substitution character s and a string
    * of plain text of length <= 16 and returns a matrix of size 4 * 4 with the 
    * corresponding plaint text ASCII values.
    * @param s: substitution character
    * @param p: String of Plaintext
    * @return - A 4 * 4 matrix of ASCII value for String p.
    */
    public static int[][] getHexMatP(char s, String p) {
      if(p.length() < 16) {
        for (int k =0; k < 16 - p.length(); k++) {
          p = p + s;
        }
      }
      int index = 0;
      int[][] A = new int[4][4];
      for (int i = 0; i < 4; i++){
        for (int j = 0; j < 4; j++) {
          A[j][i] = (int) p.charAt(index);
          index++;
        }
      }
    return A;    
  }
}
