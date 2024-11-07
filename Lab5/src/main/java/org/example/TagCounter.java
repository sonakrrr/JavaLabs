package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.Scanner;

public class TagCounter {

    public Map<String, Integer> countTags(String urlString) throws IOException {
        Map<String, Integer> tagCounts = new HashMap<>();

        try {
            URI uri = new URI(urlString);
            URL url = uri.toURL();
            try (Scanner scanner = new Scanner(url.openStream())) {
                while (scanner.hasNext()) {
                    String word = scanner.next();
                    if (word.startsWith("<") && word.endsWith(">")) {
                        tagCounts.put(word, tagCounts.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error processing URL: " + e.getMessage());
        }
        return tagCounts;
    }

    public void printTagsSortedByAlphabet(Map<String, Integer> tagCounts, StringBuilder outputBuilder) {
        tagCounts.keySet().stream()
                .sorted()
                .forEach(tag -> {
                    System.out.println(tag + ": " + tagCounts.get(tag));
                    outputBuilder.append(tag).append(": ").append(tagCounts.get(tag)).append("\n");
                });
    }

    public void printTagsSortedByFrequency(Map<String, Integer> tagCounts, StringBuilder outputBuilder) {
        tagCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                    outputBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                });
    }
}