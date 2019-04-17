package com.example.popularmovies.model;

import java.util.ArrayList;

public class Trailer {

    private String key;
    private String name;
    private ArrayList<Trailer> results;

    public Trailer() {
        this.key = "";
        this.name = "";
        this.results = new ArrayList<>();
    }

    public String getId() {
        return key;
    }

    public void setId(String id) {
        this.key = id;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String title) {
        this.name = title;
    }

    public ArrayList<Trailer> getResults() {
        return results;
    }

    public void setResults(ArrayList<Trailer> results) {
        this.results = results;
    }
}
