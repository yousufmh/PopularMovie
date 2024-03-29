package com.example.popularmovies.model;

import java.util.ArrayList;

public class Review {

    private String author;
    private String content;
    private ArrayList<Review> results;


    public Review() {
        this.author = "";
        this.content = "";
        this.results = new ArrayList<>();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Review> getResult() {
        return results;
    }

    public void setResult(ArrayList<Review> result) {
        this.results = result;
    }
}
