package com.timeakapitany.popularmovies.movie;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.timeakapitany.popularmovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageView)
    ImageView imageView;

    public MovieViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
