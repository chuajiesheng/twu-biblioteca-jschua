package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BibliotecaApp {
    private ArrayList<Book> books;
    private Book[] allBooks = {
            new Book(1, "Steve Jobs", "Walter Isaacson", "2011"),
            new Book(2, "Thinking, Fast and Slow", "Daniel Kahneman", "2011"),
            new Book(3, "Brave New World", "Aldous Huxley", "2006")
    };

    public static void main(String[] args) {
        new BibliotecaApp().run();
    }

    public BibliotecaApp() {
        books = new ArrayList<Book>();
        Collections.addAll(books, allBooks);
    }

    private void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println(generateWelcomeMessage());
        System.out.println("\n");
        System.out.println(showMenu());

        while (sc.hasNext()) {
            int option = Integer.parseInt(sc.next());
            System.out.println(navigateMenu(option));
        }
    }

    public String generateWelcomeMessage() {
        return "Welcome to Biblioteca!";
    }

    public String listAllBooks() {
        StringBuilder sb = new StringBuilder();
        for (Book b : books) {
            sb.append(b.toString() + "\n");
        }
        return sb.toString();
    }

    public String showMenu() {
        return "Biblioteca Menu\n" +
                "1. List all books\n" +
                "2. Checkout Book\n" +
                "3. Return Book\n" +
                "4. Quit";
    }

    public String navigateMenu(int item) {
        if (item == 1) {
            return listAllBooks();
        } else if (item == 4) {
            System.exit(0);
        } else {
            return "Select a valid option!";
        }
        return null;
    }

    public String checkOut(int item) {
        for (Book b : books) {
            if (b.getId() == item) {
                books.remove(b);
                return "Thank you! Enjoy the book";
            }
        }
        return "That book is not available.";
    }
}
