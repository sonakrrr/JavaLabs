package org.example;

import java.util.Scanner;
import java.util.function.Function;

public class Main {

    public static Function<Integer, String> toBinary = number -> {
        if (number < 0) return "0";
        Function<Integer, String> recursiveBinary = new Function<>() {
            @Override
            public String apply(Integer num) {
                if (num == 0) return "0";
                if (num == 1) return "1";
                return apply(num / 2) + (num % 2);
            }
        };
        return recursiveBinary.apply(number);
    };

    public static Function<Integer, String> toOctal = number -> {
        if (number < 0) return "0";
        Function<Integer, String> recursiveOctal = new Function<>() {
            @Override
            public String apply(Integer num) {
                if (num == 0) return "0";
                if (num < 8) return "" + num;
                return apply(num / 8) + (num % 8);
            }
        };
        return recursiveOctal.apply(number);
    };

    public static Function<Integer, String> toHexadecimal = number -> {
        if (number < 0) return "0";
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        Function<Integer, String> recursiveHex = new Function<>() {
            @Override
            public String apply(Integer num) {
                if (num == 0) return "0";
                if (num < 16) return Character.toString(hexChars[num]);
                return apply(num / 16) + hexChars[num % 16];
            }
        };
        return recursiveHex.apply(number);
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input a number
        System.out.print("Enter a positive integer: ");
        int decimalNumber = scanner.nextInt();

        if (decimalNumber < 0) {
            System.out.println("The number must be a positive integer.");
            return;
        }

        System.out.println("Binary representation: " + toBinary.apply(decimalNumber));
        System.out.println("Octal representation: " + toOctal.apply(decimalNumber));
        System.out.println("Hexadecimal representation: " + toHexadecimal.apply(decimalNumber));

        scanner.close();
    }
}
