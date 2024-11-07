package org.example;

import java.io.*;

public class EncryptDecrypt {

    private static final int PRINTABLE_START = 32;  // Space character
    private static final int PRINTABLE_END = 126;   // '~' character

    // Method to wrap around the printable range for encryption
    private char encryptChar(char ch, int key) {
        if (ch >= PRINTABLE_START && ch <= PRINTABLE_END) {
            int shifted = ch + key;
            if (shifted > PRINTABLE_END) {
                shifted = PRINTABLE_START + (shifted - PRINTABLE_END - 1);
            }
            return (char) shifted;
        }
        return ch;  // Return unchanged if not in the printable range
    }

    // Method to wrap around the printable range for decryption
    private char decryptChar(char ch, int key) {
        if (ch >= PRINTABLE_START && ch <= PRINTABLE_END) {
            int shifted = ch - key;
            if (shifted < PRINTABLE_START) {
                shifted = PRINTABLE_END - (PRINTABLE_START - shifted - 1);
            }
            return (char) shifted;
        }
        return ch;  // Return unchanged if not in the printable range
    }

    public void encrypt(String inputFilePath, String outputFilePath, int key) throws IOException {
        try (FilterOutputStream fos = new FilterOutputStream(new FileOutputStream(outputFilePath));
             BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            int c;
            while ((c = br.read()) != -1) {
                // Encrypt the character and write it
                fos.write(encryptChar((char) c, key));
            }
        }
    }

    public void decrypt(String inputFilePath, String outputFilePath, int key) throws IOException {
        try (FilterOutputStream fos = new FilterOutputStream(new FileOutputStream(outputFilePath));
             BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            int c;
            while ((c = br.read()) != -1) {
                // Decrypt the character and write it
                fos.write(decryptChar((char) c, key));
            }
        }
    }
}