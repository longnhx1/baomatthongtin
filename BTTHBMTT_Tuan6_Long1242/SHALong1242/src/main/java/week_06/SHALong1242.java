/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week_06;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author Administrator
 */
public class SHALong1242 {
    public static String sha1(String input)
    throws NoSuchAlgorithmException {
        return hashString(input, "SHA-1");
    }

    public static String sha256(String input)
    throws NoSuchAlgorithmException {
        return hashString(input, "SHA-256");
    }

    public static String sha512(String input)
    throws NoSuchAlgorithmException {
        return hashString(input, "SHA-512");
    }

    private static String hashString(String input, String algorithm)
    throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] hashedBytes = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
