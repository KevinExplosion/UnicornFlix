package com.example.guest.unicornflix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
    }

    public ArrayList<Movie> processResults (Response response) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONObject movieDbJSON = new JSONObject(jsonData);
                JSONArray moviesJSON = movieDbJSON.getJSONArray("results");
                for (int i = 0; i <moviesJSON.length(); i++) {
                    JSONObject movieJSON = moviesJSON.getJSONObject(i);
                    String title = movieJSON.getString("title");
                    String releaseDate = movieJSON.getString("release_date");
                    Double voteAverage = movieJSON.getDouble("vote_average");
                    String overview = movieJSON.getString("overview");

                    Movie movie = new Movie(title, releaseDate, voteAverage, overview);
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return movies;
    }

}
