package com.example.popularmovies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.popularmovies.model.Movie;
import com.example.popularmovies.model.Singalton;
import com.example.popularmovies.utility.adapter.TapAdapter;
import com.example.popularmovies.utility.fav.FavVM;
import com.example.popularmovies.utility.fav.RoomDB;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    private Movie movie;
    private TextView name,overview,rate,date;
    private ToggleButton fav;
    private ImageView pic;

    private Singalton data;
    private RoomDB db;
    private final static String BASE_URL = "http://image.tmdb.org/t/p/" + "w185/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        data = Singalton.getInstance(getApplicationContext());
        movie = data.getMovie();
        db = data.getDb();

        name=findViewById(R.id.name);
        overview=findViewById(R.id.overview);
        rate=findViewById(R.id.rate);
        date=findViewById(R.id.date);
        pic=findViewById(R.id.imageView);

        fav = findViewById(R.id.fav);

        TabLayout tab = findViewById(R.id.tabLayout);
        ViewPager page = findViewById(R.id.view_pager);
        TapAdapter adapter = new TapAdapter(getSupportFragmentManager());
        page.setAdapter(adapter);
        tab.setupWithViewPager(page,true);

    }

    @Override
    protected void onStart() {

        name.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        String rating = movie.getUserRating()+"";
        rate.setText(rating);
        date.setText(movie.getReleaseDate());
        Picasso.get()
                .load(BASE_URL+movie.getPosterImageThumbnail())
                .placeholder(R.drawable.ic_launcher_background)
                .into(pic);

        isFav();

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleButton checked = (ToggleButton) view;
                new FavTask().execute(checked.isChecked());
            }
        });


        super.onStart();
    }

    private void isFav() {

        FavVM vm = ViewModelProviders.of(this).get(FavVM.class);
        vm.getMovieLiveData().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {

                if (movie==null) {
                    fav.setChecked(false);
                    fav.setBackgroundDrawable(getDrawable(android.R.drawable.btn_star_big_off));
                }
                else {
                    fav.setChecked(true);
                    fav.setBackgroundDrawable(getDrawable(android.R.drawable.btn_star_big_on));

                }

            }
        });
    }


    public class FavTask extends AsyncTask<Boolean,Void,Boolean>{


        @Override
        protected Boolean doInBackground(Boolean... booleans) {

            if(booleans[0]){
                db.favDao().addFavMovie(movie);
            }else{
                db.favDao().deleteFavMovie(movie);
            }

            return booleans[0];
        }
    }


}
