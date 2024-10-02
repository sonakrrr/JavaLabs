package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CircleTest {

    @Test
    public void testCalcArea() {
        Circle circle = new Circle("Red", 5);
        double expectedArea = 78.5; // PI * 5^2 rounded to 1 decimal place
        assertEquals(expectedArea, circle.calcArea(), "Area calculation should be correct.");
    }

    @Test
    public void testToString() {
        Circle circle = new Circle("Blue", 3);
        String expectedString = "Circle [color=Blue, radius=3.0, area=28.3]"; // PI * 3^2 rounded to 1 decimal place
        assertEquals(expectedString, circle.toString(), "toString should return the correct string representation.");
    }


    @Test
    public void testAreaWithDifferentRadii() {
        Circle smallCircle = new Circle("Black", 1);
        Circle largeCircle = new Circle("Black", 10);
        assertTrue(smallCircle.calcArea() < largeCircle.calcArea(), "Area of larger circle should be greater.");
    }
}
