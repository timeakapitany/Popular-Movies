package com.timeakapitany.popularmovies.movie;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.timeakapitany.popularmovies.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String MENU_ID = "menu.id";
    private MovieAdapter movieAdapter;
    private int menuId = R.id.menuMostPopular;
    private MovieViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecyclerView();
        if (savedInstanceState != null) {
            menuId = savedInstanceState.getInt(MENU_ID);
        }

        model = ViewModelProviders.of(this).get(MovieViewModel.class);
        model.movieLiveData.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                movieAdapter.setItems(movies);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        model.loadData(menuId);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sort_order, menu);
        menu.findItem(menuId).setChecked(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        menuId = item.getItemId();
        model.loadData(menuId);
        item.setChecked(true);
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(MENU_ID, menuId);
        super.onSaveInstanceState(outState);
    }


    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);
    }



}
