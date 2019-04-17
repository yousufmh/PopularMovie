package com.example.popularmovies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.utility.fav.FavVM;
import com.example.popularmovies.utility.adapter.MovieAdapter;
import com.example.popularmovies.utility.retrofit.RetrofitConnection;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Global Variable
    private RecyclerView rv;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;
    private String sortURL;
    private RetrofitConnection connect;
    private final String ACTIVITY_TAG = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = new ArrayList<>();
        if(savedInstanceState!=null){
            if(savedInstanceState.containsKey(ACTIVITY_TAG)){
                sortURL = savedInstanceState.getString(ACTIVITY_TAG);
            }
        }else{
            sortURL = "popular";
        }

        rv = findViewById(R.id.movie_rv);
        adapter = new MovieAdapter(getApplicationContext(), movies);
        RecyclerView.LayoutManager layout = new GridLayoutManager(this,2);
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);
        connect = new RetrofitConnection(rv);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!sortURL.equals("Fav"))
        callRetrofit();
        else
            callFav();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(ACTIVITY_TAG,sortURL);
    }

    private void callRetrofit() {

        connect.getMovies(sortURL , getResources().getString(R.string.API_KEY),
                new RetrofitConnection.GeMoviesCallBack() {
                    @Override
                    public void getMovies(ArrayList<Movie> moviesPara,boolean successful) {

                        if(successful){
                            movies.clear();
                            movies.addAll(moviesPara);
                            adapter.notifyDataSetChanged();
                        }else{
                            Snackbar.make(rv,
                                    getResources().getString(R.string.error_internet),
                                    Snackbar.LENGTH_INDEFINITE)
                                    .setAction(getResources().getString(R.string.retry),
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    callRetrofit();
                                                }
                                            }).show();
                        }

                    }
                });

    }

    private void callFav() {

        FavVM vm = ViewModelProviders.of(this).get(FavVM.class);
        vm.getMovieList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movieList) {
                movies.clear();
                movies.addAll(movieList);
                adapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.popular:
                sortURL = "popular";
                callRetrofit();
                return true;

            case R.id.rate:
                sortURL = "top_rated";
                callRetrofit();
                return true;

            case R.id.fav:
                sortURL = "Fav";
                callFav();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}
