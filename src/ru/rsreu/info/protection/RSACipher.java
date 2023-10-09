package ru.rsreu.info.protection;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSACipher {
    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    // Генерация ключей RSA
    public void generateKeys(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger p = new BigInteger(bitLength, 100, random);
        BigInteger q = new BigInteger(bitLength, 100, random);

        modulus = p.multiply(q);

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        publicKey = new BigInteger("65537"); // Общепринятый открытый ключ (число Ферма)
        privateKey = publicKey.modInverse(phi);
    }

    // Шифрование сообщения
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    // Расшифрование сообщения
    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(privateKey, modulus);
    }

    public static void main(String[] args) {
        RSACipher rsa = new RSACipher();
        int bitLength = 1024; // Длина ключа в битах
        Scanner scanner = new Scanner(System.in);
        bitLength = scanner.nextInt();

        rsa.generateKeys(bitLength);

        String originalMessage = "Hello, RSA!";
        BigInteger message = new BigInteger(originalMessage.getBytes());

        // Шифрование
        BigInteger encryptedMessage = rsa.encrypt(message);

        // Расшифрование
        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);


        System.out.println("Original Message: " + originalMessage);
        System.out.println("Encrypted Message: " + encryptedMessage);
        System.out.println("Decrypted Message: " + new String(decryptedMessage.toByteArray()));
    }

}
