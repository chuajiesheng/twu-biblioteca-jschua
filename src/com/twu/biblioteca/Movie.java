package com.twu.biblioteca;

/**
 * Created by chuajiesheng on 17/9/14.
 */
public class Movie {
    private int id;
    private String name;
    private int year;
    private String director;
    private int rating;

    public Movie(int id, String name, int year, String director, int rating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%2d. %-25s (%4d) %-12s %2dof10", id, name, year, director, rating);
    }
}
