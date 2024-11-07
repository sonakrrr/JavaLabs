package org.example;

public abstract class Hoofed extends Animal {
    private int hoofCount; // Кількість копит

    public Hoofed(String name, int age, int hoofCount) {
        super(name, age);
        this.hoofCount = hoofCount;
    }

    public int getHoofCount() {
        return hoofCount;
    }

    public abstract void graze(); // Метод для пасіння
}