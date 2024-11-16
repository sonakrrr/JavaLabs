package org.example;

import java.util.concurrent.RecursiveTask;

public class SumOfArrayElem extends RecursiveTask<Long> {
    private final int[] numbers;
    private final int startIndex;
    private final int endIndex;
    private static final int THRESHOLD = 20;

    public SumOfArrayElem(int[] numbers, int startIndex, int endIndex) {
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Long compute() {
        int length = endIndex - startIndex;

        if (length <= THRESHOLD) {
            long sum = 0;
            for (int i = startIndex; i < endIndex; i++) {
                sum += numbers[i];
            }
            return sum;
        } else {
            int middle = startIndex + length / 2;
            SumOfArrayElem leftTask = new SumOfArrayElem(numbers, startIndex, middle);
            SumOfArrayElem rightTask = new SumOfArrayElem(numbers, middle, endIndex);

            leftTask.fork();
            rightTask.fork();

            long leftResult = leftTask.join();
            long rightResult = rightTask.join();

            return leftResult + rightResult;
        }
    }
}
