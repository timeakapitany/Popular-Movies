package com.timeakapitany.popularmovies.movie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;
import com.timeakapitany.popularmovies.JsonParser;
import com.timeakapitany.popularmovies.NetworkUtils;
import com.timeakapitany.popularmovies.R;
import com.timeakapitany.popularmovies.db.AppDatabase;
import com.timeakapitany.popularmovies.review.ReviewActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    public static final String CURRENT_MOVIE = "current movie";
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.vote_average)
    TextView voteAverage;
    @BindView(R.id.plot_synopsis)
    TextView plotSynopsis;
    @BindView(R.id.favorite_button)
    ToggleButton favoriteButton;


    Movie movie;
    private TrailerAdapter trailerAdapter;
    private TrailerAsyncTask trailerAsyncTask;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            movie = savedInstanceState.getParcelable(CURRENT_MOVIE);
        } else {
            movie = getIntent().getParcelableExtra(CURRENT_MOVIE);
        }

        ImageView image = findViewById(R.id.image_detail);
        String url = this.getString(R.string.image_path) + movie.getPosterPath();
        Picasso.with(this)
                .load(url)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(image);

        title.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());
        voteAverage.setText(movie.getVoteAverage().toString());
        plotSynopsis.setText(movie.getOverview());

        Boolean isFavorite = AppDatabase.getInstance(getApplicationContext()).favoriteDao().isFavorite(movie.getId());
        favoriteButton.setChecked(isFavorite);

        setupRecyclerView();
        startNetworkCall();

        favoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppDatabase.getInstance(getApplicationContext()).favoriteDao().insertFavorites(movie);
                } else {
                    AppDatabase.getInstance(getApplicationContext()).favoriteDao().deleteFavorites(movie);
                }
            }
        });
    }

    public void onReviewButtonClicked(View view) {
        Intent intent = new Intent(this, ReviewActivity.class);
        intent.putExtra(ReviewActivity.CURRENT_MOVIE, movie);
        this.startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CURRENT_MOVIE, movie);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (trailerAsyncTask != null) {
            trailerAsyncTask.cancel(true);
        }
    }

    private void startNetworkCall() {
        trailerAsyncTask = new TrailerAsyncTask();
        trailerAsyncTask.execute(getString(R.string.trailer_url, movie.getId().toString(), getString(R.string.api_key)));
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.trailer_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        trailerAdapter = new TrailerAdapter();
        recyclerView.setAdapter(trailerAdapter);
    }

    @SuppressLint("StaticFieldLeak")
    class TrailerAsyncTask extends AsyncTask<String, Void, List<Trailer>> {
        private static final String TAG = "TrailerAsyncTask";


        @Override
        protected List<Trailer> doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: " + strings[0]);
            String trailerFeed = NetworkUtils.downloadData(strings[0]);
            if (trailerFeed != null) {
                return JsonParser.parseTrailerJson(trailerFeed);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Trailer> trailers) {
            super.onPostExecute(trailers);
            if (trailers != null) {
                Log.d(TAG, "onPostExecute: " + trailers);
                trailerAdapter.setItems(trailers);
            }
        }


    }
}
