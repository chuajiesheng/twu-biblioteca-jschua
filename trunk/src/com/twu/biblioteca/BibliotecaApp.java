package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class BibliotecaApp {

    public static final String STRING_WELCOME_MSG = "Welcome to Biblioteca!";
    public static final String STRING_MENU = "Biblioteca Menu\n" +
            "0. Login\n" +
            "1. List all Books\n" +
            "2. Checkout Book\n" +
            "3. Return Book\n" +
            "4. List all Movies\n" +
            "5. Checkout Movie\n" +
            "6. Quit";
    public static final String STRING_MENU_LOGGED_IN = "Biblioteca Menu\n" +
            "0. User Information\n" +
            "1. List all Books\n" +
            "2. Checkout Book\n" +
            "3. Return Book\n" +
            "4. List all Movies\n" +
            "5. Checkout Movie\n" +
            "6. Quit";

    private enum STATE {MENU, BOOK_CHECKOUT, BOOK_RETURN, MOVIE_CHECKOUT, MOVIE_RETURN};
    private STATE currentState = STATE.MENU;

    private ArrayList<Book> booksAvailable;
    private ArrayList<Book> booksOnLoan;
    private Book[] allBooks = {
            new Book(1, "Steve Jobs", "Walter Isaacson", "2011"),
            new Book(2, "Thinking, Fast and Slow", "Daniel Kahneman", "2011"),
            new Book(3, "Brave New World", "Aldous Huxley", "2006")
    };
    private ArrayList<Movie> moviesAvailable;
    private ArrayList<Movie> movieOnLoan;
    private Movie[] allMovies = {
            new Movie(1, "Transformers", 2007, "Michael Bay", 7),
            new Movie(2, "Fast & Furious 6", 2013, "Justin Lin", 7),
            new Movie(3, "Transcendence", 2014, "Wally Pfister", 6),
            new Movie(4, "2012", 2009, "Roland Emmerich", 5)
    };

    private User[] allUsers =  {
            new User(1, "123-4567", "david123", "David", "david@hotmail.com", "(65) 6123 4567")
    };
    private HashMap<String, User> users;
    private User currentUser = null;

    public static void main(String[] args) {
        new BibliotecaApp().run(System.in, System.out);
    }

    public BibliotecaApp() {
        booksAvailable = new ArrayList<Book>();
        Collections.addAll(booksAvailable, allBooks);
        booksOnLoan = new ArrayList<Book>();

        moviesAvailable = new ArrayList<Movie>();
        Collections.addAll(moviesAvailable, allMovies);
        movieOnLoan = new ArrayList<Movie>();

        users = new HashMap<String, User>();
        for (User u : allUsers) {
            users.put(u.getUsername(), u);
        }
    }

    public void run(InputStream in, PrintStream out) {
        Scanner sc = new Scanner(in);

        out.println(generateWelcomeMessage());
        out.println("\n");
        out.println(showMenu());

        while (sc.hasNext()) {
            int option = Integer.parseInt(sc.next());
            if (currentState == STATE.MENU) {
                if (option == 6) {
                    break;
                }
                out.println(navigateMenu(sc, out, option));
            } else if (currentState == STATE.BOOK_CHECKOUT) {
                out.println(checkOut(currentUser, option));
            } else if (currentState == STATE.BOOK_RETURN) {
                out.println(returnBook(option));
            } else if (currentState == STATE.MOVIE_CHECKOUT) {
                out.println(movieCheckOut(option));
            }
        }
    }

    public String generateWelcomeMessage() {
        return STRING_WELCOME_MSG;
    }

    public String showMenu() {
        return STRING_MENU;
    }

    public String navigateMenu(Scanner sc, PrintStream out, int item) {
        if (item == 0) {
            if (currentUser == null) {
                return checkLogin(sc, out);
            }
            return userInformation(currentUser);
        } else if (item == 1) {
            return listAllBooks();
        } else if (item == 2) {
            if (currentUser == null) {
                return checkLogin(sc, out);
            }
            currentState = STATE.BOOK_CHECKOUT;
            return listAllBooks() + "\n" +
                    "Please select book to checkout.";
        } else if (item == 3) {
            if (currentUser == null) {
                return checkLogin(sc, out);
            }
            currentState = STATE.BOOK_RETURN;
            return listBooksOnLoan(currentUser) + "\n" +
                    "Please select book to return.";
        } else if (item == 4) {
            return listAllMovies();
        } else if (item == 5) {
            currentState = STATE.MOVIE_CHECKOUT;
            return listAllMovies() + "\n" +
                    "Please select movie to checkout.";
        } else if (item == 6) {
            System.exit(0);
        } else {
            return "Select a valid option!";
        }
        return null;
    }

    private String checkLogin(Scanner sc, PrintStream out) {
        out.print("Please enter your username: ");
        String username = sc.next();
        out.print("Please enter your password: ");
        String password = sc.next();
        currentUser = users.get(username).checkPassword(password);
        if (currentUser != null) {
            return "Login Successful!\n\n" + STRING_MENU_LOGGED_IN;
        } else {
            return "Login Failed.\n\n" + STRING_MENU;
        }
    }

    private String userInformation(User currentUser) {
        return currentUser.getUserInformation();
    }

    public String listAllBooks() {
        StringBuilder sb = new StringBuilder();
        for (Book b : booksAvailable) {
            sb.append(b.toString() + "\n");
        }
        return sb.toString();
    }

    public String checkOut(User u, int item) {
        currentState = STATE.MENU;
        for (Book b : booksAvailable) {
            if (b.getId() == item) {
                booksAvailable.remove(b);
                b.setOnLoanTo(u);
                booksOnLoan.add(b);
                return "Thank you! Enjoy the book";
            }
        }
        return "That book is not available.";
    }

    private String listBooksOnLoan(User currentUser) {
        StringBuilder sb = new StringBuilder();
        for (Book b : booksOnLoan) {
            if (b.getOnLoanTo().equals(currentUser)) {
                sb.append(b.toString() + "\n");
            }
        }
        return sb.toString();
    }

    public String returnBook(int item) {
        currentState = STATE.MENU;
        for (Book b : booksOnLoan) {
            if (b.getId() == item) {
                booksOnLoan.remove(b);
                b.setOnLoanTo(null);
                booksAvailable.add(b.getId() - 1, b);
                return "Thank you for returning the book.";
            }
        }
        return "That is not a valid book to return.";
    }

    private String listAllMovies() {
        StringBuilder sb = new StringBuilder();
        for (Movie m : moviesAvailable) {
            sb.append(m.toString() + "\n");
        }
        return sb.toString();
    }

    private String movieCheckOut(int item) {
        currentState = STATE.MENU;
        for (Movie m : moviesAvailable) {
            if (m.getId() == item) {
                moviesAvailable.remove(m);
                movieOnLoan.add(m);
                return "Thank you! Enjoy the movie";
            }
        }
        return "That book is not available.";
    }
}
