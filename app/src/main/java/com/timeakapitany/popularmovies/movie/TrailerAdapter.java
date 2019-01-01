package com.timeakapitany.popularmovies.movie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.timeakapitany.popularmovies.R;

import java.util.ArrayList;
import java.util.List;

class TrailerAdapter extends RecyclerView.Adapter<TrailerViewHolder> {

    private List<Trailer> items = new ArrayList<>();

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrailerViewHolder holder, final int position) {
        final Trailer currentItem = items.get(position);
        final Context context = holder.trailerImage.getContext();
        final String url = context.getString(R.string.trailer_image_path, currentItem.getKey());
        Picasso.with(context)
                .load(url)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.trailerImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.trailer_video_url, currentItem.getKey())));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Trailer> trailers) {
        this.items = trailers;
        notifyDataSetChanged();
    }


}
