package com.timeakapitany.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    final ImageView imageView;

    public MovieViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
    }
}
