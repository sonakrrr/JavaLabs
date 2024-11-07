package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TagCounterTest {

    private TagCounter tagCounter;

    @BeforeEach
    public void setUp() {
        tagCounter = new TagCounter();
    }

    @Test
    public void testCountTags() throws IOException {
        // Тест на підрахунок тегів з простого HTML тексту
        String url = "https://www.example.com"; // Використовуємо реальний URL для тесту (можна замінити на фейковий сервер)

        // Підраховуємо теги
        Map<String, Integer> tagCounts = tagCounter.countTags(url);

        // Перевіряємо, чи теги були знайдені
        assertNotNull(tagCounts);
        assertTrue(tagCounts.size() > 0, "The map of tags should not be empty.");

        // Наприклад, перевіряємо наявність конкретного тега, який може бути на сторінці
        assertTrue(tagCounts.containsKey("<html>"));
    }

    @Test
    public void testPrintTagsSortedByAlphabet() {
        // Підготовка вхідних даних
        Map<String, Integer> tagCounts = Map.of(
                "<html>", 3,
                "<body>", 5,
                "<head>", 2
        );
        StringBuilder outputBuilder = new StringBuilder();

        // Виведення тегів за алфавітним порядком
        tagCounter.printTagsSortedByAlphabet(tagCounts, outputBuilder);

        // Перевірка правильності сортування
        String expectedOutput = "<body>: 5\n<head>: 2\n<html>: 3\n";
        assertEquals(expectedOutput, outputBuilder.toString());
    }

    @Test
    public void testPrintTagsSortedByFrequency() {
        // Підготовка вхідних даних
        Map<String, Integer> tagCounts = Map.of(
                "<html>", 3,
                "<body>", 5,
                "<head>", 2
        );
        StringBuilder outputBuilder = new StringBuilder();

        // Виведення тегів за частотою
        tagCounter.printTagsSortedByFrequency(tagCounts, outputBuilder);

        // Перевірка правильності сортування
        String expectedOutput = "<head>: 2\n<html>: 3\n<body>: 5\n";
        assertEquals(expectedOutput, outputBuilder.toString());
    }

    @Test
    public void testCountTagsWithEmptyUrl() throws IOException {
        // Тест на випадок порожнього URL або некоректного введення
        String url = "https://www.nonexistentwebsite.com";

        // Підраховуємо теги (передбачаємо, що метод має коректно обробляти помилки)
        Map<String, Integer> tagCounts = tagCounter.countTags(url);

        // Перевірка, що результат порожній, адже сторінка не існує
        assertNotNull(tagCounts);
        assertTrue(tagCounts.isEmpty(), "The map of tags should be empty for a non-existent URL.");
    }
}
