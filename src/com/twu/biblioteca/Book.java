package com.twu.biblioteca;

/**
 * Created by chuajiesheng on 12/9/14.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String publishYear;
    private User onLoanTo;

    public Book(int id, String title, String author, String publishYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        onLoanTo = null;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public User getOnLoanTo() {
        return onLoanTo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public void setOnLoanTo(User onLoanTo) {
        this.onLoanTo = onLoanTo;
    }

    @Override
    public String toString() {
        return String.format("%2d. %-25s %-12s %-4s", id, title, author, publishYear);
    }
}
