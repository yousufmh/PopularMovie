package com.example.popularmovies.model;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.popularmovies.utility.fav.RoomDB;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singalton {

    private static Singalton instance;

    private Movie movie;
    private Retrofit retrofit;
    private RoomDB db;
    private boolean isReview;
    private Review review;
    private Trailer trailer;

    private Singalton(Context context){

        movie = new Movie();
        review = new Review();
        trailer = new Trailer();
        isReview = true;

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        db = Room.databaseBuilder(context,
                RoomDB.class,
                "FavDB")
                .build();

    }

    public static synchronized Singalton getInstance (Context context){

        if(instance ==null){
            instance = new Singalton(context);
        }
        return instance;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public RoomDB getDb() {
        return db;
    }

    public boolean isReview() {
        return isReview;
    }

    public void setReview(boolean review) {
        this.isReview = review;
    }
}
