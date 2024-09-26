package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testToBinary() {
        assertEquals("0", Main.toBinary.apply(0));
        assertEquals("1", Main.toBinary.apply(1));
        assertEquals("1010", Main.toBinary.apply(10));
        assertEquals("11111111", Main.toBinary.apply(255));
    }

    @Test
    public void testToOctal() {
        assertEquals("0", Main.toOctal.apply(0));
        assertEquals("1", Main.toOctal.apply(1));
        assertEquals("12", Main.toOctal.apply(10));
        assertEquals("377", Main.toOctal.apply(255));
    }

    @Test
    public void testToHexadecimal() {
        assertEquals("0", Main.toHexadecimal.apply(0));
        assertEquals("1", Main.toHexadecimal.apply(1));
        assertEquals("A", Main.toHexadecimal.apply(10));
        assertEquals("FF", Main.toHexadecimal.apply(255));
    }

    @Test
    public void testNegativeInput() {
        assertEquals("0", Main.toBinary.apply(-10));
        assertEquals("0", Main.toOctal.apply(-10));
        assertEquals("0", Main.toHexadecimal.apply(-10));
    }
}
