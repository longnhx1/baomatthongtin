/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week_01;
import java.util.*;
/**
 *
 * @author Administrator
 */
public class TranspositionCipher {
    public String encrypt(String text, int[] key) {
        int numRows = (int) Math.ceil((double) text.length() / key.length);
        char[][] grid = new char[numRows][key.length];
        for (char[] row : grid) {
            Arrays.fill(row, ' ');
        }

        // Fill the grid with the plaintext
        for (int i = 0; i < text.length(); i++) {
            grid[i / key.length][i % key.length] = text.charAt(i);
        }

        // Read the columns according to the key
        StringBuilder ciphertext = new StringBuilder();
        for (int k : key) {
            for (int r = 0; r < numRows; r++) {
                if (grid[r][k - 1] != ' ') {
                    ciphertext.append(grid[r][k - 1]);
                }
            }
        }

        return ciphertext.toString();
    }

    public String decrypt(String ciphertext, int[] key) {
        int numRows = (int) Math.ceil((double) ciphertext.length() / key.length);
        char[][] grid = new char[numRows][key.length];
        for (char[] row : grid) {
            Arrays.fill(row, ' ');
        }

        int index = 0;
        for (int k : key) {
            for (int r = 0; r < numRows; r++) {
                if (index < ciphertext.length()) {
                    grid[r][k - 1] = ciphertext.charAt(index++);
                }
            }
        }

        StringBuilder plaintext = new StringBuilder();
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < key.length; c++) {
                if (grid[r][c] != ' ') {
                    plaintext.append(grid[r][c]);
                }
            }
        }

        return plaintext.toString();
    }
}
