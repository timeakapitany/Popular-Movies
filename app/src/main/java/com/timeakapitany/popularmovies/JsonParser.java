package com.timeakapitany.popularmovies;

import com.timeakapitany.popularmovies.movie.Movie;
import com.timeakapitany.popularmovies.movie.Trailer;
import com.timeakapitany.popularmovies.review.Review;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
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

    public static List<Trailer> parseTrailerJson(String json) {
        List<Trailer> trailerList = new ArrayList<>();

        try {

            JSONObject trailerObject = new JSONObject(json);
            JSONArray results = trailerObject.getJSONArray("results");

            Trailer trailer;
            for (int i = 0; i < results.length(); i++) {
                trailer = new Trailer();
                JSONObject jsonObject = results.getJSONObject(i);
                if (jsonObject.has("id")) {
                    trailer.setId(jsonObject.getString("id"));
                }
                if (jsonObject.has("key")) {
                    trailer.setKey(jsonObject.getString("key"));
                }
                if (jsonObject.has("name")) {
                    trailer.setName(jsonObject.getString("name"));
                }
                if (jsonObject.has("site")) {
                    trailer.setSite(jsonObject.getString("site"));
                }
                if (jsonObject.has("size")) {
                    trailer.setSize(jsonObject.getInt("size"));
                }
                if (jsonObject.has("type")) {
                    trailer.setType(jsonObject.getString("type"));
                }

                trailerList.add(trailer);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trailerList;
    }

    public static List<Review> parseReviewJson(String json) {
        List<Review> reviewList = new ArrayList<>();

        try {

            JSONObject reviewObject = new JSONObject(json);
            JSONArray results = reviewObject.getJSONArray("results");

            Review review;
            for (int i = 0; i < results.length(); i++) {
                review = new Review();
                JSONObject jsonObject = results.getJSONObject(i);
                if (jsonObject.has("id")) {
                    review.setId(jsonObject.getString("id"));
                }
                if (jsonObject.has("author")) {
                    review.setAuthor(jsonObject.getString("author"));
                }
                if (jsonObject.has("content")) {
                    review.setContent(jsonObject.getString("content"));
                }

                reviewList.add(review);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reviewList;
    }
}
