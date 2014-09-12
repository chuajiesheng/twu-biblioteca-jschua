package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BibliotecaApp {
    private enum STATE {MENU, CHECKOUT, RETURN};
    private STATE currentState = STATE.MENU;

    private ArrayList<Book> booksAvailable;
    private ArrayList<Book> booksOnLoan;
    private Book[] allBooks = {
            new Book(1, "Steve Jobs", "Walter Isaacson", "2011"),
            new Book(2, "Thinking, Fast and Slow", "Daniel Kahneman", "2011"),
            new Book(3, "Brave New World", "Aldous Huxley", "2006")
    };

    public static void main(String[] args) {
        new BibliotecaApp().run();
    }

    public BibliotecaApp() {
        booksAvailable = new ArrayList<Book>();
        Collections.addAll(booksAvailable, allBooks);

        booksOnLoan = new ArrayList<Book>();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println(generateWelcomeMessage());
        System.out.println("\n");
        System.out.println(showMenu());

        while (sc.hasNext()) {
            int option = Integer.parseInt(sc.next());
            if (currentState == STATE.MENU) {
                System.out.println(navigateMenu(option));
            } else if (currentState == STATE.CHECKOUT) {
                System.out.println(checkOut(option));
            }
        }
    }

    public String generateWelcomeMessage() {
        return "Welcome to Biblioteca!";
    }

    public String listAllBooks() {
        StringBuilder sb = new StringBuilder();
        for (Book b : booksAvailable) {
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
        } else if (item == 2) {
            currentState = STATE.CHECKOUT;
            return listAllBooks() + "\n" +
                    "Please select book to checkout.";
        } else if (item == 4) {
            System.exit(0);
        } else {
            return "Select a valid option!";
        }
        return null;
    }

    public String checkOut(int item) {
        for (Book b : booksAvailable) {
            if (b.getId() == item) {
                booksAvailable.remove(b);
                booksOnLoan.add(b);
                return "Thank you! Enjoy the book";
            }
        }
        return "That book is not available.";
    }


    public String returnBook(int item) {
        for (Book b : booksOnLoan) {
            if (b.getId() == item) {
                booksOnLoan.remove(b);
                booksAvailable.add(b.getId() - 1, b);
                return "Thank you for returning the book.";
            }
        }
        return "That is not a valid book to return.";
    }
}
