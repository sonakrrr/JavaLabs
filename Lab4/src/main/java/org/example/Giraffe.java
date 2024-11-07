package org.example;

public class Giraffe extends Hoofed {
    public Giraffe(String name, int age) {
        super(name, age, 4); // Жирафи мають 4 копита
    }

    @Override
    public void graze() {
        System.out.println(getName() + " is grazing on the leaves of tall trees.");
    }
}