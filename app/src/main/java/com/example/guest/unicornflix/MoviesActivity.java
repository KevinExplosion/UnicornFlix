package com.example.guest.unicornflix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MoviesActivity extends AppCompatActivity {
    public ArrayList<Movie> mMovies = new ArrayList<>();
    public static final String TAG = MoviesActivity.class.getSimpleName();
    @Bind(R.id.movieListView)
    ListView mMovieListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        getMovies(title);
    }

    private void getMovies(String title) {
        final MovieService movieService = new MovieService();

        movieService.findMovies(title, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mMovies = movieService.processResults(response);

                MoviesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String[] movieNames = new String[mMovies.size()];
                        for (int i=0; i<movieNames.length; i++) {
                            movieNames[i] = mMovies.get(i).getTitle();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(MoviesActivity.this,
                                android.R.layout.simple_list_item_1, movieNames);
                        mMovieListView.setAdapter(adapter);

                        for (Movie movie : mMovies) {
                            Log.d(TAG, "Title: " + movie.getTitle());
                        }
                    }
                });
            }
        });
    }
}
