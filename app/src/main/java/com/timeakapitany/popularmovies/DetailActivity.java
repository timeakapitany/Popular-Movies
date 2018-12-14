package com.timeakapitany.popularmovies;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public static final String CURRENT_MOVIE = "current movie";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getActionBar() != null) getActionBar().setDisplayHomeAsUpEnabled(true);

        Movie movie = getIntent().getParcelableExtra(CURRENT_MOVIE);

        ImageView image = findViewById(R.id.image_detail);
        String url = this.getString(R.string.image_path) + movie.getPosterPath();
        Picasso.with(this)
                .load(url)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(image);

        TextView title = findViewById(R.id.title);
        title.setText(movie.getTitle());

        TextView releaseDate = findViewById(R.id.release_date);
        releaseDate.setText(movie.getReleaseDate());

        TextView voteAverage = findViewById(R.id.vote_average);
        voteAverage.setText(movie.getVoteAverage().toString());

        TextView plotSynopsis = findViewById(R.id.plot_synopsis);
        plotSynopsis.setText(movie.getOverview());


    }
}
