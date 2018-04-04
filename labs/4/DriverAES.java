/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labs;

import java.util.Scanner;

/**
 * file: DriverAES.java
 * author: Sri Manjusha Tella
 * course: MSCS 630
 * assignment: lab 4
 * due date: April 4th, 2018
 * This class tests the implementation of the AEScipher
 * by calling aesRoundKeys() method.
 * @author TSM
 */
public class DriverAES {
  public static void main (String[] args) {
    Scanner input = new Scanner(System.in);
    while (input.hasNext()) {
      String KeyHex = input.nextLine();
      String[] str = AEScipher.aesRoundKeys(KeyHex);
      System.out.println("keys \n");
        for (String s : str) {
          System.out.print(s + "\n");
        }
        System.out.println();
    }     
  }
}
