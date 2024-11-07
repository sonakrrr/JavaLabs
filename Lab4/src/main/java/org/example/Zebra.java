package org.example;

public class Zebra extends Hoofed {
    public Zebra(String name, int age) {
        super(name, age, 4); // Зебри також мають 4 копита
    }

    @Override
    public void graze() {
        System.out.println(getName() + " is grazing on the grass.");
    }
}