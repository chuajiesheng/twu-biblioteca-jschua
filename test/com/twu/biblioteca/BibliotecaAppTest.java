package com.twu.biblioteca;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by chuajiesheng on 12/9/14.
 */
public class BibliotecaAppTest {
    public static final String STRING_STARTUP_MSG =
            BibliotecaApp.STRING_WELCOME_MSG + "\n\n\n" +
                    BibliotecaApp.STRING_MENU + "\n";
    public static final String STRING_ALL_BOOKS = " 1. Steve Jobs                Walter Isaacson 2011\n" +
            " 2. Thinking, Fast and Slow   Daniel Kahneman 2011\n" +
            " 3. Brave New World           Aldous Huxley 2006\n";
    public static final String STRING_CHECKOUT_BOOK = " 1. Steve Jobs                Walter Isaacson 2011\n" +
            " 2. Thinking, Fast and Slow   Daniel Kahneman 2011\n" +
            " 3. Brave New World           Aldous Huxley 2006\n" +
            "\n" +
            "Please select book to checkout.\n" +
            "Thank you! Enjoy the book\n";
    public static final String STRING_ALL_MOVIES = " 1. Transformers              (2007) Michael Bay   7of10\n" +
            " 2. Fast & Furious 6          (2013) Justin Lin    7of10\n" +
            " 3. Transcendence             (2014) Wally Pfister  6of10\n" +
            " 4. 2012                      (2009) Roland Emmerich  5of10\n\n";
    public static final String STRING_CHECKOUT_MOVIE = STRING_ALL_MOVIES +
            "Please select movie to checkout.\n" +
            "Thank you! Enjoy the movie\n";

    private ByteArrayOutputStream outputBuffer;
    private PrintStream out;

    @Before
    public void setUp() throws Exception {
        outputBuffer = new ByteArrayOutputStream();
        out = new PrintStream(outputBuffer);
    }

    @Test
    public void testGenerateWelcomeMessage() throws Exception {
        String expected = BibliotecaApp.STRING_WELCOME_MSG;
        assertEquals(expected, new BibliotecaApp().generateWelcomeMessage());
    }

    @Test
    public void testStartupMessage() throws Exception {
        String expected = STRING_STARTUP_MSG;
        InputStream in = new ByteArrayInputStream("6\n".getBytes());
        final BibliotecaApp app = new BibliotecaApp();
        app.run(in, out);
        assertEquals(expected, outputBuffer.toString());
    }

    @Test
    public void testListAllBooks() throws Exception {
        String expected = " 1. Steve Jobs                Walter Isaacson 2011\n" +
                " 2. Thinking, Fast and Slow   Daniel Kahneman 2011\n" +
                " 3. Brave New World           Aldous Huxley 2006\n\n";
        InputStream in = new ByteArrayInputStream("1\n".getBytes());
        final BibliotecaApp app = new BibliotecaApp();
        app.run(in, out);
        String res = outputBuffer.toString().replace(STRING_STARTUP_MSG, "");
        assertEquals(expected, res);
    }

    @Test
    public void testShowMenu() throws Exception {
        String expected = BibliotecaApp.STRING_MENU;
        assertEquals(expected, new BibliotecaApp().showMenu());
    }

    @Test
    public void testNavigateMenu() throws Exception {
        String expected = STRING_ALL_BOOKS;
        assertEquals(expected, new BibliotecaApp().navigateMenu(null, 1));
    }

    @Test
    public void testInvalidNavigateMenu() throws Exception {
        String expected = "Select a valid option!";
        assertEquals(expected, new BibliotecaApp().navigateMenu(null, -1));
    }

    @Test
    public void testCheckoutBook() throws Exception {
        String expected = STRING_CHECKOUT_BOOK;
        InputStream in = new ByteArrayInputStream("2\n1\n".getBytes());
        final BibliotecaApp app = new BibliotecaApp();
        app.run(in, out);
        String res = outputBuffer.toString().replace(STRING_STARTUP_MSG, "");
        assertEquals(expected, res);
    }

    @Test
    public void testInvalidCheckoutBook() throws Exception {
        String expected = "That book is not available.";
        BibliotecaApp b = new BibliotecaApp();
        String msg = b.checkOut(0);
        assertEquals(expected, msg);
    }

    @Test
    public void testReturnBook() throws Exception {
        String expected = " 1. Steve Jobs                Walter Isaacson 2011\n" +
                "\n" +
                "Please select book to return.\n" +
                "Thank you for returning the book.\n";
        InputStream in = new ByteArrayInputStream("2\n1\n3\n1\n".getBytes());
        final BibliotecaApp app = new BibliotecaApp();
        app.run(in, out);

        String res = outputBuffer.toString();
        res = res.replace(STRING_STARTUP_MSG, "");
        res = res.replace(STRING_CHECKOUT_BOOK, "");
        assertEquals(expected, res);
        res = res.trim();
    }

    @Test
    public void testInvalidReturnBook() throws Exception {
        String expected = "That is not a valid book to return.";
        BibliotecaApp b = new BibliotecaApp();
        String res = b.returnBook(1);
        assertEquals(expected, res);
    }

    @Test
    public void testListAllMovies() throws Exception {
        String expected = STRING_ALL_MOVIES;
        InputStream in = new ByteArrayInputStream("4\n".getBytes());
        final BibliotecaApp app = new BibliotecaApp();
        app.run(in, out);
        String res = outputBuffer.toString().replace(STRING_STARTUP_MSG, "");
        assertEquals(expected, res);
    }

    @Test
    public void testCheckoutMovie() throws Exception {
        String expected = STRING_CHECKOUT_MOVIE;
        InputStream in = new ByteArrayInputStream("5\n1\n".getBytes());
        final BibliotecaApp app = new BibliotecaApp();
        app.run(in, out);
        String res = outputBuffer.toString().replace(STRING_STARTUP_MSG, "");
        assertEquals(expected, res);
    }

    @Test
    public void testLogin() throws Exception {
        String expected = "Login Successful!\n";
        InputStream in = new ByteArrayInputStream("0\nDavid\ndavid123\n".getBytes());
        final BibliotecaApp app = new BibliotecaApp();
        app.run(in, out);
        String res = outputBuffer.toString().replace(STRING_STARTUP_MSG, "");
        assertEquals(expected, res);
    }

    @Test
    public void testFailedLogin() throws Exception {
        String expected = "Login Failed.\n";
        InputStream in = new ByteArrayInputStream("0\nDavid\ndavid123456\n".getBytes());
        final BibliotecaApp app = new BibliotecaApp();
        app.run(in, out);
        String res = outputBuffer.toString().replace(STRING_STARTUP_MSG, "");
        assertEquals(expected, res);
    }

    @After
    public void tearDown() throws Exception {
        out.close();
    }
}
