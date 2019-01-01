package com.timeakapitany.popularmovies.movie;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.timeakapitany.popularmovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.trailer_image)
    ImageView trailerImage;

    public TrailerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
