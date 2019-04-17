package com.example.popularmovies.utility.retrofit;

import android.util.Log;
import android.view.View;

import com.example.popularmovies.model.ListMovie;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.Review;
import com.example.popularmovies.model.Singalton;
import com.example.popularmovies.model.Trailer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitConnection {


    private Singalton data;
    private Retrofit retrofit;
    private RetrofitAPI api;
    private ArrayList<Movie> movies;
    private View view;
    private final static String TAG = "RetrofitConnection:";

    public RetrofitConnection(View view){

        data = Singalton.getInstance(view.getContext());
        retrofit = data.getRetrofit();
        api = retrofit.create(RetrofitAPI.class);
        movies = new ArrayList<>();
        this.view = view;


    }

    public void getMovies(String sort, String API,final GeMoviesCallBack callBack ){

        api.getMovies(sort,API).enqueue(new Callback<ListMovie>() {
            @Override
            public void onResponse(Call<ListMovie> call, Response<ListMovie> response) {
                if(response.isSuccessful()){
                    movies = (ArrayList<Movie>) response.body().getResults();
                    callBack.getMovies(movies,true);

                }else{
                    try {
                        String error = response.errorBody() != null ? response.errorBody().string() : null;
                        Log.d(TAG, "!response.isSuccessful(): "+error);
                        callBack.getMovies(movies,false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Call<ListMovie> call, Throwable t) {

                callBack.getMovies(movies,false);

                Log.e(TAG, "onFailure: "+ t.getCause(),t );


            }
        });

    }

    public void getReviews(String id, String API_KEY, final GetReviewCallBack callBack){
        api.getReviews(id, API_KEY).enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                if(response.isSuccessful()) {
                    callBack.getReview((Review) response.body(), true);
                }else{
                    try {
                        String error = response.errorBody() != null ? response.errorBody().string() : null;
                        Log.d(TAG, "!response.isSuccessful(): " + error);
                        callBack.getReview(new Review(), false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                callBack.getReview(new Review(),false);

                Log.e(TAG, "onFailure: "+ t.getCause(),t );
            }
        });

    }


    public void getTrailer(String id, String API_KEY, final GetTrailerCallBack callBack){
        api.getVideos(id, API_KEY).enqueue(new Callback<Trailer>() {
            @Override
            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                if(response.isSuccessful())
                    callBack.getTrailer((Trailer)response.body(),true);
                else{
                    try {
                        String error = response.errorBody() != null ? response.errorBody().string() : null;
                        Log.d(TAG, "!response.isSuccessful(): " + error);
                        callBack.getTrailer(new Trailer(), false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Trailer> call, Throwable t) {
                callBack.getTrailer(new Trailer(),false);

                Log.e(TAG, "onFailure: "+ t.getCause(),t );
            }
        });

    }


    public interface GeMoviesCallBack{
        void getMovies(ArrayList<Movie> movies,boolean successful);
    }

    public interface GetReviewCallBack{
        void getReview(Review reviews, boolean successful);
    }

    public interface GetTrailerCallBack{
        void getTrailer(Trailer trailers, boolean successful);
    }

}
