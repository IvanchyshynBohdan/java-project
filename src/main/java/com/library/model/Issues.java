package com.library.model;

import java.util.Date;

public class Issues {
    private int id;
    private Books books;
    private Member member;
    private Librarian librarian;
    private Date takenDate;
    private Date broughtDate;

    public Issues() {

    }

    public Issues(int id, Books books, Member member, Librarian librarian, Date takenDate, Date broughtDate) {
        this.id = id;
        this.books = books;
        this.member = member;
        this.librarian = librarian;
        this.takenDate = takenDate;
        this.broughtDate = broughtDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public Date getTakenDate() {
        return takenDate;
    }

    public void setTakenDate(Date takenDate) {
        this.takenDate = takenDate;
    }

    public Date getBroughtDate() {
        return broughtDate;
    }

    public void setBroughtDate(Date broughtDate) {
        this.broughtDate = broughtDate;
    }
}
