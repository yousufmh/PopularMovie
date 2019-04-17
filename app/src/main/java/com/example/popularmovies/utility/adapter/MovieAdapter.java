package com.example.popularmovies.utility.adapter;

import android.content.Context;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.popularmovies.Details;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.R;
import com.example.popularmovies.model.Singalton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieVH> {

    private ArrayList<Movie> movies;
    private Context context;
    private final static String BASE_URL = "http://image.tmdb.org/t/p/" + "w185/";
    private Singalton data;

    public class MovieVH extends RecyclerView.ViewHolder{

        ImageView pic;

        public MovieVH(@NonNull View itemView) {

            super(itemView);
             pic = itemView.findViewById(R.id.movie_pic);

        }
    }

    public MovieAdapter(Context contextPar, ArrayList<Movie> moviesParameter){

        movies = moviesParameter;
        context = contextPar;
        data = Singalton.getInstance(contextPar);

    }

    @NonNull
    @Override
    public MovieVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_grid,viewGroup,false);

        return new MovieVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVH movieVH, int i) {

        final Movie movie = movies.get(i);

        Picasso.get()
                .load(BASE_URL+movie.getPosterImageThumbnail())
                .placeholder(R.drawable.ic_launcher_background)
                .into(movieVH.pic);

        movieVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Details.class);
                data.setMovie(movie);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }



}
