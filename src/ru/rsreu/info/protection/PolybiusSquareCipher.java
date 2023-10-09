package ru.rsreu.info.protection;

import java.util.Scanner;
public class PolybiusSquareCipher {
    private static final char[][] polybiusSquare = {
            {'L', 'M', 'N', 'O', 'P'},
            {'A', 'B', 'C', 'D', 'E'},
            {'Q', 'R', 'S', 'T', 'U'},
            {'F', 'G', 'H', 'I', 'K'},
            {'V', 'W', 'X', 'Y', 'Z'}
    };

    public static String encode(String input) {
        input = input.toUpperCase();
        StringBuilder encoded = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == 'J') {
                c = 'I'; // Замена 'J' на 'I'
            }

            if (Character.isLetter(c)) {
                for (int row = 0; row < 5; row++) {
                    for (int col = 0; col < 5; col++) {
                        if (polybiusSquare[row][col] == c) {
                            // Шифровка: нижняя буква из того же столбца
                            int newRow = (row + 1) % 5;
                            encoded.append(polybiusSquare[newRow][col]);
                            break;
                        }
                    }
                }
            } else {
                encoded.append(c);
            }
        }

        return encoded.toString();
    }

    public static String decode(String encodedText) {
        encodedText = encodedText.toUpperCase();
        StringBuilder decoded = new StringBuilder();

        for (char c : encodedText.toCharArray()) {
            if (Character.isLetter(c)) {
                for (int row = 0; row < 5; row++) {
                    for (int col = 0; col < 5; col++) {
                        if (polybiusSquare[row][col] == c) {
                            // Декодирование: верхняя буква из того же столбца
                            int newRow = (row - 1 + 5) % 5;
                            decoded.append(polybiusSquare[newRow][col]);
                            break;
                        }
                    }
                }
            } else {
                decoded.append(c);
            }
        }

        return decoded.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String mode = sc.nextLine();
        String text = sc.nextLine();
        String result = null;
        
        if("e".equals(mode)){
           result =  encode(text);
        } else if("d".equals(mode)){
            result = decode(text);
        }
        System.out.println(result);
    }
}
