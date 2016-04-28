package com.example.guest.unicornflix.models;

import org.parceler.Parcel;

@Parcel
public class Movie {
    private String mTitle;
    private String mMovieImage;
    private String mReleaseDate;
    private Double mVoteAverage;
    private String mOverview;

    public Movie() {

    }

    public Movie(String title, String movieImage, String releaseDate, Double voteAverage, String overview) {
        this.mTitle = title;
        this.mMovieImage = movieImage;
        this.mReleaseDate = releaseDate;
        this.mVoteAverage = voteAverage;
        this.mOverview = overview;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getMovieImage() {
        return "http://image.tmdb.org/t/p/w500" + mMovieImage;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public String getOverview() {
        return mOverview;
    }
}
