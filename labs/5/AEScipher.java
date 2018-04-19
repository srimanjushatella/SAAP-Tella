
/**
 * file: AEScipher.java
 * author: Sri Manjusha Tella
 * course: MSCS 630
 * assignment: lab 5
 * due date: April 18th, 2018
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
 
//mix column table  
  public static final int[] mixCol2 = { 0x00, 0x02, 0x04, 0x06, 0x08, 0x0a, 0x0c, 0x0e, 0x10, 0x12, 0x14, 0x16, 0x18,
    0x1a, 0x1c, 0x1e, 0x20, 0x22, 0x24, 0x26, 0x28, 0x2a, 0x2c, 0x2e, 0x30, 0x32, 0x34, 0x36, 0x38, 0x3a, 0x3c,
    0x3e, 0x40, 0x42, 0x44, 0x46, 0x48, 0x4a, 0x4c, 0x4e, 0x50, 0x52, 0x54, 0x56, 0x58, 0x5a, 0x5c, 0x5e, 0x60,
    0x62, 0x64, 0x66, 0x68, 0x6a, 0x6c, 0x6e, 0x70, 0x72, 0x74, 0x76, 0x78, 0x7a, 0x7c, 0x7e, 0x80, 0x82, 0x84,
    0x86, 0x88, 0x8a, 0x8c, 0x8e, 0x90, 0x92, 0x94, 0x96, 0x98, 0x9a, 0x9c, 0x9e, 0xa0, 0xa2, 0xa4, 0xa6, 0xa8,
    0xaa, 0xac, 0xae, 0xb0, 0xb2, 0xb4, 0xb6, 0xb8, 0xba, 0xbc, 0xbe, 0xc0, 0xc2, 0xc4, 0xc6, 0xc8, 0xca, 0xcc,
    0xce, 0xd0, 0xd2, 0xd4, 0xd6, 0xd8, 0xda, 0xdc, 0xde, 0xe0, 0xe2, 0xe4, 0xe6, 0xe8, 0xea, 0xec, 0xee, 0xf0,
    0xf2, 0xf4, 0xf6, 0xf8, 0xfa, 0xfc, 0xfe, 0x1b, 0x19, 0x1f, 0x1d, 0x13, 0x11, 0x17, 0x15, 0x0b, 0x09, 0x0f,
    0x0d, 0x03, 0x01, 0x07, 0x05, 0x3b, 0x39, 0x3f, 0x3d, 0x33, 0x31, 0x37, 0x35, 0x2b, 0x29, 0x2f, 0x2d, 0x23,
    0x21, 0x27, 0x25, 0x5b, 0x59, 0x5f, 0x5d, 0x53, 0x51, 0x57, 0x55, 0x4b, 0x49, 0x4f, 0x4d, 0x43, 0x41, 0x47,
    0x45, 0x7b, 0x79, 0x7f, 0x7d, 0x73, 0x71, 0x77, 0x75, 0x6b, 0x69, 0x6f, 0x6d, 0x63, 0x61, 0x67, 0x65, 0x9b,
    0x99, 0x9f, 0x9d, 0x93, 0x91, 0x97, 0x95, 0x8b, 0x89, 0x8f, 0x8d, 0x83, 0x81, 0x87, 0x85, 0xbb, 0xb9, 0xbf,
    0xbd, 0xb3, 0xb1, 0xb7, 0xb5, 0xab, 0xa9, 0xaf, 0xad, 0xa3, 0xa1, 0xa7, 0xa5, 0xdb, 0xd9, 0xdf, 0xdd, 0xd3,
    0xd1, 0xd7, 0xd5, 0xcb, 0xc9, 0xcf, 0xcd, 0xc3, 0xc1, 0xc7, 0xc5, 0xfb, 0xf9, 0xff, 0xfd, 0xf3, 0xf1, 0xf7,
    0xf5, 0xeb, 0xe9, 0xef, 0xed, 0xe3, 0xe1, 0xe7, 0xe5 };
 
//mix column table
  public static final int[] mixCol3 = { 0x00, 0x03, 0x06, 0x05, 0x0c, 0x0f, 0x0a, 0x09, 0x18, 0x1b, 0x1e, 0x1d, 0x14,
    0x17, 0x12, 0x11, 0x30, 0x33, 0x36, 0x35, 0x3c, 0x3f, 0x3a, 0x39, 0x28, 0x2b, 0x2e, 0x2d, 0x24, 0x27, 0x22,
    0x21, 0x60, 0x63, 0x66, 0x65, 0x6c, 0x6f, 0x6a, 0x69, 0x78, 0x7b, 0x7e, 0x7d, 0x74, 0x77, 0x72, 0x71, 0x50,
    0x53, 0x56, 0x55, 0x5c, 0x5f, 0x5a, 0x59, 0x48, 0x4b, 0x4e, 0x4d, 0x44, 0x47, 0x42, 0x41, 0xc0, 0xc3, 0xc6,
    0xc5, 0xcc, 0xcf, 0xca, 0xc9, 0xd8, 0xdb, 0xde, 0xdd, 0xd4, 0xd7, 0xd2, 0xd1, 0xf0, 0xf3, 0xf6, 0xf5, 0xfc,
    0xff, 0xfa, 0xf9, 0xe8, 0xeb, 0xee, 0xed, 0xe4, 0xe7, 0xe2, 0xe1, 0xa0, 0xa3, 0xa6, 0xa5, 0xac, 0xaf, 0xaa,
    0xa9, 0xb8, 0xbb, 0xbe, 0xbd, 0xb4, 0xb7, 0xb2, 0xb1, 0x90, 0x93, 0x96, 0x95, 0x9c, 0x9f, 0x9a, 0x99, 0x88,
    0x8b, 0x8e, 0x8d, 0x84, 0x87, 0x82, 0x81, 0x9b, 0x98, 0x9d, 0x9e, 0x97, 0x94, 0x91, 0x92, 0x83, 0x80, 0x85,
    0x86, 0x8f, 0x8c, 0x89, 0x8a, 0xab, 0xa8, 0xad, 0xae, 0xa7, 0xa4, 0xa1, 0xa2, 0xb3, 0xb0, 0xb5, 0xb6, 0xbf,
    0xbc, 0xb9, 0xba, 0xfb, 0xf8, 0xfd, 0xfe, 0xf7, 0xf4, 0xf1, 0xf2, 0xe3, 0xe0, 0xe5, 0xe6, 0xef, 0xec, 0xe9,
    0xea, 0xcb, 0xc8, 0xcd, 0xce, 0xc7, 0xc4, 0xc1, 0xc2, 0xd3, 0xd0, 0xd5, 0xd6, 0xdf, 0xdc, 0xd9, 0xda, 0x5b,
    0x58, 0x5d, 0x5e, 0x57, 0x54, 0x51, 0x52, 0x43, 0x40, 0x45, 0x46, 0x4f, 0x4c, 0x49, 0x4a, 0x6b, 0x68, 0x6d,
    0x6e, 0x67, 0x64, 0x61, 0x62, 0x73, 0x70, 0x75, 0x76, 0x7f, 0x7c, 0x79, 0x7a, 0x3b, 0x38, 0x3d, 0x3e, 0x37,
    0x34, 0x31, 0x32, 0x23, 0x20, 0x25, 0x26, 0x2f, 0x2c, 0x29, 0x2a, 0x0b, 0x08, 0x0d, 0x0e, 0x07, 0x04, 0x01,
    0x02, 0x13, 0x10, 0x15, 0x16, 0x1f, 0x1c, 0x19, 0x1a };
  
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
  
/**
  * AESAddKey
  * 
  * This method takes two 4 * 4 input matrices and gives a output matrix
  * whose elements are XOR's of the input hex digits.
  * 
  * @param sHex - the input string of 4*4 matrix
  * @param keyHex - the input hex key which is 4 * 4
  * @return Value - A 4*4 matrix having corresponding XOR values
  */
  public static String[][] AESAddKey(String[][] sHex, String[][] keyHex) {
    String[][] inputMatrix = new String[4][4];
    for(int i = 0; i < 4; i++) {
      for(int j = 0; j < 4; j++) {
        inputMatrix[j][i] = operationXOR(sHex[j][i], keyHex[i][j]);
      }
    }
    return inputMatrix;
  }
  
