package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testToStringMethod() {
        LocalDate birthDate = LocalDate.of(167, 3, 7);
        Main.Student student = new Main.Student("Aurelius", "Marcus", birthDate, "0000001488", "Roman Empire, 1");

        String expectedString = "Student: Aurelius Marcus\n" +
                "Birth Date: 0167-03-07\n" +
                "Phone Number: 0000001488\n" +
                "Address: Roman Empire, 1";

        assertEquals(expectedString, student.toString());
    }

    @Test
    void testValidPhoneNumber() {
        String validPhoneNumber = "0000001488";
        String pattern = "\\d{10}";
        assertTrue(validPhoneNumber.matches(pattern), "The phone number should be valid.");
    }

    @Test
    void testInvalidPhoneNumber() {
        String invalidPhoneNumber = "000000148";
        String pattern = "\\d{10}";
        assertFalse(invalidPhoneNumber.matches(pattern), "The phone number should be invalid.");

        invalidPhoneNumber = "00000014888";
        assertFalse(invalidPhoneNumber.matches(pattern), "The phone number should be invalid.");

        invalidPhoneNumber = "qwerty1488";
        assertFalse(invalidPhoneNumber.matches(pattern), "The phone number should be invalid.");
    }

    @Test
    void testInputWithValidationIncorrectInput() {
        String input = "Aurelius228";
        String pattern = "[\\p{L}’`'-]+";
        assertFalse(input.matches(pattern));
    }

    @Test
    void testInputWithValidationCorrectInput() {
        String input = "Marcus";
        String pattern = "[\\p{L}’`'-]+";
        assertTrue(input.matches(pattern));
    }

    @Test
    void testRemoveStudent() {
        // Add a student
        LocalDate birthDate = LocalDate.of(2000, 5, 15);
        Main.Student student = new Main.Student("Aurelius", "Marcus", birthDate, "0000001488", "Roman Empire, 1");
        Main.studentList.add(student);

        assertFalse(Main.studentList.isEmpty());

        String lastNameToRemove = "Aurelius";
        String firstNameToRemove = "Marcus";

        Main.studentList.removeIf(s -> s.lastName.equalsIgnoreCase(lastNameToRemove) && s.firstName.equalsIgnoreCase(firstNameToRemove));

        assertTrue(Main.studentList.stream().noneMatch(s -> s.lastName.equalsIgnoreCase(lastNameToRemove) && s.firstName.equalsIgnoreCase(firstNameToRemove)));
    }

    @Test
    void testInputBirthDateInvalid() {
        String invalidDate = "07.03.0167";
        assertThrows(Exception.class, () -> {
            LocalDate.parse(invalidDate, Main.dateFormatter);
        });
    }

    @Test
    void testInputBirthDateValid() {
        LocalDate expectedDate = LocalDate.of(167, 3, 7);
        String inputDate = "0167-03-07";

        LocalDate actualDate = LocalDate.parse(inputDate, Main.dateFormatter);
        assertEquals(expectedDate, actualDate);
    }
}
