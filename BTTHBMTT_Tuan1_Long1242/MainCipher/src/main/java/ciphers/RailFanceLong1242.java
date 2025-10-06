/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciphers;

/**
 *
 * @author Administrator
 */
public class RailFanceLong1242 {
    public static String encrypt(String text, int key) {
        if (key <= 1) {
            return text; // No encryption needed for key 1 or less
        }

        char[] chars = text.toCharArray();
        char[] result = new char[chars.length];
        int cycle = 2 * key - 2;
        int index = 0;

        for (int i = 0; i < key; i++) {
            for (int j = 0; j + i < chars.length; j += cycle) {
                result[index++] = chars[j + i];
                if (i != 0 && i != key - 1 && j + cycle - i < chars.length) {
                    result[index++] = chars[j + cycle - i];
                }
            }
        }

        return new String(result);
    }

    public static String decrypt(String text, int key) {
        if (key <= 1) {
            return text; // No decryption needed for key 1 or less
        }

        char[] chars = text.toCharArray();
        char[] result = new char[chars.length];
        int cycle = 2 * key - 2;
        int index = 0;
        
        for (int i = 0; i < key; i++) {
            for (int j = 0; j + i < chars.length; j += cycle) {
                result[j + i] = chars[index++];
                if (i != 0 && i != key - 1 && j + cycle - i < chars.length) {
                    result[j + cycle - i] = chars[index++];
                }
            }
        }
        return new String(result);
    }
}
