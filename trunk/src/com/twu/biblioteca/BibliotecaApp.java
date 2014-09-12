package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {
    public static final String ALL_BOOKS = "1. Steve Jobs\t\t\t\tWalter Isaacson\t2011\n" +
            "2. Thinking, Fast and Slow\tDaniel Kahneman\t2011\n" +
            "3. Brave New World\t\t\tAldous Huxley\t2006";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(generateWelcomeMessage());
        System.out.println("\n");
        System.out.println(showMenu());

        while (sc.hasNext()) {
            int option = Integer.parseInt(sc.next());
            System.out.println(navigateMenu(option));
        }
    }

    public static String generateWelcomeMessage() {
        return "Welcome to Biblioteca!";
    }

    public static String listAllBooks() {
        return ALL_BOOKS;
    }

    public static String showMenu() {
        return "Biblioteca Menu\n" +
                "1. List all books";
    }

    public static String navigateMenu(int item) {
        if (item == 1) {
            return ALL_BOOKS;
        } else {
            return "Select a valid option!";
        }
    }
}
