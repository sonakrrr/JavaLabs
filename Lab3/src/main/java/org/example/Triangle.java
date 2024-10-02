package org.example;

import java.io.Serializable;

public class Triangle extends Shape implements Serializable {
    double base;
    double height;

    public Triangle(String shapeColor, double base, double height) {
        super(shapeColor);
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        double area = 0.5 * base * height;
        return roundToTenth(area);
    }

    @Override
    public String toString() {
        return "Triangle [color=" + getShapeColor() + ", base=" + base + ", height=" + height + ", area=" + calcArea() + "]";
    }

    private double roundToTenth(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    @Override
    public void draw() {

    }
}