package com.twu.biblioteca;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by chuajiesheng on 12/9/14.
 */
public class BibliotecaAppTest {
    @Test
    public void testGenerateWelcomeMessage() throws Exception {
        String expected = "Welcome to Biblioteca!";
        assertEquals(expected, new BibliotecaApp().generateWelcomeMessage());
    }

    @Test
    public void testListAllBooks() throws Exception {
        String expected = " 1. Steve Jobs                Walter Isaacson 2011\n" +
                " 2. Thinking, Fast and Slow   Daniel Kahneman 2011\n" +
                " 3. Brave New World           Aldous Huxley 2006\n";
        assertEquals(expected, new BibliotecaApp().listAllBooks());
    }

    @Test
    public void testShowMenu() throws Exception {
        String expected = "Biblioteca Menu\n" +
                "1. List all books\n" +
                "2. Checkout Book\n" +
                "3. Return Book\n4. Quit";
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
}
