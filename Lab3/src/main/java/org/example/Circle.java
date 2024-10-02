package org.example;

import java.io.Serializable;

public class Circle extends Shape implements Serializable {
    private double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        double area = Math.PI * Math.pow(radius, 2);
        return roundToTenth(area);
    }

    @Override
    public String toString() {
        return "Circle [color=" + getShapeColor() + ", radius=" + radius + ", area=" + calcArea() + "]";
    }

    double roundToTenth(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    @Override
    public void draw() {

    }
}
