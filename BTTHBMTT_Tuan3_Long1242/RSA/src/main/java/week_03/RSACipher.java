package week_03;

import java.math.BigInteger;
import java.util.Random;

public class RSACipher {
    private BigInteger P, Q, N, r, E, D;
    private int bitLength;

    public RSACipher(int bitLength) {
        this.bitLength = bitLength;
        generateKeys();
    }

    private void generateKeys() {
        P = BigInteger.probablePrime(bitLength / 2, new Random());
        Q = BigInteger.probablePrime(bitLength / 2, new Random());
        N = P.multiply(Q);
        r = P.subtract(BigInteger.ONE).multiply(Q.subtract(BigInteger.ONE));
        do {
            E = new BigInteger(bitLength, new Random());
        } while ((E.compareTo(r) != -1) || (E.gcd(r).compareTo(BigInteger.ONE) != 0));
        D = E.modInverse(r);
    }

    public BigInteger[] encrypt(String message) {
        byte[] bytes = message.getBytes();
        BigInteger[] encrypted = new BigInteger[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            encrypted[i] = new BigInteger(new byte[]{bytes[i]}).modPow(E, N);
        }
        return encrypted;
    }

    public String decrypt(BigInteger[] message, BigInteger d, BigInteger n) {
        byte[] bytes = new byte[message.length];
        for (int i = 0; i < message.length; i++) {
            bytes[i] = message[i].modPow(d, n).byteValue();
        }
        return new String(bytes);
    }

    public BigInteger getP() {
        return P;
    }

    public BigInteger getQ() {
        return Q;
    }

    public BigInteger getN() {
        return N;
    }

    public BigInteger getE() {
        return E;
    }

    public BigInteger getD() {
        return D;
    }
}
