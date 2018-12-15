package com.timeakapitany.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class JsonParser {
    private static final String TAG = "JsonParser";

    static List<Movie> parseMovieJson(String json) {
        List<Movie> movieList = new ArrayList<>();

        try {

            JSONObject movieObject = new JSONObject(json);
            JSONArray results = movieObject.getJSONArray("results");

            Movie movie;
            for (int i = 0; i < results.length(); i++) {
                movie = new Movie();
                JSONObject jsonObject = results.getJSONObject(i);
                if (jsonObject.has("id")) {
                    movie.setId(jsonObject.getInt("id"));
                }
                if (jsonObject.has("overview")) {
                    movie.setOverview(jsonObject.getString("overview"));
                }
                if (jsonObject.has("popularity")) {
                    movie.setPopularity(jsonObject.getDouble("popularity"));
                }
                if (jsonObject.has("poster_path")) {
                    movie.setPosterPath(jsonObject.getString("poster_path"));
                }
                if (jsonObject.has("release_date")) {
                    movie.setReleaseDate(jsonObject.getString("release_date"));
                }
                if (jsonObject.has("original_title")) {
                    movie.setTitle(jsonObject.getString("original_title"));
                }
                if (jsonObject.has("vote_average")) {
                    movie.setVoteAverage(jsonObject.getDouble("vote_average"));
                }

                movieList.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }
}
