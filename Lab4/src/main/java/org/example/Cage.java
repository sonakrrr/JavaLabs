package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cage<T extends Animal> {
    private final int capacity;
    private final List<T> animals;

    public Cage(int capacity) {
        this.capacity = capacity;
        animals = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupiedPlaces() {
        return animals.size();
    }

    public void addAnimal(T animal) throws Exception {
        if (animals.size() >= capacity) {
            throw new Exception("Вольєр переповнений!");
        }
        animals.add(animal);
    }

    public void removeAnimal(T animal) throws Exception {
        if (!animals.contains(animal)) {
            throw new Exception("Тварина не знайдена у вольєрі!");
        }
        animals.remove(animal);
    }

    public List<T> getAnimals() {
        return animals;
    }
}
