/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week_04;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
/**
 *
 * @author Administrator
 */
public class DESLong1242 {
    private static final String ALGORITHM = "DES";
    public static String encrypt(String plaintext, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
  
        SecretKey key = generateKey(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    public static String decrypt(String ciphertext, String secretKey) 
            throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, 
                   NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        
        SecretKey key = generateKey(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        return new String(decryptedBytes);
    }


    private static SecretKey generateKey(String secretKey) 
            throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        
        DESKeySpec desKeySpec = new DESKeySpec(secretKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        
        return keyFactory.generateSecret(desKeySpec);
    }
}