/**
  * AESNiblleSub
  * 
  * This method performs the substitution operation of the AES cipher
  * @param inStateHex - A 4*4 matrix whose values are substituted
  * within the SBox
  * @return Value - The resultant matrix after substitution
  */
  public static String[][] AESNibbleSub(String[][] inStateHex) {
    String[][] sBoxMatrix = new String[4][4];
    for(int i = 0; i < 4; i++) {
      for(int j = 0; j < 4; j++) {
        sBoxMatrix[i][j] = aesSBox(inStateHex[i][j]);
      }
    }
    return sBoxMatrix;
  }
  
/**
  * AESShiftRow
  * 
  * This method performs the shift row operation of the AES cipher.
  * 
  * @param inStateHex - the matrix whose rows are to be shifted
  * @return Value - The resultant matrix after row shift operation.
  */
  public static String[][] AESShiftRow(String[][] inStateHex) {
    String[][] inputMatrix = redo(inStateHex);
    String[][] outputMatrix = new String[4][4];
    outputMatrix[0] = inputMatrix[0];
    for(int i = 1; i < 4; i++) {
      for(int j = 0; j < 4; j++) {
        outputMatrix[i][j] = inputMatrix[i][(j + i) % 4];
      }
    }
    return redo(outputMatrix);
  }
  
