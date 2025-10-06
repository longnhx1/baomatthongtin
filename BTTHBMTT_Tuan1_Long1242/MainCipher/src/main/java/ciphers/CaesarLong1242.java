/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciphers;

/**
 *
 * @author nierlynguyen
 */
public class CaesarLong1242 {
    
    // Ham ma hoa voi khoa key
    public static String encrypt(String text, int key) {
        return caesarCipher(text, key, true);
    }

    // Ham giai ma voi khoa key
    public static String decrypt(String text, int key) {
        return caesarCipher(text, key, false);
    }

    // Ham giup ma hoa va giai ma
    private static String caesarCipher(String text, int key, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        int shift = encrypt ? key : -key;

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int offset = (character - base + shift) % 26;
                if (offset < 0) {
                    offset += 26;
                }
                result.append((char) (base + offset));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}
