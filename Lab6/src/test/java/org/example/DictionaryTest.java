package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {
    private Dictionary dictionary;

    @BeforeEach
    void setUp() {
        dictionary = new Dictionary();
    }

    @Test
    void testAddWord() {
        dictionary.addWord("hello", "привіт");
        assertEquals("привіт", dictionary.translatePhrase("hello", "en-uk"));
    }

    @Test
    void testRemoveWordByEnglishKey() {
        dictionary.addWord("hello", "привіт");
        dictionary.removeWord("hello");
        assertEquals("[hello]", dictionary.translatePhrase("hello", "en-uk"));
    }

    @Test
    void testRemoveWordByUkrainianValue() {
        dictionary.addWord("hello", "привіт");
        dictionary.removeWord("привіт");
        assertEquals("[hello]", dictionary.translatePhrase("hello", "en-uk"));
    }

    @Test
    void testRemoveWordNotFound() {
        dictionary.addWord("hello", "привіт");
        dictionary.removeWord("goodbye");
        assertEquals("привіт", dictionary.translatePhrase("hello", "en-uk"));
    }

    @Test
    void testDisplayAllWordsEmptyDictionary() {
        String output = captureConsoleOutput(() -> dictionary.displayAllWords());
        assertTrue(output.contains("Словник порожній."));
    }

    @Test
    void testDisplayAllWordsNonEmptyDictionary() {
        dictionary.addWord("hello", "привіт");
        String output = captureConsoleOutput(() -> dictionary.displayAllWords());
        assertTrue(output.contains("hello - привіт"));
    }

    @Test
    void testTranslatePhraseEnToUk() {
        dictionary.addWord("hello", "привіт");
        String translation = dictionary.translatePhrase("hello world", "en-uk");
        assertEquals("привіт [world]", translation);
    }

    @Test
    void testTranslatePhraseUkToEn() {
        dictionary.addWord("hello", "привіт");
        String translation = dictionary.translatePhrase("привіт світе", "uk-en");
        assertEquals("hello [світе]", translation);
    }

    @Test
    void testTranslatePhraseWithPunctuation() {
        dictionary.addWord("hello", "привіт");
        String translation = dictionary.translatePhrase("hello, world!", "en-uk");
        assertEquals("привіт, [world!]!", translation);
    }

    @Test
    void testIsEnglishWordValid() {
        assertFalse(dictionary.isEnglishWord("hello"));
    }

    @Test
    void testIsEnglishWordInvalid() {
        assertTrue(dictionary.isEnglishWord("привіт123"));
    }

    @Test
    void testIsUkrainianWordValid() {
        assertFalse(dictionary.isUkrainianWord("привіт"));
    }

    @Test
    void testIsUkrainianWordInvalid() {
        assertTrue(dictionary.isUkrainianWord("hello123"));
    }

    private String captureConsoleOutput(Runnable action) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        action.run();
        System.setOut(System.out);
        return out.toString().trim();
    }
}
