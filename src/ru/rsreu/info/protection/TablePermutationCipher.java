package ru.rsreu.info.protection;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class TablePermutationCipher {

    public static void main(String[] args) {
        int[] key = {2, 0, 1}; // Задайте свой ключ перестановки

        String plaintext = "HELLO WORLD"; // Текст для шифрования
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Зашифрованный текст: " + ciphertext);

        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Расшифрованный текст: " + decryptedText);
    }

    // Метод для шифрования текста
    public static String encrypt(String plaintext, int[] key) {
        int textLength = plaintext.length();
        int tableSize = key.length;

        int numRows = (int) Math.ceil((double) textLength / tableSize);
        char[][] table = new char[numRows][tableSize];
        int currentIndex = 0;

        // Заполнение таблицы символами из текста
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < tableSize; col++) {
                if (currentIndex < textLength) {
                    table[row][col] = plaintext.charAt(currentIndex);
                    currentIndex++;
                } else {
                    table[row][col] = ' '; // Заполнение пробелами, если символы закончились
                }
            }
        }

        // Шифрование согласно ключу
        StringBuilder ciphertext = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int col : key) {
                ciphertext.append(table[row][col]);
            }
        }

        return ciphertext.toString();
    }

    // Метод для дешифрования текста
    public static String decrypt(String ciphertext, int[] key) {
        int textLength = ciphertext.length();
        int tableSize = key.length;

        int numRows = textLength / tableSize;
        char[][] table = new char[numRows][tableSize];
        int currentIndex = 0;

        // Распределение символов по таблице согласно ключу
        for (int row = 0; row < numRows; row++) {
        for (int col : key) {

                table[row][col] = ciphertext.charAt(currentIndex);
                currentIndex++;
            }
        }

        // Дешифрование
        StringBuilder plaintext = new StringBuilder();

            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < tableSize; col++) {

                plaintext.append(table[row][col]);
            }
        }

        return plaintext.toString().trim();
    }
}
