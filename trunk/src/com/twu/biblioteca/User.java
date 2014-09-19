package com.twu.biblioteca;

/**
 * Created by chuajiesheng on 19/9/14.
 */
public class User {
    private int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User checkPassword(String password) {
        if (this.password.equals(password)) {
            return this;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != getClass()) {
            return false;
        }

        User u = (User) o;
        return u.getId() == getId();
    }
}
