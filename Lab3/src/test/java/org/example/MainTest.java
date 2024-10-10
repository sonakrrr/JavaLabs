package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private Shape[] shapes;

    @BeforeEach
    void setUp() {
        shapes = new Shape[]{
                new Rectangle("Red", 5, 3),
                new Triangle("Blue", 4, 2),
                new Circle("Green", 2)
        };
    }

    @Test
    void testGenerateRandomShapes() {
        Shape[] generatedShapes = Main.generateRandomShapes(10);
        assertEquals(10, generatedShapes.length);
        for (Shape shape : generatedShapes) {
            assertNotNull(shape);
        }
    }

    @Test
    void testGetShapeType() {
        assertEquals(Rectangle.class, Main.getShapeType(1));
        assertEquals(Triangle.class, Main.getShapeType(2));
        assertEquals(Circle.class, Main.getShapeType(3));
        assertNull(Main.getShapeType(4)); // Test invalid input
    }

    @Test
    void testValidInput() {
        String input = "5\n";
        Scanner scanner = new Scanner(input);
        int result = Main.getValidInput(scanner);
        assertEquals(5, result);
    }

    @Test
    void testInvalidInput() {
        String input = "invalid\n3\n";
        Scanner scanner = new Scanner(input);
        int result = Main.getValidInput(scanner);
        assertEquals(-1, result); // Ensure it reads the next valid input
    }

    @Test
    void testCalculateTotalArea() {
        ShapeController controller = new ShapeController(shapes);
        double expectedTotalArea = 5 * 3 + 0.5 * 4 * 2 + Math.PI * Math.pow(2, 2);
        double totalArea = controller.calculateTotalArea();
        assertEquals(expectedTotalArea, totalArea, 0.1); // Allow a small delta for floating point comparison
    }
}