/**
  * redo
  * 
  * This method interchanges the values and stores them in a new array.
  * 
  * @param inputMatrix - the matrix whose values are to be interchanged.
  * @return Value - The resultant matrix.
  */  
  public static String[][] redo(String[][] inputMatrix) {
    String[][] matrix = new String[4][4];
    for(int i = 0; i < 4; i++) {
      for(int j = 0; j < 4; j++) {
        matrix[i][j] = inputMatrix[j][i];
      }
    }
    return matrix;
  }
  
/**
  * AESMixColumn
  * 
  * This method transforms the input state into output state.
  * 
  * @param inStateHex - mix column operation is to be performed 
  * on the input matrix
  * @return Value - The resultant matrix after mix column operation.
  */  
  public static String[][] AESMixColumn(String[][] inStateHex) {
    String[][] matrix = new String[4][4];
    int[] array1 = new int[4];
    int[] array2 = new int[4];
    for(int i = 0; i < 4; i++) {
      for(int j = 0; j < 4; j++) {
        array1[j] = Integer.parseInt(inStateHex[i][j], 16);
      }
      array2[0] = mixCol2[array1[0]] ^ mixCol2[array1[1]] ^ array1[2] ^ array1[3];
      array2[1] = array1[0] ^ mixCol2[array1[1]] ^ mixCol2[array1[2]] ^ array1[3];
      array2[2] = array1[0] ^ array1[1] ^ mixCol2[array1[2]] ^ mixCol2[array1[3]];
      array2[3] = mixCol2[array1[0]] ^ array1[1] ^ array1[2] ^ mixCol2[array1[3]];
       
      for (int k = 0; k < 4; k++) {
        matrix[i][k] = Integer.toHexString(array2[k]);
      }
    }
    return matrix;
  }
  
