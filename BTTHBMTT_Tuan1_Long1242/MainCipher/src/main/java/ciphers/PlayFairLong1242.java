/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciphers;
import java.util.*;
/**
 *
 * @author Administrator
 */
public class PlayFairLong1242 {
    private final char[][] keyMatrix;
    private static final int MATIX_SIZE = 5;

    public PlayFairLong1242(String key) {
        keyMatrix = generateKeyMatrix(key);
    }

    private char[][] generateKeyMatrix(String key) {
        char[][] matrix = new char[MATIX_SIZE][MATIX_SIZE];
        Set<Character> usedChars = new HashSet<>();
        StringBuilder filteredKey = new StringBuilder();

        for (char ch : key.toUpperCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                ch = Character.toUpperCase(ch);
                if (ch == 'J') ch = 'I'; // Treat 'I' and 'J' as the same letter
                if (!usedChars.contains(ch)) {
                    usedChars.add(ch);
                    filteredKey.append(ch);
                }
            }
        }
        
        for (char ch  = 'A'; ch <= 'Z'; ch++) {
            if (ch == 'J') continue; // Skip 'J'
            if (!usedChars.contains(ch)) {
                usedChars.add(ch);
                filteredKey.append(ch);
            }
        }

        // Ensure filteredKey has at least 25 characters
        while (filteredKey.length() < MATIX_SIZE * MATIX_SIZE) {
            for (char ch = 'A'; ch <= 'Z' && filteredKey.length() < MATIX_SIZE * MATIX_SIZE; ch++) {
                if (ch == 'J') continue;
                if (!usedChars.contains(ch)) {
                    usedChars.add(ch);
                    filteredKey.append(ch);
                }
            }
        }

        int index = 0;
        for (int row = 0; row < MATIX_SIZE; row++) {
            for (int col = 0; col < MATIX_SIZE; col++) {
                matrix[row][col] = filteredKey.charAt(index++);
            }
        }
        return matrix;
    }

    private String preprocessText(String text) {
        StringBuilder sb = new StringBuilder(text.toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I'));
        for (int i = 0; i < sb.length() - 1; i += 2) {
            if (sb.charAt(i) == sb.charAt(i + 1)) {
                sb.insert(i + 1, 'X');
            }
        }
        if (sb.length() % 2 != 0) {
            sb.append('X');
        }
        return sb.toString();
    }

    public String encrypt(String plaintext) {
        return processText(plaintext, true);
    }

    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        ciphertext = ciphertext.toUpperCase().replaceAll("[^A-Z]", "");

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char firstChar = ciphertext.charAt(i);
            char secondChar = ciphertext.charAt(i + 1);

            int[] pos1 = findPosition(firstChar);
            int[] pos2 = findPosition(secondChar);

            if (pos1[0] == pos2[0]) {
                plaintext.append(keyMatrix[pos1[0]][(pos1[1] + 4) % 5]);
                plaintext.append(keyMatrix[pos2[0]][(pos2[1] + 4) % 5]);
            } else if (pos1[1] == pos2[1]) {
                plaintext.append(keyMatrix[(pos1[0] + 4) % 5][pos1[1]]);
                plaintext.append(keyMatrix[(pos2[0] + 4) % 5][pos2[1]]);
            } else {
                plaintext.append(keyMatrix[pos1[0]][pos2[1]]);
                plaintext.append(keyMatrix[pos2[0]][pos1[1]]);
            }
        }

        for (int i = 0; i < plaintext.length(); i++) {
            if (plaintext.charAt(i) == 'X' && plaintext.charAt(i - 1) == plaintext.charAt(i + 1)) {
                plaintext.deleteCharAt(i);
            }
        }

        return plaintext.toString();
    }

    private String processText(String text, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        text = preprocessText(text);

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] pos1 = findPosition(a);
            int[] pos2 = findPosition(b);

            if (pos1[0] == pos2[0]) {
                result.append(keyMatrix[pos1[0]][(pos1[1] + (encrypt ? 1 : 4)) % MATIX_SIZE]);
                result.append(keyMatrix[pos2[0]][(pos2[1] + (encrypt ? 1 : 4)) % MATIX_SIZE]);
            } else if (pos1[1] == pos2[1]) {
                result.append(keyMatrix[(pos1[0] + (encrypt ? 1 : 4)) % MATIX_SIZE][pos1[1]]);
                result.append(keyMatrix[(pos2[0] + (encrypt ? 1 : 4)) % MATIX_SIZE][pos2[1]]);
            } else {
                result.append(keyMatrix[pos1[0]][pos2[1]]);
                result.append(keyMatrix[pos2[0]][pos1[1]]);
            }
        }

        return result.toString();
    }
    private int[] findPosition(char ch) {
        for (int row = 0; row < MATIX_SIZE; row++) {
            for (int col = 0; col < MATIX_SIZE; col++) {
                if (keyMatrix[row][col] == ch) {
                    return new int[]{row, col};
                }
            }
        }
        return null; 
    }

    public String getKeyMatrixString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < MATIX_SIZE; row++) {
            for (int col = 0; col < MATIX_SIZE; col++) {
                sb.append(keyMatrix[row][col]).append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
