package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeControllerTest {
    private ShapeController shapeController;
    private Shape[] shapes;

    @BeforeEach
    public void setUp() {
        shapes = new Shape[] {
                new Rectangle("red", 2.0, 3.0),   // Площа = 2.0 * 3.0 = 6.0
                new Circle("blue", 2.0),          // Площа = π * 2.0^2 ≈ 12.57
                new Triangle("green", 3.0, 4.0)   // Площа = 0.5 * 3.0 * 4.0 = 6.0
        };
        shapeController = new ShapeController(shapes);
    }

    @Test
    public void testCalculateTotalArea() {
        // Перевіряємо загальну площу
        double expectedTotalArea = 6.0 + (Math.PI * Math.pow(2.0, 2)) + 6.0; // 6.0 + 12.566370614359172 + 6.0
        assertEquals(expectedTotalArea, shapeController.calculateTotalArea(), 0.1);
    }

    @Test
    public void testCalculateAreaByType() {
        // Перевіряємо площу для трикутника
        double expectedTriangleArea = 6.0; // Площа трикутника
        assertEquals(expectedTriangleArea, shapeController.calculateAreaByType(Triangle.class), 0.1);

        // Перевіряємо площу для кола
        double expectedCircleArea = Math.PI * Math.pow(2.0, 2); // Площа кола
        assertEquals(expectedCircleArea, shapeController.calculateAreaByType(Circle.class), 0.1);

        // Перевіряємо площу для прямокутника
        double expectedRectangleArea = 6.0; // Площа прямокутника
        assertEquals(expectedRectangleArea, shapeController.calculateAreaByType(Rectangle.class), 0.1);
    }

    @Test
    public void testSortShapesByArea() {
        shapeController.sortShapesByArea();
        Shape[] sortedShapes = shapeController.getShapes();

        // Перевірка, що фігури відсортовані за площею
        assertTrue(sortedShapes[0].calcArea() <= sortedShapes[1].calcArea());
        assertTrue(sortedShapes[1].calcArea() <= sortedShapes[2].calcArea());
    }

    @Test
    public void testSortShapesByColor() {
        shapeController.sortShapesByColor();
        Shape[] sortedShapes = shapeController.getShapes();

        // Перевірка, що фігури відсортовані за кольором
        assertEquals("blue", sortedShapes[0].getShapeColor());
        assertEquals("green", sortedShapes[1].getShapeColor());
        assertEquals("red", sortedShapes[2].getShapeColor());
    }

}
