package com.twu.biblioteca;

public class BibliotecaApp {
    public static final String ALL_BOOKS = "1. Steve Jobs by Walter Isaacson\n" +
            "2. Thinking, Fast and Slow by Daniel Kahneman\n" +
            "3. Brave New World by Aldous Huxley";

    public static void main(String[] args) {
        System.out.println(generateWelcomeMessage());
        System.out.println("\n");
        System.out.println("List of all library books:");
        System.out.println(listAllBooks());
    }

    public static String generateWelcomeMessage() {
        return "Welcome to Biblioteca!";
    }

    public static String listAllBooks() {
        return ALL_BOOKS;
    }
}
