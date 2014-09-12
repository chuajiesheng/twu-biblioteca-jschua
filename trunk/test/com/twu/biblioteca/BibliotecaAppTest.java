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
        String expected = BibliotecaApp.ALL_BOOKS;
        assertEquals(expected,  new BibliotecaApp().listAllBooks());
    }
}
