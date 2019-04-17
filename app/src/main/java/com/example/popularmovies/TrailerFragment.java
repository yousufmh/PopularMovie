package com.example.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popularmovies.model.Singalton;
import com.example.popularmovies.utility.adapter.RandTAdapter;
import com.example.popularmovies.utility.retrofit.RetrofitConnection;
import com.example.popularmovies.model.Trailer;

import java.util.ArrayList;


public class TrailerFragment extends Fragment {

    public TrailerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Singalton data = Singalton.getInstance(getActivity());
        data.setReview(false);
        RecyclerView rv = container.findViewById(R.id.rv_trailer);
        final ArrayList<Trailer> trailers = new ArrayList<>();
        final RandTAdapter adapter = new RandTAdapter(getContext(),trailers);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);
        RetrofitConnection connection = new RetrofitConnection(rv);
        connection.getTrailer(""+data.getMovie().getID(), getResources().getString(R.string.API_KEY), new RetrofitConnection.GetTrailerCallBack() {
            @Override
            public void getTrailer(ArrayList<Trailer> ParTrailers, boolean successful) {
                if(successful){
                    trailers.clear();
                    trailers.addAll(ParTrailers);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        return inflater.inflate(R.layout.fragment_trailer, container, false);
    }


}
