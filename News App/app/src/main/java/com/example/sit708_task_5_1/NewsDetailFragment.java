package com.example.sit708_task_5_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

/**
 * A Fragment class for displaying detailed news content including related news items.
 */
public class NewsDetailFragment extends Fragment {
    // Variables to hold the news title, description, and image resource ID
    private String newsTitle;
    private String newsDescription;
    private int newsImage;
    // Adapter for the related news RecyclerView
    private RelativeNewsAdapter relativeNewsAdapter;
    // Layout manager for the RecyclerView
    private LinearLayoutManager linearLayoutManager;

    /**
     * Required empty public constructor
     */
    public NewsDetailFragment() {
        // empty public constructor
    }

    /**
     * Static factory method to create new instances of this fragment using the provided parameters.
     *
     * @param title       The title of the news.
     * @param description The description of the news.
     * @param imageResId  The resource ID for the news image.
     * @return A new instance of fragment NewsDetailFragment.
     */
    public static NewsDetailFragment newInstance(String title, String description, int imageResId) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        args.putInt("imageResId", imageResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve and store the arguments passed to this fragment
        if (getArguments() != null) {
            newsTitle = getArguments().getString("title");
            newsDescription = getArguments().getString("description");
            newsImage = getArguments().getInt("imageResId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        // Setup the TextView and ImageView for news details
        TextView tvDescription = view.findViewById(R.id.newsDescription);
        ImageView ivImage = view.findViewById(R.id.newsImage);
        tvDescription.setText(newsDescription);
        ivImage.setImageResource(newsImage);

        // Setup the RecyclerView for related news items
        RecyclerView rvRelatedNews = view.findViewById(R.id.relatedNewsRv);
        setupRecyclerView(rvRelatedNews);

        return view;
    }

    /**
     * Sets up the RecyclerView for displaying related news items.
     *
     * @param recyclerView The RecyclerView to be set up.
     */
    private void setupRecyclerView(RecyclerView recyclerView) {
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        int[] array = new int[6];
        Arrays.fill(array, R.drawable.ic_launcher_background);
        relativeNewsAdapter = new RelativeNewsAdapter(array);
        recyclerView.setAdapter(relativeNewsAdapter);
        recyclerView.setHasFixedSize(true);
    }
}
