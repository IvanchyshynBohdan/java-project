package com.library.model;

public class Publisher {
    private int id;
    private String name;
    private String site;

    public Publisher() {

    }

    public Publisher(int id, String name, String site) {
        this.id = id;
        this.name = name;
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String email) {
        this.site = site;
    }

    public Publisher(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
