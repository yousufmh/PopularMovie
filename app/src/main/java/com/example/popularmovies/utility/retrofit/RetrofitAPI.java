package com.example.popularmovies.utility.retrofit;

import android.arch.lifecycle.MutableLiveData;

import com.example.popularmovies.model.ListMovie;
import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.Review;
import com.example.popularmovies.model.Trailer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("{sort}")
    Call<ListMovie> getMovies(@Path("sort") String sort, @Query("api_key") String sortWithKey);

    @GET("{id}/videos")
    Call<List<Trailer>> getVideos(@Path("id") String id, @Query("api_key") String sortWithKey);

    @GET("{id}/reviews")
    Call<List<Review>> getReviews(@Path("id") String id,@Query("api_key") String sortWithKey);


}
