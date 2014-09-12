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
        String expected = "1. Steve Jobs\t\t\t\tWalter Isaacson\t2011\n" +
                "2. Thinking, Fast and Slow\tDaniel Kahneman\t2011\n" +
                "3. Brave New World\t\t\tAldous Huxley\t2006";
        assertEquals(expected,  new BibliotecaApp().listAllBooks());
    }
}
