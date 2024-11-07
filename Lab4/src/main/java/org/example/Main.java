package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Zoo zoo = new Zoo();

        // Додаємо вольєри у зоопарк
        zoo.addCage(new Cage<Lion>(2));  // Вольєр для левів, максимальна кількість місць - 2
        zoo.addCage(new Cage<Hoofed>(5));  // Вольєр для копитних (зебри, жирафи), максимальна кількість місць - 5
        zoo.addCage(new Cage<Eagle>(3));  // Вольєр для птахів (орли), максимальна кількість місць - 3

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Додати тварину у вольєр");
            System.out.println("2. Подивитися максимальну кількість місць у вольєрі");
            System.out.println("3. Подивитися кількість зайнятих місць у вольєрі");
            System.out.println("4. Вилучити тварину з вольєра");
            System.out.println("5. Вийти");
            System.out.print("Оберіть опцію: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addAnimalMenu(zoo, scanner);
                    break;
                case 2:
                    viewMaxCapacity(zoo, scanner);
                    break;
                case 3:
                    viewOccupiedCapacity(zoo, scanner);
                    break;
                case 4:
                    removeAnimalMenu(zoo, scanner);
                    break;
                case 5:
                    System.out.println("Вихід з програми.");
                    return;
                default:
                    System.out.println("Неправильний вибір, спробуйте ще раз.");
            }
        }
    }

    private static void addAnimalMenu(Zoo zoo, Scanner scanner) {
        System.out.println("1. Додати ссавця");
        System.out.println("2. Додати птицю");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("1. Додати лева");
            System.out.println("2. Додати копитну тварину (зебра або жираф)");
            int mammalChoice = scanner.nextInt();

            if (mammalChoice == 1) {
                System.out.print("Введіть ім'я лева: ");
                String name = scanner.next();
                try {
                    zoo.getCages().get(0).addAnimal(new Lion(name));
                    System.out.println("Лев " + name + " доданий до вольєра!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (mammalChoice == 2) {
                System.out.println("1. Додати зебру");
                System.out.println("2. Додати жирафа");
                int hoofedChoice = scanner.nextInt();

                if (hoofedChoice == 1) {
                    System.out.print("Введіть ім'я зебри: ");
                    String name = scanner.next();
                    try {
                        zoo.getCages().get(1).addAnimal(new Zebra(name));
                        System.out.println("Зебра " + name + " додана до вольєра!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (hoofedChoice == 2) {
                    System.out.print("Введіть ім'я жирафа: ");
                    String name = scanner.next();
                    try {
                        zoo.getCages().get(1).addAnimal(new Giraffe(name));
                        System.out.println("Жираф " + name + " доданий до вольєра!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Неправильний вибір.");
                }
            } else {
                System.out.println("Неправильний вибір.");
            }
        } else if (choice == 2) {
            System.out.print("Введіть ім'я орла: ");
            String name = scanner.next();
            try {
                zoo.getCages().get(2).addAnimal(new Eagle(name));
                System.out.println("Орел " + name + " доданий до вольєра!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Неправильний вибір.");
        }
    }

    private static void viewMaxCapacity(Zoo zoo, Scanner scanner) {
        System.out.println("1. Подивитися максимальну кількість місць у вольєрі для левів");
        System.out.println("2. Подивитися максимальну кількість місць у вольєрі для копитних");
        System.out.println("3. Подивитися максимальну кількість місць у вольєрі для птахів");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Максимальна кількість місць у вольєрі для левів: " + zoo.getCages().get(0).getMaxCapacity());
        } else if (choice == 2) {
            System.out.println("Максимальна кількість місць у вольєрі для копитних: " + zoo.getCages().get(1).getMaxCapacity());
        } else if (choice == 3) {
            System.out.println("Максимальна кількість місць у вольєрі для птахів: " + zoo.getCages().get(2).getMaxCapacity());
        } else {
            System.out.println("Неправильний вибір.");
        }
    }

    private static void viewOccupiedCapacity(Zoo zoo, Scanner scanner) {
        System.out.println("1. Подивитися кількість зайнятих місць у вольєрі для левів");
        System.out.println("2. Подивитися кількість зайнятих місць у вольєрі для копитних");
        System.out.println("3. Подивитися кількість зайнятих місць у вольєрі для птахів");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Зайняті місця у вольєрі для левів: " + zoo.getCages().get(0).getOccupiedPlaces());
        } else if (choice == 2) {
            System.out.println("Зайняті місця у вольєрі для копитних: " + zoo.getCages().get(1).getOccupiedPlaces());
        } else if (choice == 3) {
            System.out.println("Зайняті місця у вольєрі для птахів: " + zoo.getCages().get(2).getOccupiedPlaces());
        } else {
            System.out.println("Неправильний вибір.");
        }
    }

    private static void removeAnimalMenu(Zoo zoo, Scanner scanner) {
        System.out.println("1. Вилучити лева");
        System.out.println("2. Вилучити копитну тварину");
        System.out.println("3. Вилучити орла");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Введіть ім'я лева для вилучення: ");
            String name = scanner.next();
            try {
                zoo.getCages().get(0).removeAnimal(new Lion(name));
                System.out.println("Лев вилучений!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (choice == 2) {
            System.out.println("1. Вилучити зебру");
            System.out.println("2. Вилучити жирафа");
            int hoofedChoice = scanner.nextInt();

            if (hoofedChoice == 1) {
                System.out.print("Введіть ім'я зебри для вилучення: ");
                String name = scanner.next();
                try {
                    zoo.getCages().get(1).removeAnimal(new Zebra(name));
                    System.out.println("Зебра вилучена!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (hoofedChoice == 2) {
                System.out.print("Введіть ім'я жирафа для вилучення: ");
                String name = scanner.next();
                try {
                    zoo.getCages().get(1).removeAnimal(new Giraffe(name));
                    System.out.println("Жираф вилучений!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Неправильний вибір.");
            }
        } else if (choice == 3) {
            System.out.print("Введіть ім'я орла для вилучення: ");
            String name = scanner.next();
            try {
                zoo.getCages().get(2).removeAnimal(new Eagle(name));
                System.out.println("Орел вилучений!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Неправильний вибір.");
        }
    }
}
