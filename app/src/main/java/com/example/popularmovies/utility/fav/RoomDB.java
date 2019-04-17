package com.example.popularmovies.utility.fav;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.popularmovies.model.Movie;

@Database(entities = {Movie.class}, version = 1,exportSchema = false)

public abstract class RoomDB extends RoomDatabase {

    public abstract FavInterface favDao();

}
