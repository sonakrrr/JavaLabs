package org.example;

import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class EncryptDecryptTest {

    private static final String DECRYPTED_FILE = "decrypted.txt";
    private static final String ENCRYPTED_FILE = "encrypted.txt";

    @Test
    public void testEncryption() throws IOException {
        EncryptDecrypt encryptDecrypt = new EncryptDecrypt();

        int key = 'a';
        encryptDecrypt.encrypt(DECRYPTED_FILE, ENCRYPTED_FILE, key);

        try (BufferedReader decryptedReader = new BufferedReader(new FileReader(DECRYPTED_FILE));
             BufferedReader encryptedReader = new BufferedReader(new FileReader(ENCRYPTED_FILE))) {
            String decryptedLine;
            String encryptedLine;
            while ((decryptedLine = decryptedReader.readLine()) != null) {
                encryptedLine = encryptedReader.readLine();
                assertNotEquals(decryptedLine, encryptedLine, "Encrypted content should differ from original content.");
            }
        }
    }

    @Test
    public void testDecryption() throws IOException {
        EncryptDecrypt encryptDecrypt = new EncryptDecrypt();

        int key = 'a';
        encryptDecrypt.encrypt(DECRYPTED_FILE, ENCRYPTED_FILE, key);

        encryptDecrypt.decrypt(ENCRYPTED_FILE, DECRYPTED_FILE, key);

        try (BufferedReader decryptedReader = new BufferedReader(new FileReader(DECRYPTED_FILE));
             BufferedReader originalReader = new BufferedReader(new FileReader(ENCRYPTED_FILE))) {
            String decryptedLine;
            String originalLine;
            while ((decryptedLine = decryptedReader.readLine()) != null) {
                originalLine = originalReader.readLine();
                assertEquals(originalLine, decryptedLine, "Decrypted content should match the original content.");
            }
        }
    }

    @Test
    public void testDecryptionWithInvalidKey() throws IOException {
        EncryptDecrypt encryptDecrypt = new EncryptDecrypt();

        int key = 'a';
        encryptDecrypt.encrypt(DECRYPTED_FILE, ENCRYPTED_FILE, key);

        int wrongKey = 'b';
        encryptDecrypt.decrypt(ENCRYPTED_FILE, DECRYPTED_FILE, wrongKey);

        try (BufferedReader decryptedReader = new BufferedReader(new FileReader(DECRYPTED_FILE))) {
            String decryptedLine;
            while ((decryptedLine = decryptedReader.readLine()) != null) {
                assertNotEquals(decryptedLine, "My name is Sofiia, I love Java language",
                        "Decrypted content should not match the original content with the wrong key.");
            }
        }
    }
}
