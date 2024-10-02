package org.example;

public class ShapeView {
    public void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1 - Display all shapes");
        System.out.println("2 - Calculate total area");
        System.out.println("3 - Calculate area by shape type");
        System.out.println("4 - Sort shapes by area");
        System.out.println("5 - Sort shapes by color");
        System.out.println("6 - Save shapes to text file");
        System.out.println("7 - Load shapes from text file");
        System.out.println("0 - Exit");
        System.out.print("Choose an option: ");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayTotalArea(double totalArea) {
        System.out.printf("Total area of all shapes: %.1f\n", totalArea);
    }

    public void displayAreaByType(String shapeType, double area) {
        System.out.printf("Total area of %s: %.1f\n", shapeType, area);
    }
}
