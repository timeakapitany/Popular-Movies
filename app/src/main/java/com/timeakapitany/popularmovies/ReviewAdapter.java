package com.timeakapitany.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private List<Review> items = new ArrayList<>();

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReviewViewHolder holder, final int position) {
        final Review currentItem = items.get(position);
        final Context context = holder.author.getContext();
        holder.author.setText(currentItem.getAuthor());
        holder.review.setText(currentItem.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReviewDetailActivity.class);
                intent.putExtra(ReviewDetailActivity.CURRENT_REVIEW, currentItem);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Review> reviews) {
        this.items = reviews;
        notifyDataSetChanged();
    }


}
