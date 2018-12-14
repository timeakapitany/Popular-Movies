package com.timeakapitany.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class JsonParser {
    private static final String TAG = "JsonParser";

    public static List<Movie> parseMovieJson(String json) {
        List<Movie> movieList = new ArrayList<>();

        try {
            JSONObject movieObject = new JSONObject(json);
            JSONArray results = movieObject.getJSONArray("results");

            Movie movie;
            for (int i = 0; i < results.length(); i++) {
                movie = new Movie();
                JSONObject jsonObject = results.getJSONObject(i);
                movie.setId(jsonObject.getInt("id"));
                movie.setOverview(jsonObject.getString("overview"));
                movie.setPopularity(jsonObject.getDouble("popularity"));
                movie.setPosterPath(jsonObject.getString("poster_path"));
                movie.setReleaseDate(jsonObject.getString("release_date"));
                movie.setTitle(jsonObject.getString("original_title"));
                movie.setVoteAverage(jsonObject.getDouble("vote_average"));

                movieList.add(movie);
            }


        } catch (Exception e) {
            Log.d(TAG, "parseMovieJson: " + e.getMessage());

        }

        return movieList;


    }


}
