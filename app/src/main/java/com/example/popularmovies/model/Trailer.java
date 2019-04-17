package com.example.popularmovies.model;

import java.util.ArrayList;

public class Trailer {

    private String id;
    private String title;
    private ArrayList<Trailer> result;

    public Trailer() {
        this.id = "";
        this.title = "";
        this.result = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Trailer> getResult() {
        return result;
    }

    public void setResult(ArrayList<Trailer> result) {
        this.result = result;
    }
}
