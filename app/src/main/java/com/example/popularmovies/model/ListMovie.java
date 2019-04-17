package com.example.popularmovies.model;

import java.util.ArrayList;

public class ListMovie {

    private ArrayList<Movie> results;

    public ListMovie(ArrayList<Movie> movies){
        results = movies;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
