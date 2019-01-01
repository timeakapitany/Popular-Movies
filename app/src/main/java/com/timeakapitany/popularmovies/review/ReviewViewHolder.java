package com.timeakapitany.popularmovies.review;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.timeakapitany.popularmovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.review)
    TextView review;

    public ReviewViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
