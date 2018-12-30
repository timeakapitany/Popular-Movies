package com.timeakapitany.popularmovies;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

    Movie movie;



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
}
