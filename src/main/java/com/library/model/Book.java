package com.library.model;

public class Book {
    private int code;
    private String title;
    private String author;
    private String genre;
    private float price;

    public Book() {

    }

    public Book(int code, String title, String author, String genre, float price) {
        this.code = code;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
