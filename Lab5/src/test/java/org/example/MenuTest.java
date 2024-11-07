package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private Menu menu;
    private static final String TEST_FILE = "laba5.txt";
    private static final String ENCRYPTED_FILE = "encrypted.txt";
    private static final String DECRYPTED_FILE = "decrypted.txt";

    @BeforeEach
    public void setUp() throws IOException {
        menu = new Menu();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write("My name is Sofiia, I love Java language)\n");
            writer.write("Hey\n");
            writer.write("U look like a god!\n");
        }
    }

    @Test
    public void testReadFile() throws IOException {
        menu.readFile(new Scanner(TEST_FILE));

        List<String> lines = menu.fileHandler.readLinesFromFile(TEST_FILE);
        assertEquals(3, lines.size());
        assertEquals("My name is Sofiia, I love Java language)", lines.get(0));
        assertEquals("Hey", lines.get(1));
        assertEquals("U look like a god!", lines.get(2));
    }

    @Test
    public void testShowLongestLine() throws IOException {
        menu.readFile(new Scanner(TEST_FILE));

        menu.showLongestLine();

        List<String> lines = menu.fileHandler.readLinesFromFile(TEST_FILE);
        String longestLine = lines.stream()
                .max(Comparator.comparingInt(line -> line.split("\\s+").length))
                .orElse("");

        assertTrue(longestLine.contains("My name is Sofiia, I love Java language"));
    }

    @Test
    public void testSaveFile() throws IOException {
        menu.readFile(new Scanner(TEST_FILE));

        String saveFilePath = "savedFile.txt";
        menu.saveFile(new Scanner(saveFilePath));

        try (BufferedReader reader = new BufferedReader(new FileReader(saveFilePath))) {
            String line;
            StringBuilder savedContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                savedContent.append(line).append("\n");
            }
            assertTrue(savedContent.toString().contains("My name is Sofiia, I love Java language"));
        }
    }

    @Test
    public void testEncryptFile() throws IOException {
        menu.readFile(new Scanner(TEST_FILE));

        char key = 'K';
        menu.encryptFile(new Scanner(String.valueOf(key)));

        List<String> encryptedLines = menu.fileHandler.readLinesFromFile(ENCRYPTED_FILE);
        assertFalse(encryptedLines.isEmpty());
    }

    @Test
    public void testDecryptFile() throws IOException {
        menu.readFile(new Scanner(TEST_FILE));
        char key = 'K';
        menu.encryptFile(new Scanner(String.valueOf(key)));

        menu.decryptFile(new Scanner(String.valueOf(key)));

        List<String> decryptedLines = menu.fileHandler.readLinesFromFile(DECRYPTED_FILE);
        assertFalse(decryptedLines.isEmpty());
        assertEquals("My name is Sofiia, I love Java language)", decryptedLines.get(0));
    }

    @Test
    public void testCountTagsSortedByAlphabet() throws IOException {
        String url = "http://example.com";
        menu.countTagsSortedByAlphabet(new Scanner(url));

        Map<String, Integer> tags = menu.tagCounter.countTags(url);
        assertFalse(tags.isEmpty());
    }

    @Test
    public void testCountTagsSortedByFrequency() throws IOException {
        String url = "http://example.com";
        menu.countTagsSortedByFrequency(new Scanner(url));

        Map<String, Integer> tags = menu.tagCounter.countTags(url);
        assertFalse(tags.isEmpty());
    }
}
