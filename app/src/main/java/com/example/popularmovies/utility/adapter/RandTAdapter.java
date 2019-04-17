package com.example.popularmovies.utility.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.popularmovies.R;
import com.example.popularmovies.model.Review;
import com.example.popularmovies.model.Singalton;
import com.example.popularmovies.model.Trailer;

import java.util.ArrayList;

public class RandTAdapter extends RecyclerView.Adapter<RandTAdapter.RandTVH> {

    private ArrayList list;
    private Context context;
    private Singalton data;

    public RandTAdapter(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
        data = Singalton.getInstance(context);
    }

    @NonNull
    @Override
    public RandTVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.r_t_item,viewGroup,false);
        return new RandTVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandTVH randTVH, int i) {


        if(data.isReview()){

            Review review = (Review) list.get(i);
            randTVH.name.setText(review.getAuthor());
            randTVH.content.setText(review.getContent());

        }else{
            Trailer trailer = (Trailer) list.get(i);
            randTVH.content.setVisibility(View.INVISIBLE);
            randTVH.name.setText(trailer.getTitle());
            randTVH.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RandTVH extends RecyclerView.ViewHolder {

        TextView name, content;

        public RandTVH(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            content = itemView.findViewById(R.id.content);
        }
    }
}
