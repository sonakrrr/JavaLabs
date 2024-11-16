package org.example;

import java.util.concurrent.ForkJoinPool;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int[] numbers = new int[1_000_000];
        Random rng = new Random();

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rng.nextInt(101);
        }

        long totalSum;
        try (ForkJoinPool pool = new ForkJoinPool()) {
            SumOfArrayElem sumTask = new SumOfArrayElem(numbers, 0, numbers.length);

            totalSum = pool.invoke(sumTask);
        }
        System.out.println("The total sum of array elements: " + totalSum);
    }
}
