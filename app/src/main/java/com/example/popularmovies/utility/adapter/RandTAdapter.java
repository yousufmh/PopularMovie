package com.example.popularmovies.utility.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies.R;
import com.example.popularmovies.model.Review;
import com.example.popularmovies.model.Singalton;
import com.example.popularmovies.model.Trailer;

import java.util.ArrayList;

public class RandTAdapter extends RecyclerView.Adapter<RandTAdapter.RandTVH> {

    private ArrayList list;
    private Context context;
    private boolean isReview;

    public RandTAdapter(boolean isReview, Context context, ArrayList list) {
        this.context = context;
        this.list = list;
        this.isReview = isReview;
    }

    @NonNull
    @Override
    public RandTVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.r_t_item,viewGroup,false);
        return new RandTVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandTVH randTVH, int i) {


        if(isReview){

            Review review = (Review) list.get(i);
            randTVH.share.setVisibility(View.INVISIBLE);
            randTVH.name.setText(review.getAuthor());
            randTVH.content.setText(review.getContent());

        }else{
            final Trailer trailer = (Trailer) list.get(i);
            randTVH.content.setVisibility(View.INVISIBLE);
            randTVH.name.setText(trailer.getTitle());

            randTVH.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, "http://www.youtube.com/watch?v="+ trailer.getId());
                    intent.setType("text/plain");
                    context.startActivity(Intent.createChooser(intent, "Sharing Youtube Link"));
                }
            });

            randTVH.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+ trailer.getId()));
                    context.startActivity(intent);

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
        ImageView share;

        public RandTVH(@NonNull View itemView) {
            super(itemView);
            share = itemView.findViewById(R.id.share);
            name = itemView.findViewById(R.id.name);
            content = itemView.findViewById(R.id.content);
        }
    }
}
