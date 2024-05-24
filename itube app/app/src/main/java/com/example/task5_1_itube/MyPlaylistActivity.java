package com.example.task5_1_itube;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyPlaylistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserDatabaseHelper userDbHelper;
    private List<String> playlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myplaylist);

        userDbHelper = new UserDatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        playlist = new ArrayList<>();
        Cursor cursor = userDbHelper.getPlaylist();

        if (cursor != null) {
            int linkColumnIndex = cursor.getColumnIndex("link");

            if (linkColumnIndex >= 0) {
                while (cursor.moveToNext()) {
                    String link = cursor.getString(linkColumnIndex);
                    playlist.add(link);
                }
                cursor.close();
            } else {
                Toast.makeText(this, "Column 'link' not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Failed to retrieve playlist", Toast.LENGTH_SHORT).show();
        }

        PlaylistAdapter adapter = new PlaylistAdapter(playlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private static class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {

        private final List<String> playlist;

        PlaylistAdapter(List<String> playlist) {
            this.playlist = playlist;
        }

        @Override
        public PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item, parent, false);
            return new PlaylistViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PlaylistViewHolder holder, int position) {
            String link = playli
