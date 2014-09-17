package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BibliotecaApp {

    public static final String STRING_WELCOME_MSG = "Welcome to Biblioteca!";
    public static final String STRING_MENU = "Biblioteca Menu\n" +
            "1. List all books\n" +
            "2. Checkout Book\n" +
            "3. Return Book\n" +
            "4. Quit";

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
        new BibliotecaApp().run(System.in, System.out);
    }

    public BibliotecaApp() {
        booksAvailable = new ArrayList<Book>();
        Collections.addAll(booksAvailable, allBooks);

        booksOnLoan = new ArrayList<Book>();
    }

    public void run(InputStream in, PrintStream out) {
        Scanner sc = new Scanner(in);

        out.println(generateWelcomeMessage());
        out.println("\n");
        out.println(showMenu());

        while (sc.hasNext()) {
            int option = Integer.parseInt(sc.next());
            if (currentState == STATE.MENU) {
                if (option == 4) {
                    break;
                }
                out.println(navigateMenu(option));
            } else if (currentState == STATE.CHECKOUT) {
                out.println(checkOut(option));
            } else if (currentState == STATE.RETURN) {
                out.println(returnBook(option));
            }
        }
    }

    public String generateWelcomeMessage() {
        return STRING_WELCOME_MSG;
    }

    public String listAllBooks() {
        StringBuilder sb = new StringBuilder();
        for (Book b : booksAvailable) {
            sb.append(b.toString() + "\n");
        }
        return sb.toString();
    }

    private String listBooksOnLoan() {
        StringBuilder sb = new StringBuilder();
        for (Book b : booksOnLoan) {
            sb.append(b.toString() + "\n");
        }
        return sb.toString();
    }

    public String showMenu() {
        return STRING_MENU;
    }

    public String navigateMenu(int item) {
        if (item == 1) {
            return listAllBooks();
        } else if (item == 2) {
            currentState = STATE.CHECKOUT;
            return listAllBooks() + "\n" +
                    "Please select book to checkout.";
        } else if (item == 3) {
            currentState = STATE.RETURN;
            return listBooksOnLoan() + "\n" +
                    "Please select book to return.";
        } else if (item == 4) {
            System.exit(0);
        } else {
            return "Select a valid option!";
        }
        return null;
    }

    public String checkOut(int item) {
        currentState = STATE.MENU;
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
        currentState = STATE.MENU;
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
