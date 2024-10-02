package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RectangleTest {

    @Test
    public void testCalcArea() {
        Rectangle rectangle = new Rectangle("Red", 5, 10);
        double expectedArea = 50.0; // 5 * 10
        assertEquals(expectedArea, rectangle.calcArea(), "Area calculation should be correct.");
    }

    @Test
    public void testToString() {
        Rectangle rectangle = new Rectangle("Blue", 4, 3);
        String expectedString = "Rectangle [color=Blue, width=4.0, height=3.0, area=12.0]"; // 4 * 3
        assertEquals(expectedString, rectangle.toString(), "toString should return the correct string representation.");
    }

    @Test
    public void testConstructor() {
        Rectangle rectangle = new Rectangle("Green", 6, 2);
        assertEquals("Green", rectangle.getShapeColor(), "Constructor should set the shape color.");
        assertEquals(6, rectangle.width, "Constructor should set the width correctly.");
        assertEquals(2, rectangle.height, "Constructor should set the height correctly.");
    }

    @Test
    public void testAreaWithDifferentDimensions() {
        Rectangle smallRectangle = new Rectangle("Black", 2, 3);
        Rectangle largeRectangle = new Rectangle("Black", 10, 5);
        assertTrue(smallRectangle.calcArea() < largeRectangle.calcArea(), "Area of larger rectangle should be greater.");
    }

    @Test
    public void testAreaWithZeroDimensions() {
        Rectangle rectangleWithZeroWidth = new Rectangle("Yellow", 0, 5);
        Rectangle rectangleWithZeroHeight = new Rectangle("Yellow", 5, 0);
        Rectangle rectangleWithBothZero = new Rectangle("Yellow", 0, 0);

        assertEquals(0.0, rectangleWithZeroWidth.calcArea(), "Area should be 0 when width is 0.");
        assertEquals(0.0, rectangleWithZeroHeight.calcArea(), "Area should be 0 when height is 0.");
        assertEquals(0.0, rectangleWithBothZero.calcArea(), "Area should be 0 when both dimensions are 0.");
    }
}
