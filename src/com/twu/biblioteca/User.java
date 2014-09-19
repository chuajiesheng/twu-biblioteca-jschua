package com.twu.biblioteca;

/**
 * Created by chuajiesheng on 19/9/14.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;

    public User(int id, String username, String password, String name, String email, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getUserInformation() {
        return String.format("Name: %s\nEmail: %s\nPhone: %s", name, email, phone);
    }
}
