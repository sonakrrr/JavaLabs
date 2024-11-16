package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати слово у словник");
            System.out.println("2. Видалити слово із словника");
            System.out.println("3. Подивитись усі слова в словнику");
            System.out.println("4. Перекласти речення");
            System.out.println("5. Вийти");
            System.out.print("Виберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    String englishWord;
                    String ukrainianWord;

                    do {
                        System.out.print("Англійське слово: ");
                        englishWord = scanner.nextLine();
                        if (dictionary.isEnglishWord(englishWord)) {
                            System.out.println("Помилка: Введіть слово, що містить тільки англійські літери.");
                        }
                    } while (dictionary.isEnglishWord(englishWord));

                    do {
                        System.out.print("Українське слово: ");
                        ukrainianWord = scanner.nextLine();
                        if (dictionary.isUkrainianWord(ukrainianWord)) {
                            System.out.println("Помилка: Введіть слово, що містить тільки українські літери.");
                        }
                    } while (dictionary.isUkrainianWord(ukrainianWord));

                    dictionary.addWord(englishWord, ukrainianWord);
                    System.out.println("Слово додано до словника.");
                    break;

                case 2:
                    System.out.print("Введіть слово, яке хочете видалити: ");
                    String wordToDelete = scanner.nextLine();
                    dictionary.removeWord(wordToDelete);
                    break;

                case 3:
                    System.out.println("Слова у словнику:");
                    dictionary.displayAllWords();
                    break;

                case 4:
                    System.out.println("Оберіть напрямок перекладу:");
                    System.out.println("1. З англійської на українську");
                    System.out.println("2. З української на англійську");
                    int direction = scanner.nextInt();
                    scanner.nextLine(); // Споживаємо залишок нового рядка
                    System.out.print("Введіть фразу для перекладу: ");
                    String phrase = scanner.nextLine();
                    String translation = direction == 1
                            ? dictionary.translatePhrase(phrase, "en-uk")
                            : dictionary.translatePhrase(phrase, "uk-en");
                    System.out.println("Переклад: " + translation);
                    break;

                case 5:
                    System.out.println("Завершення програми.");
                    return;

                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
