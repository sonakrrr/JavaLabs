package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    @Test
    void testAddWordAndDisplayWords() {
        // Вхідні дані для емуляції введення
        String input = "1\nhello\nпривіт\n3\n5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Вихід для захоплення результатів програми
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Виконання програми
        Main.main(new String[]{});

        // Перевірка результатів
        String output = out.toString();
        assertTrue(output.contains("Слово додано до словника."));
        assertTrue(output.contains("hello - привіт"));
    }

    @Test
    void testTranslatePhrase() {
        // Вхідні дані для перекладу
        String input = "1\nhello\nпривіт\n4\n1\nhello\n5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String output = out.toString();
        assertTrue(output.contains("Переклад: привіт"));
    }

    @Test
    void testInvalidChoice() {
        String input = "9\n5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String output = out.toString();
        assertTrue(output.contains("Невірний вибір. Спробуйте ще раз."));
    }

    @Test
    void testPhraseTranslationWithNoWords() {
        // Емулюємо переклад фрази, коли словник порожній
        String input = "4\n1\nhello world\n5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        // Перевіряємо, чи програма вивела повідомлення про відсутність перекладу
        String output = out.toString();
        assertTrue(output.contains("Переклад: "));
    }

    @Test
    void testRemoveWordNotInDictionary() {
        // Емулюємо видалення слова, якого немає у словнику
        String input = "2\nnonexistent\n5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        // Перевіряємо, чи програма вивела правильне повідомлення
        String output = out.toString();
        assertTrue(output.contains("Слово не знайдено у словнику."));
    }

    @Test
    void testInvalidWordInput() {
        String input = "1\nhello123\nhello\nпривіт123\nпривіт\n3\n5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String output = out.toString();

        assertTrue(output.contains("Помилка: Введіть слово, що містить тільки англійські літери."));
        assertTrue(output.contains("Помилка: Введіть слово, що містить тільки українські літери."));

        assertTrue(output.contains("hello - привіт"));
    }

}
