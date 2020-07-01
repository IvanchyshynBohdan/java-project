package com.library.model;

public class Librarian extends Person {
    private int id;
    private String password;
    private String phone;
    private String email;
    private int experience;

    public Librarian() {

    }

    public Librarian(int id, String password, String phone, String email, int experience) {
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
