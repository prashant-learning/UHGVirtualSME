package com.wipro.UHGVirtualSME.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


import org.apache.commons.codec.binary.Base64;

public class EncryptDecryptUtil {

    public static final byte[] KEY = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P' };

    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!%^&*()?><:;{}~$%]).{8,20})";

    private static Cipher ecipher;
    private static Cipher dcipher;
    private static Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private static Matcher matcher;


    static {
        try {
            ecipher = Cipher.getInstance("AES");
            SecretKeySpec eSpec = new SecretKeySpec(KEY, "AES");
            ecipher.init(Cipher.ENCRYPT_MODE, eSpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            dcipher = Cipher.getInstance("AES");
            SecretKeySpec dSpec = new SecretKeySpec(KEY, "AES");
            dcipher.init(Cipher.DECRYPT_MODE, dSpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static String encrypt(String value) {
        byte[] b1 = value.getBytes();
        byte[] encryptedValue;
        try {
            encryptedValue = ecipher.doFinal(b1);
            return Base64.encodeBase64String(encryptedValue);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }


    public static String decrypt(String encryptedValue) {
        byte[] decryptedValue = Base64.decodeBase64(encryptedValue.getBytes());
        byte[] decValue;
        try {
            decValue = dcipher.doFinal(decryptedValue);
            return new String(decValue);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static boolean validate(final String password) {
        matcher = pattern.matcher(password);
        return matcher.matches();

    }
}
