package com.example.sit708_task_5_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter for a RecyclerView that displays related news items with images.
 */
public class RelativeNewsAdapter extends RecyclerView.Adapter<RelativeNewsAdapter.ViewHolder> {
    // Array of image resource IDs for the news items
    private int[] imageResIds;

    /**
     * Constructor for the RelativeNewsAdapter.
     *
     * @param imageResIds An array of image resource IDs for initializing the adapter.
     */
    public RelativeNewsAdapter(int[] imageResIds) {
        this.imageResIds = imageResIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout from XML and initialize the ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.relative_news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind the image resource to the ImageView in each item of the RecyclerView
        holder.relativeImageView.setImageResource(imageResIds[position]);
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the data set held by the adapter
        return imageResIds.length;
    }

    /**
     * ViewHolder class for the RecyclerView, which contains views that display individual news items.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // ImageView for displaying the news image
        ImageView relativeImageView;

        /**
         * Constructor for initializing the views in the ViewHolder
         *
         * @param itemView The view of the individual row/item
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeImageView = itemView.findViewById(R.id.relativeNewsImage);
        }
    }
}
