package org.example;

import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private List<Cage<? extends Animal>> cages = new ArrayList<>();

    public void addCage(Cage<? extends Animal> cage) {
        cages.add(cage);
    }

    public List<Cage<? extends Animal>> getCages() {
        return cages;
    }

    public int getCountOfAnimals() {
        int totalAnimals = 0;
        for (Cage<? extends Animal> cage : cages) {
            totalAnimals += cage.getOccupiedPlaces();
        }
        return totalAnimals;
    }
}
