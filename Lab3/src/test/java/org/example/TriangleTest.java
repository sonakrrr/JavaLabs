package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    @Test
    public void testCalcArea() {
        Triangle triangle = new Triangle("Red", 6, 8);
        double expectedArea = 24.0; // 0.5 * base * height = 0.5 * 6 * 8
        assertEquals(expectedArea, triangle.calcArea(), "Area calculation should be correct.");
    }

    @Test
    public void testToString() {
        Triangle triangle = new Triangle("Blue", 4, 3);
        String expectedString = "Triangle [color=Blue, base=4.0, height=3.0, area=6.0]"; // 0.5 * 4 * 3
        assertEquals(expectedString, triangle.toString(), "toString should return the correct string representation.");
    }

    @Test
    public void testConstructor() {
        Triangle triangle = new Triangle("Green", 5, 2);
        assertEquals("Green", triangle.getShapeColor(), "Constructor should set the shape color.");
        assertEquals(5, triangle.base, "Constructor should set the base correctly.");
        assertEquals(2, triangle.height, "Constructor should set the height correctly.");
    }

    @Test
    public void testAreaWithDifferentDimensions() {
        Triangle smallTriangle = new Triangle("Black", 2, 3);
        Triangle largeTriangle = new Triangle("Black", 10, 5);
        assertTrue(smallTriangle.calcArea() < largeTriangle.calcArea(), "Area of larger triangle should be greater.");
    }

    @Test
    public void testAreaWithZeroDimensions() {
        Triangle triangleWithZeroBase = new Triangle("Yellow", 0, 5);
        Triangle triangleWithZeroHeight = new Triangle("Yellow", 5, 0);
        Triangle triangleWithBothZero = new Triangle("Yellow", 0, 0);

        assertEquals(0.0, triangleWithZeroBase.calcArea(), "Area should be 0 when base is 0.");
        assertEquals(0.0, triangleWithZeroHeight.calcArea(), "Area should be 0 when height is 0.");
        assertEquals(0.0, triangleWithBothZero.calcArea(), "Area should be 0 when both dimensions are 0.");
    }
}
