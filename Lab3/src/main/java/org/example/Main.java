package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Створюємо набір даних фігур випадковим чином
        Shape[] shapes = generateRandomShapes(10);

        // Створюємо контролер і вид
        ShapeController controller = new ShapeController(shapes);
        ShapeView view = new ShapeView();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            view.displayMenu(); // Відображення меню
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    view.displayMessage("Displaying all shapes:");
                    controller.displayShapes();
                    break;
                case 2:
                    double totalArea = controller.calculateTotalArea();
                    view.displayTotalArea(totalArea);
                    break;
                case 3:
                    view.displayMessage("Choose the type of shape to calculate total area:");
                    view.displayMessage("1 - Rectangle, 2 - Triangle, 3 - Circle");
                    int shapeChoice = scanner.nextInt();
                    Class<?> selectedShapeType = getShapeType(shapeChoice);
                    if (selectedShapeType != null) {
                        double totalSelectedShapeArea = controller.calculateAreaByType(selectedShapeType);
                        view.displayAreaByType(selectedShapeType.getSimpleName(), totalSelectedShapeArea);
                    } else {
                        view.displayMessage("Invalid choice! No area calculation will be performed.");
                    }
                    break;
                case 4:
                    // Сортування фігур за площею
                    controller.sortShapesByArea();
                    view.displayMessage("Shapes sorted by area:");
                    controller.displayShapes();
                    break;
                case 5:
                    // Сортування фігур за кольором
                    controller.sortShapesByColor();
                    view.displayMessage("Shapes sorted by color:");
                    controller.displayShapes();
                    break;
                case 6:
                    // Зберегти фігури у текстовий файл
                    saveShapesToTextFile(controller.getShapes());
                    break;
                case 7:
                    // Завантажити фігури з текстового файлу
                    view.displayMessage("Enter the filename to load shapes from (e.g., shapes.txt):");
                    String filename = scanner.next();
                    shapes = loadShapesFromTextFile(filename);
                    controller = new ShapeController(shapes); // Оновлюємо контролер
                    break;
                case 0:
                    exit = true; // Вихід з програми
                    break;
                default:
                    view.displayMessage("Invalid choice! Please try again.");
            }
        }
    }

    // Метод для створення випадкових фігур
    private static Shape[] generateRandomShapes(int number) {
        Shape[] shapes = new Shape[number];
        Random random = new Random();
        String[] colors = {"Red", "Green", "Blue", "Yellow", "Black", "White", "Purple"};

        for (int i = 0; i < number; i++) {
            String color = colors[random.nextInt(colors.length)];
            int shapeType = random.nextInt(3); // 0, 1, or 2

            switch (shapeType) {
                case 0: // Rectangle
                    double width = Math.round((random.nextDouble() * 10 + 1) * 10.0) / 10.0; // Random width
                    double height = Math.round((random.nextDouble() * 10 + 1) * 10.0) / 10.0; // Random height
                    shapes[i] = new Rectangle(color, width, height);
                    break;
                case 1: // Triangle
                    double base = Math.round((random.nextDouble() * 10 + 1) * 10.0) / 10.0; // Random base
                    double triHeight = Math.round((random.nextDouble() * 10 + 1) * 10.0) / 10.0; // Random height
                    shapes[i] = new Triangle(color, base, triHeight);
                    break;
                case 2: // Circle
                    double radius = Math.round((random.nextDouble() * 10 + 1) * 10.0) / 10.0; // Random radius
                    shapes[i] = new Circle(color, radius);
                    break;
            }
        }
        return shapes;
    }

    // Метод для збереження фігур у текстовий файл
    private static void saveShapesToTextFile(Shape[] shapes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("shapes.txt"))) {
            for (Shape shape : shapes) {
                writer.write(shape.toString());
                writer.newLine(); // Перехід на новий рядок
            }
            System.out.println("Shapes saved to shapes.txt");
        } catch (IOException e) {
            System.out.println("Error saving shapes to text file: " + e.getMessage());
        }
    }

    private static Shape[] loadShapesFromTextFile(String filename) {
        List<Shape> shapeList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Відокремлюємо тип фігури від інших параметрів
                String[] parts = line.split("\\["); // Розділяємо на тип фігури і параметри
                String shapeType = parts[0].trim(); // Отримуємо тип фігури (Triangle, Circle, Rectangle)
                String params = parts[1].replaceAll("]", "").trim(); // Отримуємо параметри (колір, розміри)

                // Розділяємо параметри
                String[] attributes = params.split(", ");
                String color = attributes[0].split("=")[1].trim(); // Отримуємо колір

                switch (shapeType) {
                    case "Triangle":
                        double base = Double.parseDouble(attributes[1].split("=")[1].trim());
                        double height = Double.parseDouble(attributes[2].split("=")[1].trim());
                        shapeList.add(new Triangle(color, base, height));
                        break;
                    case "Circle":
                        double radius = Double.parseDouble(attributes[1].split("=")[1].trim());
                        shapeList.add(new Circle(color, radius));
                        break;
                    case "Rectangle":
                        double width = Double.parseDouble(attributes[1].split("=")[1].trim());
                        double rectHeight = Double.parseDouble(attributes[2].split("=")[1].trim());
                        shapeList.add(new Rectangle(color, width, rectHeight));
                        break;
                    default:
                        System.out.println("Unknown shape type: " + shapeType);
                }
            }
            System.out.println("Shapes loaded from " + filename);

            // Відображення фігур у консолі
            System.out.println("Loaded Shapes:");
            for (Shape shape : shapeList) {
                System.out.println(shape); // Виводимо фігури в консоль
            }
            return shapeList.toArray(new Shape[0]); // Повертаємо масив
        } catch (IOException e) {
            System.out.println("Error loading shapes from text file: " + e.getMessage());
            return new Shape[0]; // Повертаємо порожній масив у разі помилки
        }
    }

    // Допоміжний метод для отримання типу фігури
    private static Class<?> getShapeType(int choice) {
        return switch (choice) {
            case 1 -> Rectangle.class;
            case 2 -> Triangle.class;
            case 3 -> Circle.class;
            default -> null;
        };
    }
}
