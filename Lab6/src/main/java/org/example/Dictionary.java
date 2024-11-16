package org.example;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private final Map<String, String> dictionary;

    public Dictionary() {
        dictionary = new HashMap<>();
    }

    // Метод для додавання нової пари слів
    public void addWord(String englishWord, String ukrainianWord) {
        dictionary.put(englishWord.toLowerCase(), ukrainianWord.toLowerCase());
    }

    // Метод для видалення слова з словника
    public void removeWord(String word) {
        if (dictionary.containsKey(word.toLowerCase())) {
            dictionary.remove(word.toLowerCase());
            System.out.println("Слово видалено з словника.");
        } else if (dictionary.containsValue(word.toLowerCase())) {
            dictionary.entrySet().removeIf(entry -> entry.getValue().equals(word.toLowerCase()));
            System.out.println("Слово видалено з словника.");
        } else {
            System.out.println("Слово не знайдено у словнику.");
        }
    }

    // Метод для перегляду всіх слів
    public void displayAllWords() {
        if (dictionary.isEmpty()) {
            System.out.println("Словник порожній.");
        } else {
            dictionary.forEach((enWord, ukWord) ->
                    System.out.println(enWord + " - " + ukWord));
        }
    }

    // Метод для перекладу фрази з урахуванням напряму
    public String translatePhrase(String phrase, String direction) {
        StringBuilder translation = new StringBuilder();
        String[] words = phrase.split(" ");

        for (String word : words) {
            String cleanedWord = cleanWord(word);
            String translatedWord = "";

            // Перевірка напряму перекладу та переклад
            if (direction.equals("en-uk")) {
                translatedWord = dictionary.getOrDefault(cleanedWord, "[" + word + "]");
            } else if (direction.equals("uk-en")) {
                translatedWord = dictionary.entrySet().stream()
                        .filter(entry -> entry.getValue().equals(cleanedWord))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse("[" + word + "]");
            }

            // Відновлюємо розділові знаки після перекладу
            String result = restorePunctuation(word, translatedWord);
            translation.append(result).append(" ");
        }

        return translation.toString().trim();
    }

    // Метод для перевірки англійського слова
    public boolean isEnglishWord(String word) {
        return !word.matches("[a-zA-Z]+");
    }

    // Метод для перевірки українського слова
    public boolean isUkrainianWord(String word) {
        return !word.matches("[а-яА-ЯіІїЇєЄґҐ]+");
    }

    // Метод для очищення слова від розділових знаків
    private String cleanWord(String word) {
        return word.replaceAll("[^a-zA-Zа-яА-ЯіІїЇєЄґҐ]", "").toLowerCase();
    }

    // Метод для відновлення розділових знаків
    private String restorePunctuation(String originalWord, String translatedWord) {
        // Зберігаємо лапки окремо, якщо вони є в слові
        String punctuation = originalWord.replaceAll("[a-zA-Zа-яА-ЯіІїЇєЄґҐ]", "");

        // Обробка лапок
        if (punctuation.contains("\"")) {
            punctuation = punctuation.replace("\"", "");
            translatedWord = "\"" + translatedWord + "\"";
        }

        return translatedWord + punctuation;
    }

}
