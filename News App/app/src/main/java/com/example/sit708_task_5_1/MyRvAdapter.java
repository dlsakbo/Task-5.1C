package com.example.sit708_task_5_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter class for RecyclerView that handles strings as data.
 */
public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.ViewHolder> {
    // List of data items represented as strings
    private ArrayList<String> data;
    // Listener for handling item click events
    private OnItemClickListener listener;

    /**
     * Constructor to initialize the adapter with data.
     *
     * @param data An ArrayList of Strings used as data for the RecyclerView.
     */
    public MyRvAdapter(ArrayList<String> data) {
        this.data = data;
    }

    /**
     * Interface for click events in the RecyclerView's items.
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /**
     * Sets the listener for item click events.
     *
     * @param listener The listener that will handle item click events.
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item of the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind data to the TextView of each item
        holder.tvTitle.setText(data.get(position));
        // Set an OnClickListener to the entire row
        holder.itemView.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            // Trigger the listener's method if item is clicked and the listener has been set
            if (listener != null && currentPosition != RecyclerView.NO_POSITION) {
                listener.onItemClick(currentPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Return the size of the data list
        return data.size();
    }

    /**
     * ViewHolder class that holds the views for the RecyclerView.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle; // TextView for displaying the title

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the TextView from the layout
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
