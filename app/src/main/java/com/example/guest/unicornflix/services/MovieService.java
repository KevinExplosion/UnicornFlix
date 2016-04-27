package com.example.guest.unicornflix.services;

import android.util.Log;

import com.example.guest.unicornflix.Constants;
import com.example.guest.unicornflix.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MovieService {

    public static void findMovies(String title, Callback callback) {
        String MOVIE_DB_KEY = Constants.MOVIE_DB_KEY;


        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MOVIE_DB_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.MOVIE_DB_QUERY, title);
        urlBuilder.addQueryParameter("api_key", MOVIE_DB_KEY);
        String url = urlBuilder.build().toString();

        Log.d("url", url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
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
