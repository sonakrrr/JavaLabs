package org.example;

import java.io.Serializable;

public abstract class Shape implements Drawable, Serializable {
    private String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    // Абстрактний метод для обчислення площі
    public abstract double calcArea();

    // Метод для отримання кольору фігури
    public String getShapeColor() {
        return shapeColor;
    }

    @Override
    public String toString() {
        return "Shape [color=" + shapeColor + "]";
    }
}
