package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input a number
        System.out.print("Enter a positive integer: ");
        int decimalNumber = scanner.nextInt();

        if (decimalNumber < 0) {
            System.out.println("The number must be a positive integer.");
            return;
        }

        // Convert to binary system
        System.out.println("Binary representation: " + toBinary(decimalNumber));

        // Convert to octal system
        System.out.println("Octal representation: " + toOctal(decimalNumber));

        // Convert to hexadecimal system
        System.out.println("Hexadecimal representation: " + toHexadecimal(decimalNumber));

        scanner.close();
    }

    // Method for converting to binary system
    public static String toBinary(int number) {
        StringBuilder binary = new StringBuilder();
        while (number > 0) {
            binary.insert(0, number % 2);
            number /= 2;
        }
        return !binary.isEmpty() ? binary.toString() : "0";
    }

    // Method for converting to octal system
    public static String toOctal(int number) {
        StringBuilder octal = new StringBuilder();
        while (number > 0) {
            octal.insert(0, number % 8);
            number /= 8;
        }
        return !octal.isEmpty() ? octal.toString() : "0";
    }

    // Method for converting to hexadecimal system
    public static String toHexadecimal(int number) {
        StringBuilder hex = new StringBuilder();
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (number > 0) {
            hex.insert(0, hexChars[number % 16]);
            number /= 16;
        }
        return !hex.isEmpty() ? hex.toString() : "0";
    }
}