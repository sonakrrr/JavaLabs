package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    final FileHandler fileHandler;
    private final EncryptDecrypt encryptDecrypt;
    final TagCounter tagCounter;
    private String currentFilePath = "";
    private String consoleOutput = ""; // Змінна для збереження тексту з консолі

    public Menu() {
        fileHandler = new FileHandler();
        encryptDecrypt = new EncryptDecrypt();
        tagCounter = new TagCounter();
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Read File");
            System.out.println("2. Show Longest Line");
            System.out.println("3. Save to File");
            System.out.println("4. Encrypt Input Character Stream");
            System.out.println("5. Decrypt Character Stream");
            System.out.println("6. Count Tags in Lexicographical Order");
            System.out.println("7. Count Tags by Frequency");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    readFile(scanner);
                    break;
                case 2:
                    showLongestLine();
                    break;
                case 3:
                    saveFile(scanner);
                    break;
                case 4:
                    encryptFile(scanner);
                    break;
                case 5:
                    decryptFile(scanner);
                    break;
                case 6:
                    countTagsSortedByAlphabet(scanner);
                    break;
                case 7:
                    countTagsSortedByFrequency(scanner);
                    break;
                case 8:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }

    void readFile(Scanner scanner) {
        System.out.print("Enter the file path to read: ");
        currentFilePath = scanner.nextLine();
        try {
            List<String> lines = fileHandler.readLinesFromFile(currentFilePath);
            StringBuilder outputBuilder = new StringBuilder();
            for (String line : lines) {
                outputBuilder.append(line).append("\n");
                System.out.println(line);
            }
            consoleOutput = outputBuilder.toString();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }



    void showLongestLine() {
        if (currentFilePath.isEmpty()) {
            System.out.println("Please read a file first.");
            return;
        }
        try {
            List<String> lines = fileHandler.readLinesFromFile(currentFilePath);
            String maxLine = lines.stream()
                    .max(Comparator.comparingInt(line -> line.split("\\s+").length))
                    .orElse("The file is empty.");
            System.out.println("Line with the most words: " + maxLine);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    void saveFile(Scanner scanner) {
        if (consoleOutput.isEmpty()) {
            System.out.println("No content available to save. Perform encryption or decryption first.");
            return;
        }
        System.out.print("Enter the file path to save data: ");
        String saveFilePath = scanner.nextLine();
        try {
            fileHandler.saveToFile(saveFilePath);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath))) {
                writer.write(consoleOutput);
            }
            System.out.println("Data saved to file: " + saveFilePath);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    void encryptFile(Scanner scanner) {
        if (currentFilePath.isEmpty()) {
            System.out.println("Please read a file first.");
            return;
        }
        System.out.print("Enter the key character for encryption: ");
        char key = scanner.nextLine().charAt(0);
        String encryptedFilePath = "encrypted.txt";
        try {
            encryptDecrypt.encrypt(currentFilePath, encryptedFilePath, key);
            System.out.println("File has been encrypted.");
            List<String> encryptedLines = fileHandler.readLinesFromFile(encryptedFilePath);
            StringBuilder outputBuilder = new StringBuilder();
            for (String line : encryptedLines) {
                System.out.println(line);
                outputBuilder.append(line).append("\n");
            }
            consoleOutput = outputBuilder.toString(); // Зберігаємо текст у змінну
        } catch (IOException e) {
            System.out.println("Error encrypting file: " + e.getMessage());
        }
    }

    void decryptFile(Scanner scanner) {
        String encryptedFilePath = "encrypted.txt";
        System.out.print("Enter the key character for decryption: ");
        char key = scanner.nextLine().charAt(0);
        String decryptedFilePath = "decrypted.txt";
        try {
            encryptDecrypt.decrypt(encryptedFilePath, decryptedFilePath, key);
            System.out.println("File has been decrypted.");
            List<String> decryptedLines = fileHandler.readLinesFromFile(decryptedFilePath);
            StringBuilder outputBuilder = new StringBuilder();
            for (String line : decryptedLines) {
                System.out.println(line);
                outputBuilder.append(line).append("\n");
            }
            consoleOutput = outputBuilder.toString(); // Зберігаємо текст у змінну
        } catch (IOException e) {
            System.out.println("Error decrypting file: " + e.getMessage());
        }
    }

    void countTagsSortedByAlphabet(Scanner scanner) {
        System.out.print("Enter the URL for tag counting: ");
        String url = scanner.nextLine();
        try {
            Map<String, Integer> tagCounts = tagCounter.countTags(url);
            System.out.println("Tags in alphabetical order:");
            StringBuilder outputBuilder = new StringBuilder();
            tagCounter.printTagsSortedByAlphabet(tagCounts, outputBuilder);
            consoleOutput = outputBuilder.toString(); // Зберігаємо текст у змінну
        } catch (IOException e) {
            System.out.println("Error counting tags: " + e.getMessage());
        }
    }

    void countTagsSortedByFrequency(Scanner scanner) {
        System.out.print("Enter the URL for tag counting: ");
        String url = scanner.nextLine();
        try {
            Map<String, Integer> tagCounts = tagCounter.countTags(url);
            System.out.println("Tags by frequency:");
            StringBuilder outputBuilder = new StringBuilder();
            tagCounter.printTagsSortedByFrequency(tagCounts, outputBuilder);
            consoleOutput = outputBuilder.toString(); // Зберігаємо текст у змінну
        } catch (IOException e) {
            System.out.println("Error counting tags: " + e.getMessage());
        }
    }
}