
import java.util.Scanner;

 /**
   * file: Encrypt.java
   * author: Sri Manjusha Tella
   * course: MSCS 630
   * assignment: lab 1
   * due date: January 24, 2018
   *
   * This file encrypts an input string given in alphabets 
   * and transforms it to integers.
   */

public class Encrypt {
  //constants thatdefine the ASCII character set. 
  static final int ASCII_UPPERCASE  = 65;
  static final int ASCII_SPACE = 32;

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    while (input.hasNext()) {
      String plainText = input.next();
      plainText = plainText.toUpperCase();
      int[] output = str2int(plainText);
      for (int i = 0; i < output.length; i++) {
        System.out.println(output[i] + " ");
          }
    System.out.println();
    }
  }
    
 /**
  * str2int
  * 
  * This function encodes the string of plaint text
  * to their corresponding integer values.
  * 
  * @param plainText : the String to be encoded.
  * @return value : the list of corresponding integer values.
  */
  public static int[] str2int(String plainText) {
    int[] result = new int[plainText.length()];
    plainText = plainText.toUpperCase();
    for (int i = 0; i < plainText.length(); i++) {
      char text = plainText.charAt(i);
      result[i] = ((int) text) - ASCII_UPPERCASE;
      if (text == ASCII_SPACE) {
        result[i] = 26;
      }
    }
    return result;
  }
}
