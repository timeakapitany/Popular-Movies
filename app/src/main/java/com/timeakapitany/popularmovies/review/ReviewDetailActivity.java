package com.timeakapitany.popularmovies.review;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.timeakapitany.popularmovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewDetailActivity extends AppCompatActivity {
    public static final String CURRENT_REVIEW = "current review";
    @BindView(R.id.author_detail)
    TextView authorDetail;
    @BindView(R.id.review_detail)
    TextView reviewDetail;

    private Review review;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            review = savedInstanceState.getParcelable(CURRENT_REVIEW);
        } else {
            review = getIntent().getParcelableExtra(CURRENT_REVIEW);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        authorDetail.setText(review.getAuthor());
        reviewDetail.setText(review.getContent());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CURRENT_REVIEW, review);
        super.onSaveInstanceState(outState);
    }
}

