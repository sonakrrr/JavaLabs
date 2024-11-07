package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler implements Serializable {

    private String content;
    private String filePath;

    public FileHandler() {}

    // Зберігаємо вміст у файл як серіалізований об'єкт
    public void saveToFile(String saveFilePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFilePath))) {
            oos.writeObject(this);  // Серіалізуємо весь об'єкт, включаючи шлях і вміст
        }
    }

    // Метод для зчитування рядків із текстового файлу
    public List<String> readLinesFromFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        this.content = String.join("\n", lines);  // Зберігаємо вміст файлу
        this.filePath = filePath;  // Зберігаємо шлях до файлу
        return lines;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
