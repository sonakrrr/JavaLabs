package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner userInputScanner = new Scanner(System.in);
    static final List<Student> studentList = new ArrayList<>();
    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        new Main().startJournal();
        Student student = new Student();
        System.out.print(student);
    }

    private void startJournal() {
        String selectedOption;
        do {
            showMenu();
            selectedOption = userInputScanner.nextLine();
            processUserSelection(selectedOption);
        } while (!selectedOption.equals("4"));
    }

    private void showMenu() {
        System.out.println("\n=============================");
        System.out.println("    Student Journal Menu     ");
        System.out.println("=============================");
        System.out.println("1. Add New Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Remove Student");
        System.out.println("4. Exit Application");
        System.out.println("=============================");
        System.out.print("Please choose an option (1-4): ");
    }

    private void processUserSelection(String option) {
        if (option.equals("1")) {
            createStudent();
        } else if (option.equals("2")) {
            listStudents();
        } else if (option.equals("3")) {
            removeStudent();
        } else if (option.equals("4")) {
            System.out.println("Thank you for using the Student Journal. Goodbye!");
        } else {
            System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    private void createStudent() {
        System.out.println("\nEnter student information:");
        Student newStudent = new Student(
                getValidInput("Last Name: ", "[\\p{L}’`'-]+", "Last name must contain only letters."),
                getValidInput("First Name: ", "[\\p{L}’`'-]+", "First name must contain only letters."),
                getBirthDateInput(),
                getValidInput("Phone Number (10 digits): ", "\\d{10}", "Phone number must contain 10 digits."),
                getValidInput("Address (street, house, apartment): ", ".+", "Address cannot be empty.")
        );

        studentList.add(newStudent);
        System.out.println("Student added successfully!");
    }

    private LocalDate getBirthDateInput() {
        while (true) {
            System.out.print("Birth Date (yyyy-MM-dd): ");
            String input = userInputScanner.nextLine();

            if (input.matches("\\d{4}-02-30")) {
                System.out.println("February only has 29 days in leap years or 28 days in non-leap years. Please enter a valid date.");
                continue;
            }

            if (input.matches("\\d{4}-02-29")) {
                int year = Integer.parseInt(input.split("-")[0]);
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return LocalDate.parse(input, dateFormatter);
                } else {
                    System.out.println("The year " + year + " is not a leap year. Please enter a valid date.");
                    continue;
                }
            }

            try {
                LocalDate birthDate = LocalDate.parse(input, dateFormatter);
                return birthDate;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }

    private void listStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students available.");
        } else {
            System.out.println("\nCurrent Students in the Journal:\n");
            for (Student student : studentList) {
                System.out.println(student);
                System.out.println("\n");
            }
        }
    }

    private String getValidInput(String prompt, String pattern, String errorMessage) {
        String userInput;
        do {
            System.out.print(prompt);
            userInput = userInputScanner.nextLine();
            if (!Pattern.matches(pattern, userInput)) {
                System.out.println(errorMessage);
            }
        } while (!Pattern.matches(pattern, userInput));
        return userInput;
    }

    private void removeStudent() {
        String lastNameToRemove = getValidInput("Enter Last Name of the student to remove: ", "[\\p{L}’`'-]+", "Last name must contain only letters.");
        String firstNameToRemove = getValidInput("Enter First Name of the student to remove: ", "[\\p{L}’`'-]+", "First name must contain only letters.");

        Student studentToRemove = null;
        for (Student student : studentList) {
            if (student.lastName.equalsIgnoreCase(lastNameToRemove) && student.firstName.equalsIgnoreCase(firstNameToRemove)) {
                studentToRemove = student;
                break;
            }
        }

        if (studentToRemove != null) {
            studentList.remove(studentToRemove);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("No matching student found for " + firstNameToRemove + " " + lastNameToRemove + ".");
        }
    }

    static class Student {
        final String lastName;
        final String firstName;
        private final LocalDate birthDate;
        private final String phoneNumber;
        private final String address;

        public Student(String lastName, String firstName, LocalDate birthDate, String phoneNumber, String address) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.birthDate = birthDate;
            this.phoneNumber = phoneNumber;
            this.address = address;
        }

        @Override
        public String toString() {
            return String.format("Student: %s %s\nBirth Date: %s\nPhone Number: %s\nAddress: %s",
                    lastName, firstName, birthDate, phoneNumber, address);
        }
    }
}
