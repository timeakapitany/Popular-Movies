package com.timeakapitany.popularmovies;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String MOVIE_URL = "movie.url";
    private MovieAdapter movieAdapter;
    private DiscoverMoviesAsyncTask discoverMoviesAsyncTask;
    private int movieUrl = R.string.popular_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            movieUrl = savedInstanceState.getInt(MOVIE_URL);
        }
        setupRecyclerView();
        startNetworkCall();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sort_order, menu);
        if (movieUrl == R.string.toprated_url) {
            menu.findItem(R.id.menuTopRated).setChecked(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuMostPopular) {
            movieUrl = R.string.popular_url;
            item.setChecked(true);
        } else if (id == R.id.menuTopRated) {
            movieUrl = R.string.toprated_url;
            item.setChecked(true);
        } else {
            return super.onOptionsItemSelected(item);
        }
        startNetworkCall();
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(MOVIE_URL, movieUrl);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (discoverMoviesAsyncTask != null) {
            discoverMoviesAsyncTask.cancel(true);
        }
    }

    private void startNetworkCall() {
        discoverMoviesAsyncTask = new DiscoverMoviesAsyncTask();
        discoverMoviesAsyncTask.execute(getString(movieUrl), getString(R.string.api_key));
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);
    }

    @SuppressLint("StaticFieldLeak")
    class DiscoverMoviesAsyncTask extends AsyncTask<String, Void, List<Movie>> {
        private static final String TAG = "DiscoverMoviesAsyncTask";


        @Override
        protected List<Movie> doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: " + strings[0]);
            String movieFeed = NetworkUtils.downloadData(strings[0] + strings[1]);
            if (movieFeed != null) {
                return JsonParser.parseMovieJson(movieFeed);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            if (movies != null) {
                Log.d(TAG, "onPostExecute: " + movies);
                movieAdapter.setItems(movies);
            } else {
                Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_LONG).show();
            }
        }




    }
}
