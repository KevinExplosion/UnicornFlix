package com.example.guest.unicornflix.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.unicornflix.R;
import com.example.guest.unicornflix.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public MovieListAdapter (Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    //instantiates the viewholder and binds the layout to the context of the parent element//

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    //takes each movie and binds it to it's position in the recycler view//

    @Override
    public void onBindViewHolder(MovieListAdapter.MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    //checks size of movie array//

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.posterImageView) ImageView mPosterImageView;
        @Bind(R.id.movieTitleTextView) TextView mMovieTitleTextView;
        @Bind(R.id.overviewTextView) TextView mOverviewTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        @Bind(R.id.releaseDateTextView) TextView mReleaseDateTextView;
        private Context mContext;


        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindMovie(Movie movie) {
            String image = movie.getMovieImage();
            if (image.equals("http://image.tmdb.org/t/p/w500null")) {
                Picasso.with(mContext).load(R.drawable.movie_placeholder).into(mPosterImageView);
            } else {
                Picasso.with(mContext).load(movie.getMovieImage()).into(mPosterImageView);
            }
            mMovieTitleTextView.setText(movie.getTitle());
            mRatingTextView.setText(movie.getVoteAverage().toString() + "/10");
            mOverviewTextView.setText(movie.getOverview());
            mReleaseDateTextView.setText(movie.getReleaseDate());
        }
    }
}
