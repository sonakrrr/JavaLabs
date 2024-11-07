package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    private FileHandler fileHandler;
    private static final String TEST_FILE = "laba5.txt";
    private static final String TEST_SAVE_PATH = "savedFile.ser";

    @BeforeEach
    public void setUp() throws IOException {
        fileHandler = new FileHandler();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write("My name is Sofiia, I love Java language)\n");
            writer.write("Hey\n");
            writer.write("U look like a god!\n");
        }
    }

    @Test
    public void testSaveToFile() throws IOException {
        fileHandler.setContent("Test content");
        fileHandler.saveToFile(TEST_SAVE_PATH);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TEST_SAVE_PATH))) {
            FileHandler deserializedFileHandler = (FileHandler) ois.readObject();
            assertEquals("Test content", deserializedFileHandler.getContent());
        } catch (ClassNotFoundException e) {
            fail("Class not found during deserialization");
        }
    }

    @Test
    public void testReadLinesFromFile() throws IOException {
        List<String> lines = fileHandler.readLinesFromFile(TEST_FILE);

        assertEquals(3, lines.size());

        assertEquals("My name is Sofiia, I love Java language)", lines.get(0));
        assertEquals("Hey", lines.get(1));
        assertEquals("U look like a god!", lines.get(2));
    }

    @Test
    public void testGetContent() throws IOException {
        fileHandler.readLinesFromFile(TEST_FILE);

        String expectedContent = "My name is Sofiia, I love Java language)\nHey\nU look like a god!";
        assertEquals(expectedContent, fileHandler.getContent());
    }

    @Test
    public void testGetFilePath() throws IOException {
        fileHandler.readLinesFromFile(TEST_FILE);

        assertEquals(TEST_FILE, fileHandler.getFilePath());
    }
}
