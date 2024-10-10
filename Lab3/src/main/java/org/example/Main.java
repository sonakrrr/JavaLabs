package org.example;

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
            int choice = getValidInput(scanner);

            if (choice == -1) continue; // Пропускаємо некоректне введення

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
                    int shapeChoice = getValidInput(scanner);
                    Class<?> selectedShapeType = getShapeType(shapeChoice);
                    if (selectedShapeType != null) {
                        double totalSelectedShapeArea = controller.calculateAreaByType(selectedShapeType);
                        view.displayAreaByType(selectedShapeType.getSimpleName(), totalSelectedShapeArea);
                    } else {
                        view.displayMessage("Invalid choice! No area calculation will be performed.");
                    }
                    break;
                case 4:
                    controller.sortShapesByArea();
                    view.displayMessage("Shapes sorted by area:");
                    controller.displayShapes();
                    break;
                case 5:
                    controller.sortShapesByColor();
                    view.displayMessage("Shapes sorted by color:");
                    controller.displayShapes();
                    break;
                case 6:
                    view.displayMessage("Enter file path to save shapes:");
                    String savePath = scanner.next();
                    ShapeFileHandler.saveShapes(savePath, controller.getShapes());
                    break;
                case 7:
                    view.displayMessage("Enter file path to load shapes:");
                    String loadPath = scanner.next();
                    shapes = ShapeFileHandler.loadShapes(loadPath);
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

    static Shape[] generateRandomShapes(int number) {
        Shape[] shapes = new Shape[number];
        for (int i = 0; i < number; i++) {
            int randomChoice = (int) (Math.random() * 3); // Випадкове число від 0 до 2
            switch (randomChoice) {
                case 0:
                    shapes[i] = new Rectangle("Red", Math.random() * 10 + 1, Math.random() * 10 + 1);
                    break;
                case 1:
                    shapes[i] = new Triangle("Blue", Math.random() * 10 + 1, Math.random() * 10 + 1);
                    break;
                case 2:
                    shapes[i] = new Circle("Green", Math.random() * 10 + 1);
                    break;
            }
        }
        return shapes;
    }

    static Class<?> getShapeType(int choice) {
        return switch (choice) {
            case 1 -> Rectangle.class;
            case 2 -> Triangle.class;
            case 3 -> Circle.class;
            default -> null;
        };
    }

    static int getValidInput(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Очищення некоректного введення
            return -1;
        }
    }
}
