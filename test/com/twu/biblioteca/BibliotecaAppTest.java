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
        InputStream in = new ByteArrayInputStream("4\n".getBytes());
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
        String expected = " 1. Steve Jobs                Walter Isaacson 2011\n" +
                " 2. Thinking, Fast and Slow   Daniel Kahneman 2011\n" +
                " 3. Brave New World           Aldous Huxley 2006\n";
        assertEquals(expected, new BibliotecaApp().navigateMenu(1));
    }

    @Test
    public void testInvalidNavigateMenu() throws Exception {
        String expected = "Select a valid option!";
        assertEquals(expected, new BibliotecaApp().navigateMenu(-1));
    }

    @Test
    public void testCheckoutBook() throws Exception {
        String expectedMsg = "Thank you! Enjoy the book";
        String expected = " 2. Thinking, Fast and Slow   Daniel Kahneman 2011\n" +
                " 3. Brave New World           Aldous Huxley 2006\n";
        BibliotecaApp b = new BibliotecaApp();
        String msg = b.checkOut(1);
        assertEquals(expectedMsg, msg);
        String res = b.listAllBooks();
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
        String expectedMsg = "Thank you for returning the book.";
        String expected = " 1. Steve Jobs                Walter Isaacson 2011\n" +
                " 2. Thinking, Fast and Slow   Daniel Kahneman 2011\n" +
                " 3. Brave New World           Aldous Huxley 2006\n";
        BibliotecaApp b = new BibliotecaApp();
        b.checkOut(1);
        String msg = b.returnBook(1);
        assertEquals(expectedMsg, msg);
        String res = b.listAllBooks();
        assertEquals(expected, res);
    }

    @Test
    public void testInvalidReturnBook() throws Exception {
        String expected = "That is not a valid book to return.";
        BibliotecaApp b = new BibliotecaApp();
        String res = b.returnBook(1);
        assertEquals(expected, res);
    }

    @After
    public void tearDown() throws Exception {
        out.close();
    }
}