/**
  * keyMatrix
  * 
  * This method gets key and changes into an array.
  * @param keyHex - hexadecimal key
  * @param blockMatrix - size of the matrix
  * @return Value - Round keys
  */
  public static String[][] keyMatrix(String KeyHex, int blockMatrix) {
    int a = 0;
    int b = 0;
    String[][] matrixKey = new String[blockMatrix][blockMatrix];
    for (int i = 0; i < KeyHex.length(); i += 2) {
      matrixKey[a][b] = "" + KeyHex.charAt(i) + KeyHex.charAt(i + 1);
      a++;
      if (a == 4) {
        a = 0;
        b++;
      }
    }
    return matrixKey;
  }
  
/**
  * AES
  * 
  * This method implements the AES encryption algorithm.
  * @param pTextHex - the plain text that is to be converted to cipher text.
  * @param keyHex - The key values used for encryption.
  * @return Value - The cipher text string
  */
  public static String AES(String pTextHex, String keyHex) {
    int matrix = (int) Math.sqrt(pTextHex.length() / 2);
    String[][] pTextHexmatrix = redo(keyMatrix(pTextHex, matrix));
    String[][] wMatrix = doRoundKeyMatrix(keyHex, matrix);
    String[][] encrypt = new String[4][4];
    String[][] roundKey = (keyMatrix(keyHex, matrix));
    encrypt = AESAddKey(pTextHexmatrix, roundKey);
    for(int i = 1; i < 11; i++) {
      for(int j = 0; j < 4; j++) {
        for(int k = 0; k < 4; k++) {
          roundKey[k][j] = wMatrix[k][(i *4) + j];
        }  
      }
      encrypt = AESNibbleSub(encrypt);
      encrypt = AESShiftRow(encrypt);
      if (i < 10)
        encrypt = AESMixColumn(encrypt);
        encrypt = AESAddKey(encrypt, roundKey);
    }
    StringBuilder stringCipher = new StringBuilder();
    for(String[] i : encrypt) {
      for(String j : i) {
        if (j.length() == 1)
          j = "0" + j;
          stringCipher.append(j);
      }
    }
    return stringCipher.toString();
  }
  
/**
  * doRoundKeyMatrix
  * 
  * This method converts the key value into 11 round keys and stores them
  * in an array.
  * 
  * @param KeyHex - the hexadecimal key
  * @param blockMatrix - size of the matrix
  * @return Value - The resulting round keys
  */
  private static String[][] doRoundKeyMatrix(String KeyHex, int blockMatrix) {
    int a = 0;
    int b = 0;
    String[][] matrixKey = new String[blockMatrix][blockMatrix];
    for (int i = 0; i < KeyHex.length(); i += 2) {
      matrixKey[a][b] = "" + KeyHex.charAt(i) + KeyHex.charAt(i + 1);
      a++;
      if (a == 4) {
        a = 0;
        b++;
      }
    }
    String allRoundsMatrix[][] = new String[4][11 * 4];
    for (int roundzerorow = 0; roundzerorow < 4; roundzerorow++) {
      System.arraycopy(matrixKey[roundzerorow], 0, allRoundsMatrix[roundzerorow], 0, 4);
    }
    for (int i = 4; i < 44; i++) {
      if (i % 4 != 0) {
        for (int row = 0; row < 4; row++) {
          allRoundsMatrix[row][i] = operationXOR(allRoundsMatrix[row][i - 4], allRoundsMatrix[row][i - 1]);
        }
      } else {
        String[] arrayVector = new String[4];
        for (int arrayRow = 0; arrayRow < 4; arrayRow++) {
          arrayVector[((arrayRow - 1) + 4) % 4] = aesSBox(allRoundsMatrix[arrayRow][i - 1]);
        }
        String roundCon = aesRcon(i);
        arrayVector[0] = operationXOR(roundCon, arrayVector[0]);
        for (int row = 0; row < 4; row++) {
          allRoundsMatrix[row][i] = operationXOR(allRoundsMatrix[row][i - 4], arrayVector[row]);
        }
      }
    }
    return allRoundsMatrix;
  }
}
