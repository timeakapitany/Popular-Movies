package com.timeakapitany.popularmovies;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Movie movie = getIntent().getParcelableExtra(CURRENT_MOVIE);

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

}
