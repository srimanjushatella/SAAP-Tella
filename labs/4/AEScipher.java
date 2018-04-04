/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labs;

/**
 * file: AEScipher.java
 * author: Sri Manjusha Tella
 * course: MSCS 630
 * assignment: lab 4
 * due date: April 4th, 2018
 * @author TSM
 */
public class AEScipher {
    
//SBox
  public static String[][] SBox = {
    {"63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76"},
    {"CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0"},
    {"B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15"},
    {"04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75"},
    {"09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84"},
    {"53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF"},
    {"D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8"},
    {"51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2"},
    {"CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73"},
    {"60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB"},
    {"E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79"},
    {"E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08"},
    {"BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A"},
    {"70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E"},
    {"E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF"},
    {"8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16"} 
  };
    
//RCon
  public static String[][] RCon = {
    {"8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A"},
    {"2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39"},
    {"72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A"},
    {"74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36", "6C", "D8"},
    {"AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF"},
    {"C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94", "33", "66", "CC"},
    {"83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B"},
    {"36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3"},
    {"7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94"},
    {"33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20"},
    {"40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35"},
    {"6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F"},
    {"25", "4A", "94", "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04"},
    {"08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63"},
    {"C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD"},
    {"61", "C2", "9F", "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D"} 
  };
 
/**
  * aesRoundKeys
  * 
  * This method receives a key of size 16 and returns 11 rounds of keys.
  * @param KeyHex - the 16 length hex string key.
  * @return Value: An array containing all round keys.
  */
  public static String[] aesRoundKeys(String KeyHex) {
    int blockMatrix = (int) Math.sqrt(KeyHex.length()/2);
    String[][] kMatrix = new String[blockMatrix][blockMatrix];
    int row = 0;
    int column = 0;
      for (int i = 0; i < KeyHex.length(); i += 2) {
        kMatrix[row][column] = "" + KeyHex.charAt(i) + KeyHex.charAt(i+1);
        row++;
        if(row == 4) {
          row = 0;
          column++;
        }
      }
    String[][] wMatrix = new String[4][11*4];
      for (int round = 0; round < 4; round++) {
        System.arraycopy(kMatrix[round], 0, wMatrix[round], 0, 4);
      }
      for(int wcol = 4; wcol < 44; wcol++ ) {
        if(wcol % 4 != 0) {
          for(int wrow = 0; wrow < 4; wrow ++) {
            wMatrix[wrow][wcol] = operationXOR(wMatrix[wrow][wcol-4], wMatrix[wrow][wcol-1]);
          }
        } else {
            String[] wNew = new String[4];
            for(int newrow = 0; newrow < 4; newrow++) {
              wNew[((newrow-1) + 4) % 4] = aesSBox(wMatrix[newrow][wcol-1]);                    
            }   
            String rconValue = aesRcon(wcol);
            wNew[0] = operationXOR(rconValue, wNew[0]);
            for(int wrow = 0; wrow < 4; wrow++) {
              wMatrix[wrow][wcol] = operationXOR(wMatrix[wrow][wcol-4], wNew[wrow]);
            }
          }
        }
        String output[] = keyArray(wMatrix);       
        return output;
    }
    
/**
  * keyArray 
  * This method converts a 2D array containing the round key values
  * and adds each key to a new array of size 11.
  * 
  * @param arrayKey - the 2D array containing the new values, which are 
  * to be transferred.
  *   
  * @return Value - The array of all the round keys.
  */
  public static String[] keyArray (String[][] arrayKey) {
    String roundkeys = "";
    String[] Key = new String [11];
    int b = 0;
      for(int i = 0; i < 11; i++) {
        for(int j = (i*4); j < (i*4) + 4; j++) {
          for(int k = 0; k < 4; k++) {
            String formatvalue = arrayKey[k][j].toUpperCase();
              if(formatvalue.length() == 1)
                formatvalue = "0" +formatvalue;
              roundkeys += formatvalue;
          }
        }    
        Key[i] = roundkeys.substring(b);   
        b=b+32;
      }
    return Key;
  }
/**
  * aesSBox 
  * 
  * This method to refers to the SBox table.
  * 
  * @Param inHex - String used for lookup in the SBox table.
  * @return value - The corresponding SBox value. 
  */
  public static String aesSBox(String inHex) {
    int rowIndex, columnIndex;
    String str= String.format("%2s", inHex).replace(' ', '0');
    rowIndex = Integer.parseInt(""+str.charAt(0), 16);
    columnIndex = Integer.parseInt(""+str.charAt(1), 16);
    return SBox[rowIndex][columnIndex];
  }

/**
  * aesRCon 
  * 
  * This method to refers to the RCon table.
  * 
  * @Param round - The round undergoing transfer is looked up.
  * @return value - The corresponding RCon value. 
  */
  public static String aesRcon(int round) {
    return RCon[0][round / 4];
  }

/**
  * operationXOR
  * 
  * This method represents the exclusive or (XOR) operation.
  * @param num1 - the first hex number
  * @param num2 - the second hex number 
  * @return Value - The resultant hex value
  */
  public static String operationXOR(String num1, String num2) {
    int value1 = Integer.parseInt(num1, 16);
    int value2 = Integer.parseInt(num2, 16);
    int xorresult =  value1 ^ value2;
    String hexVal = Integer.toHexString(xorresult);
    return hexVal;     
  } 
}  
