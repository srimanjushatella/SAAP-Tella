

import java.util.Scanner;

/**
 * file: Driver.java
 * author: Sri Manjusha Tella
 * course: MSCS 630
 * assignment: lab 5
 * due date: April 18th, 2018
 * This class tests the implementation of the AEScipher
 * @author TSM
 */
public class Driver {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        String KeyHex = input.nextLine().toUpperCase();
        String pTextHex = input.nextLine().toUpperCase();
        System.out.println(AEScipher.AES(pTextHex, KeyHex).toUpperCase());
    }
}
