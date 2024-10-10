package org.example;

import java.io.*;

public class ShapeFileHandler {

    // Метод для збереження фігур у файл
    public static void saveShapes(String filePath, Shape[] shapes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(shapes);
            System.out.println("Shapes successfully saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving shapes to file: " + e.getMessage());
        }
    }

    // Метод для завантаження фігур з файлу
    public static Shape[] loadShapes(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Shape[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading shapes from file: " + e.getMessage());
            return new Shape[0]; // Повертаємо порожній масив у разі помилки
        }
    }
}

