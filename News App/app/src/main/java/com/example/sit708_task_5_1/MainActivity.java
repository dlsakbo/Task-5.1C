package com.example.sit708_task_5_1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Main activity class that manages two RecyclerViews, one for horizontal display and one for grid display.
 */
public class MainActivity extends AppCompatActivity {
    // RecyclerView for horizontal display
    private RecyclerView horizontalRecyclerView;
    // Data source for the horizontal RecyclerView
    private ArrayList<String> horizontalDataSource;
    // LayoutManager for horizontal RecyclerView
    private LinearLayoutManager horizontalLinearLayoutManager;
    // Adapter for the horizontal RecyclerView
    private MyRvAdapter horizontalRvAdapter;
    // RecyclerView for grid display
    private RecyclerView gridRecyclerView;
    // Adapter for the grid RecyclerView
    private RvAdapter gridRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the horizontal RecyclerView
        initHorizontalRecyclerView();
        // Initialize the grid RecyclerView
        initGridRecyclerView();
    }

    /**
     * Initializes the horizontal RecyclerView with LinearLayoutManager and sets the adapter.
     */
    private void initHorizontalRecyclerView() {
        horizontalRecyclerView = findViewById(R.id.horizontalRv);
        horizontalDataSource = new ArrayList<>(Arrays.asList("News", "News", "News", "News", "News", "News"));
        horizontalLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horizontalRvAdapter = new MyRvAdapter(horizontalDataSource);
        horizontalRecyclerView.setLayoutManager(horizontalLinearLayoutManager);
        horizontalRecyclerView.setAdapter(horizontalRvAdapter);

        // Set the click listener for the horizontal RecyclerView items
        horizontalRvAdapter.setOnItemClickListener(position -> displayDetailsFragment(horizontalDataSource.get(position), "Sample Description", R.drawable.ic_launcher_background));
    }

    /**
     * Initializes the grid RecyclerView with GridLayoutManager and sets the adapter.
     */
    private void initGridRecyclerView() {
        gridRecyclerView = findViewById(R.id.newsRv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        int[] imageResources = new int[6];
        Arrays.fill(imageResources, R.drawable.ic_launcher_background);
        gridRvAdapter = new RvAdapter(imageResources);
        gridRecyclerView.setLayoutManager(gridLayoutManager);
        gridRecyclerView.setAdapter(gridRvAdapter);
        gridRecyclerView.setHasFixedSize(true);

        // Set the click listener for the grid RecyclerView items
        gridRvAdapter.setOnItemClickListener(position -> displayDetailsFragment("Sample Title", "Sample Description", R.drawable.ic_launcher_background));
    }

    /**
     * Displays a detail fragment for the clicked news item.
     *
     * @param title       The title of the news item.
     * @param description The description of the news item.
     * @param imageResId  The resource ID for the image.
     */
    private void displayDetailsFragment(String title, String description, int imageResId) {
        Log.d("NewsApp", "News item clicked, title: " + title);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.newsDetailFragmentContainer, NewsDetailFragment.newInstance(title, description, imageResId))
                .addToBackStack(null)
                .commit();

        // Make the container visible
        findViewById(R.id.newsDetailFragmentContainer).setVisibility(View.VISIBLE);
    }
}
