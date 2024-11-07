package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler implements Serializable {

    private String content;
    private String filePath;

    public FileHandler() {}

    public void saveToFile(String saveFilePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFilePath))) {
            oos.writeObject(this);
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
        this.content = String.join("\n", lines);
        this.filePath = filePath;
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
