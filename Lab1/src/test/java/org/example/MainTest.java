package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testToBinary() {
        assertEquals("0", Main.toBinary(0));
        assertEquals("1", Main.toBinary(1));
        assertEquals("1010", Main.toBinary(10));
        assertEquals("11111111", Main.toBinary(255));
    }

    @Test
    public void testToOctal() {
        assertEquals("0", Main.toOctal(0));
        assertEquals("1", Main.toOctal(1));
        assertEquals("12", Main.toOctal(10));
        assertEquals("377", Main.toOctal(255));
    }

    @Test
    public void testToHexadecimal() {
        assertEquals("0", Main.toHexadecimal(0));
        assertEquals("1", Main.toHexadecimal(1));
        assertEquals("A", Main.toHexadecimal(10));
        assertEquals("FF", Main.toHexadecimal(255));
    }

    @Test
    public void testNegativeInput() {
        assertEquals("0", Main.toBinary(-10));
        assertEquals("0", Main.toOctal(-10));
        assertEquals("0", Main.toHexadecimal(-10));
    }
}
