package com.example.popularmovies.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity
public class Movie  {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int ID;
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterImageThumbnail;
    @SerializedName("overview")
    private String overview;
    @SerializedName("vote_average")
    private double userRating;
    @SerializedName("release_date")
    private String releaseDate;


    public Movie() {
        this.ID = 0;
        this.title = "";
        this.posterImageThumbnail = "";
        this.overview = "";
        this.userRating = 0;
        this.releaseDate = "";
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterImageThumbnail() {
        return posterImageThumbnail;
    }

    public void setPosterImageThumbnail(String posterImageThumbnail) {
        this.posterImageThumbnail = posterImageThumbnail;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


}
