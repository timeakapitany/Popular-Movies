package com.timeakapitany.popularmovies.movie;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.timeakapitany.popularmovies.JsonParser;
import com.timeakapitany.popularmovies.NetworkUtils;
import com.timeakapitany.popularmovies.R;
import com.timeakapitany.popularmovies.db.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    public MutableLiveData<List<Movie>> movieLiveData = new MutableLiveData<>();
    private int movieUrl = R.string.popular_url;

    public MovieViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadData(int menuId) {
        if (menuId == R.id.menuMostPopular) {
            movieUrl = R.string.popular_url;
            startNetworkCall();
        } else if (menuId == R.id.menuTopRated) {
            movieUrl = R.string.toprated_url;
            startNetworkCall();
        } else {
            movieLiveData.postValue(AppDatabase.getInstance(getApplication().getApplicationContext()).favoriteDao().loadAllFavorites());
        }
    }

    private void startNetworkCall() {
        DiscoverMoviesAsyncTask discoverMoviesAsyncTask = new DiscoverMoviesAsyncTask();
        discoverMoviesAsyncTask.execute(getApplication().getResources().getString(movieUrl), getApplication().getResources().getString(R.string.api_key));
    }

    @SuppressLint("StaticFieldLeak")
    class DiscoverMoviesAsyncTask extends AsyncTask<String, Void, List<Movie>> {
        private static final String TAG = "DiscoverMoviesAsyncTask";


        @Override
        protected List<Movie> doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: " + strings[0]);
            List<Movie> movies = new ArrayList<>();
            String movieFeed = NetworkUtils.downloadData(strings[0] + strings[1]);
            if (movieFeed != null) {
                movies = JsonParser.parseMovieJson(movieFeed);
            }
            return movies;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            Log.d(TAG, "onPostExecute: " + movies);
            movieLiveData.postValue(movies);
        }
    }

}
