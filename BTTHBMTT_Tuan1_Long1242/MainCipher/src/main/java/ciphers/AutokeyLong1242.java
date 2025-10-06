/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciphers;

/**
 *
 * @author Administrator
 */
public class AutokeyLong1242 {
    public String encrypt (String plaintext, String key) {
        plaintext = plaintext.toUpperCase();
        key = key.toUpperCase();
        
        StringBuilder ciphertext = new StringBuilder();
        int index = 0;

        for (int i = 0; i < plaintext.length(); i++) {
            char pChar = plaintext.charAt(i);
            if (Character.isLetter(pChar)) {
                char keyChar;

                if (index < key.length()) {
                    keyChar = key.charAt(index++);
                } else {
                    keyChar = plaintext.charAt(index++ - key.length());
                }

                int shift = keyChar - 'A';
                char encryptedChar = (char) (((pChar - 'A' + shift) % 26) + 'A');
                ciphertext.append(encryptedChar);
            } else {
                ciphertext.append(pChar); 
            }
        }
        return ciphertext.toString();
    }

    public String decrypt (String ciphertext, String key) {
        ciphertext = ciphertext.toUpperCase();
        key = key.toUpperCase();
        
        StringBuilder plaintext = new StringBuilder();
        int index = 0;

        for (int i = 0; i < ciphertext.length(); i++) {
            char cChar = ciphertext.charAt(i);
            if (Character.isLetter(cChar)) {
                char keyChar;

                if (index < key.length()) {
                    keyChar = key.charAt(index++);
                    index++;
                } else {
                    keyChar = plaintext.charAt(index - key.length());
                }

                int shift = keyChar - 'A';
                char decryptedChar = (char) (((cChar - 'A' - shift + 26) % 26) + 'A');
                plaintext.append(decryptedChar);
            } else {
                plaintext.append(cChar); 
            }
        }
        return plaintext.toString();
    }
        
}
