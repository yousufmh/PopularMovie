package com.example.popularmovies;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popularmovies.model.Review;
import com.example.popularmovies.model.Singalton;
import com.example.popularmovies.model.Trailer;
import com.example.popularmovies.utility.adapter.RandTAdapter;
import com.example.popularmovies.utility.retrofit.RetrofitConnection;

import java.util.ArrayList;


public class ReviewFragment extends Fragment {


    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Singalton data = Singalton.getInstance(getActivity());
        data.setReview(true);

        RecyclerView rv = container.findViewById(R.id.rv_review);
        final ArrayList<Review> reviews = new ArrayList<>();
        final RandTAdapter adapter = new RandTAdapter(getContext(),reviews);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);
        RetrofitConnection connection = new RetrofitConnection(rv);
        connection.getReviews(""+data.getMovie().getID(), getResources().getString(R.string.API_KEY), new RetrofitConnection.GetReviewCallBack() {
            @Override
            public void getReview(ArrayList<Review> ParReviews, boolean successful) {
                if(successful){
                    reviews.clear();
                    reviews.addAll(ParReviews);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        return inflater.inflate(R.layout.fragment_review, container, false);
    }

}
