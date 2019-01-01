package com.timeakapitany.popularmovies.review;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.timeakapitany.popularmovies.JsonParser;
import com.timeakapitany.popularmovies.NetworkUtils;
import com.timeakapitany.popularmovies.R;
import com.timeakapitany.popularmovies.movie.Movie;

import java.util.List;

public class ReviewActivity extends AppCompatActivity {

    public static final String CURRENT_MOVIE = "current movie";
    private ReviewAdapter reviewAdapter;
    private ReviewAsyncTask reviewAsyncTask;
    private Movie movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        if (savedInstanceState != null) {
            movie = savedInstanceState.getParcelable(CURRENT_MOVIE);
        } else {
            movie = getIntent().getParcelableExtra(CURRENT_MOVIE);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle(movie.getTitle());
        }
        setupRecyclerView();
        startNetworkCall();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CURRENT_MOVIE, movie);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (reviewAsyncTask != null) {
            reviewAsyncTask.cancel(true);
        }
    }

    private void startNetworkCall() {
        reviewAsyncTask = new ReviewAsyncTask();
        reviewAsyncTask.execute(getString(R.string.review_url, movie.getId().toString(), getString(R.string.api_key)));
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewAdapter = new ReviewAdapter();
        recyclerView.setAdapter(reviewAdapter);
    }

    @SuppressLint("StaticFieldLeak")
    class ReviewAsyncTask extends AsyncTask<String, Void, List<Review>> {
        private static final String TAG = "ReviewAsyncTask";


        @Override
        protected List<Review> doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: " + strings[0]);
            String reviewFeed = NetworkUtils.downloadData(strings[0]);
            if (reviewFeed != null) {
                return JsonParser.parseReviewJson(reviewFeed);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Review> reviews) {
            super.onPostExecute(reviews);
            if (reviews != null && reviews.size() != 0) {
                Log.d(TAG, "onPostExecute: " + reviews);
                reviewAdapter.setItems(reviews);
            } else {
                Toast.makeText(ReviewActivity.this, "No reviews...", Toast.LENGTH_LONG).show();
            }
        }


    }
}
