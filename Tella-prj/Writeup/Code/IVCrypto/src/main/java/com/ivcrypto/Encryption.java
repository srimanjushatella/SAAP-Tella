package com.ivcrypto;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

    public static byte[] Encrypt(String file_Path, String password) throws Exception{

        final String encryptedPassword = password;

        File file = new File(file_Path);
        int file_Length = (int) file.length();
        byte[] bytes = new byte[file_Length];

        FileInputStream fis = new FileInputStream(file);
        try {
            fis.read(bytes);
        } finally {
            fis.close();
        }

        byte[] file_Contents = bytes;
        byte[] key  = password.getBytes("UTF-8");

        // Hashing the key
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(key);
        SecretKeySpec secretKey = new SecretKeySpec(md.digest(), "AES");

        // Generating intializing vector
        int Size = 16;
        byte[] vector = new byte[Size];
        SecureRandom rand = new SecureRandom();
        rand.nextBytes(vector);
        IvParameterSpec vectorSpec = new IvParameterSpec(vector);


        // Encrypt
        Cipher encryptCipher = Cipher.getInstance("AES_256/CBC/PKCS7Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, vectorSpec);
        byte[] encrypted = encryptCipher.doFinal(file_Contents);
        byte[] vectorCipher = new byte[Size+encrypted.length];
        System.arraycopy(vector,0,vectorCipher,0,Size);
        System.arraycopy(encrypted,0,vectorCipher,Size,encrypted.length);

        return vectorCipher;
    }

    public static byte[] Decrypt(String file_Path, String password) throws Exception {

        final String encryptedPassword = password;

        File file = new File(file_Path);
        int file_Length = (int) file.length();
        byte[] bytes = new byte[file_Length];

        FileInputStream fis = new FileInputStream(file);
        try {
            fis.read(bytes);
        } finally {
            fis.close();
        }

        byte[] file_Contents = bytes;
        byte[] key  = password.getBytes("UTF-8");

        int Size = 16;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(key);
        SecretKeySpec secretKey = new SecretKeySpec(md.digest(), "AES");

        byte[] vector = new byte[Size];
        System.arraycopy(file_Contents,0,vector,0,vector.length);
        IvParameterSpec vectorSpec = new IvParameterSpec(vector);


        int cipherTextSize = file_Contents.length - Size;
        byte[] cipher = new byte[cipherTextSize];
        System.arraycopy(file_Contents,Size,cipher,0,cipherTextSize);
        Cipher decryptedCipher = Cipher.getInstance("AES_256/CBC/PKCS7Padding");
        decryptedCipher.init(Cipher.DECRYPT_MODE,secretKey,vectorSpec);
        byte[] decrypt = decryptedCipher.doFinal(cipher);

        return decrypt;
    }


}
