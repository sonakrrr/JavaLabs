package org.example;

import java.util.Comparator;

public class ShapeController {
    private Shape[] shapes;

    public ShapeController(Shape[] shapes) {
        this.shapes = shapes;
    }

    public Shape[] getShapes() {
        return shapes; // Додано метод для отримання масиву фігур
    }

    // Відображення всіх фігур
    public void displayShapes() {
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    // Обчислення загальної площі всіх фігур з округленням до десятих
    public double calculateTotalArea() {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.calcArea();
        }
        return roundToTenth(totalArea); // Округлення загальної площі до десятих
    }

    // Обчислення загальної площі фігур певного типу з округленням до десятих
    public double calculateAreaByType(Class<?> shapeType) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            if (shapeType.isInstance(shape)) {
                totalArea += shape.calcArea();
            }
        }
        return roundToTenth(totalArea); // Округлення площі до десятих
    }

    // Функція для округлення до десятих
    private double roundToTenth(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    // Сортування фігур за площею
    public void sortShapesByArea() {
        java.util.Arrays.sort(shapes, Comparator.comparingDouble(Shape::calcArea));
    }

    // Сортування фігур за кольором
    public void sortShapesByColor() {
        java.util.Arrays.sort(shapes, Comparator.comparing(Shape::getShapeColor));
    }

    // Відображення відсортованих фігур
    public void displaySortedShapes() {
        displayShapes(); // Виклик для відображення фігур
    }
}
