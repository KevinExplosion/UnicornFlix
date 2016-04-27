package com.example.guest.unicornflix.models;


public class Movie {
    private String mTitle;
    private String mReleaseDate;
    private Double mVoteAverage;
    private String mOverview;

    public Movie(String title, String releaseDate, Double voteAverage, String overview) {
        this.mTitle = title;
        this.mReleaseDate = releaseDate;
        this.mVoteAverage = voteAverage;
        this.mOverview = overview;
    }

    public String getTitle() {
        return mTitle;
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
