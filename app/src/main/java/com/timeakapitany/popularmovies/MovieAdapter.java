package com.timeakapitany.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> items = new ArrayList<>();

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {
        Movie currentItem = items.get(position);
        Context context = holder.imageView.getContext();
        String url = context.getString(R.string.image_path) + currentItem.getPosterPath();
        Picasso.with(context)
                .load(url)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Movie> movies) {
        this.items = movies;
        notifyDataSetChanged();
    }
}
