package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SumOfArrayElemTest {

    @Test
    public void testSmallArray() {
        int[] smallArray = {1, 2, 3, 4, 5};
        SumOfArrayElem task = new SumOfArrayElem(smallArray, 0, smallArray.length);
        long sum = task.compute();
        assertEquals(15, sum);
    }

    @Test
    public void testLargeArray() {
        int[] largeArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        SumOfArrayElem task = new SumOfArrayElem(largeArray, 0, largeArray.length);
        long sum = task.compute();
        assertEquals(300, sum);
    }

    @Test
    public void testEmptyArray() {
        int[] emptyArray = {};
        SumOfArrayElem task = new SumOfArrayElem(emptyArray, 0, emptyArray.length);
        long sum = task.compute();
        assertEquals(0, sum);
    }

    @Test
    public void testIdenticalElements() {
        int[] identicalArray = {10, 10, 10, 10, 10};
        SumOfArrayElem task = new SumOfArrayElem(identicalArray, 0, identicalArray.length);
        long sum = task.compute();
        assertEquals(50, sum);
    }

    @Test
    public void testLargeValues() {
        int[] largeValuesArray = {1000, 2000, 3000, 4000, 5000};
        SumOfArrayElem task = new SumOfArrayElem(largeValuesArray, 0, largeValuesArray.length);
        long sum = task.compute();
        assertEquals(15000, sum);
    }

    @Test
    public void testNegativeValues() {
        int[] negativeArray = {-1, -2, -3, -4, -5};
        SumOfArrayElem task = new SumOfArrayElem(negativeArray, 0, negativeArray.length);
        long sum = task.compute();
        assertEquals(-15, sum);
    }
}
