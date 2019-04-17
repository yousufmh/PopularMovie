package com.example.popularmovies.utility.fav;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.Singalton;

import java.util.List;

public class FavVM extends AndroidViewModel {

   private LiveData<List<Movie>> movieList;
   private LiveData<Movie> movieLiveData;
   private Singalton data;

    public FavVM(@NonNull Application application) {
        super(application);
        data = Singalton.getInstance(application.getApplicationContext());
        RoomDB db = data.getDb();
        movieList = db.favDao().getFavMovies();
        movieLiveData = db.favDao().getSingalMovie(data.getMovie().getID());

    }

    public LiveData<List<Movie>> getMovieList() {
        return movieList;
    }

    public LiveData<Movie> getMovieLiveData(){return movieLiveData;}
}
