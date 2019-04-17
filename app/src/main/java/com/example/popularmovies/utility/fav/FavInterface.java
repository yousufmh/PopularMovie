package com.example.popularmovies.utility.fav;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.popularmovies.model.Movie;

import java.util.List;

@Dao
public interface FavInterface {

    @Query("SELECT * from Movie")
    LiveData<List<Movie>> getFavMovies();

    @Query("SELECT * from Movie WHERE ID = :id")
    LiveData<Movie> getSingalMovie(int id);

    @Insert(onConflict = 2 )
    void addFavMovie(Movie movie);

    @Delete
    void deleteFavMovie(Movie movie);

}
