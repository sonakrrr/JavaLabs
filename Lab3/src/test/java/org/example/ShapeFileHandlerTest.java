package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ShapeFileHandlerTest {

    private final String testFilePath = "test_shapes.ser";
    private Shape[] testShapes;

    @BeforeEach
    void setUp() {
        // Ініціалізуємо масив тестових фігур
        testShapes = new Shape[]{
                new Rectangle("Red", 2.0, 3.0),
                new Circle("Blue", 1.5),
                new Triangle("Green", 4.0, 2.5)
        };
    }

    @AfterEach
    void tearDown() {
        // Видаляємо тестовий файл після кожного тесту, якщо він існує
        File file = new File(testFilePath);
        if (file.exists()) {
            assertTrue(file.delete(), "Failed to delete test file");
        }
    }

    @Test
    void testSaveShapes() {
        // Тестуємо, чи правильно зберігаються фігури у файл
        ShapeFileHandler.saveShapes(testFilePath, testShapes);

        File file = new File(testFilePath);
        assertTrue(file.exists(), "File should be created after saving shapes");
    }

    @Test
    void testLoadShapesFromNonExistentFile() {
        // Тестуємо завантаження фігур із неіснуючого файлу
        Shape[] loadedShapes = ShapeFileHandler.loadShapes("non_existent_file.ser");

        // Перевіряємо, чи повертається порожній масив
        assertNotNull(loadedShapes, "Loaded shapes should not be null");
        assertEquals(0, loadedShapes.length, "Loaded shapes array should be empty when file does not exist");
    }

    @Test
    void testSaveShapesWithIOException() {
        // Тестуємо збереження фігур у файл, куди неможливо записати
        String invalidFilePath = "/invalid_path/test_shapes.ser";
        assertDoesNotThrow(() -> ShapeFileHandler.saveShapes(invalidFilePath, testShapes),
                "IOException should be handled gracefully");
    }

    @Test
    void testLoadShapesWithIOException() {
        // Тестуємо завантаження фігур із невалідного шляху
        String invalidFilePath = "/invalid_path/test_shapes.ser";
        assertDoesNotThrow(() -> ShapeFileHandler.loadShapes(invalidFilePath),
                "IOException should be handled gracefully when loading from invalid path");
    }
}
