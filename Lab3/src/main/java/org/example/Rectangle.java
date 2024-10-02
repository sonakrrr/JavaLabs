package org.example;

import java.io.Serializable;

public class Rectangle extends Shape implements Serializable {
    double width;
    double height;

    public Rectangle(String shapeColor, double width, double height) {
        super(shapeColor);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        double area = width * height;
        return roundToTenth(area);
    }

    @Override
    public String toString() {
        return "Rectangle [color=" + getShapeColor() + ", width=" + width + ", height=" + height + ", area=" + calcArea() + "]";
    }

    private double roundToTenth(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    @Override
    public void draw() {

    }
}