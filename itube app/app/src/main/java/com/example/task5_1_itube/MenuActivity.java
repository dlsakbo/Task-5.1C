package com.example.task5_1_itube;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    // Declare UI elements
    private EditText etYouTubeLink;
    private Button btnPlay;
    private Button btnAddToPlaylist;
    private Button btnMyPlaylist;
    private UserDatabaseHelper userDbHelper;
    private final String youtubeUrlRegex = "^(https?)?(://)?(www.)?(m.)?((youtube.com)|(youtu.be))/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        // Initialize UI elements
        etYouTubeLink = findViewById(R.id.linkTextView);
        btnPlay = findViewById(R.id.playButton);
        btnAddToPlaylist = findViewById(R.id.addToPlaylistButton);
        btnMyPlaylist = findViewById(R.id.myPlaylistButton);

        // Initialize the database helper instance
        userDbHelper = new UserDatabaseHelper(this);

        // Set click listener for "Add to Playlist" button
        btnAddToPlaylist.setOnClickListener(v -> {
            String youtubeLink = etYouTubeLink.getText().toString().trim();

            if (!youtubeLink.isEmpty()) {
                boolean isAdded = userDbHelper.addLinkToPlaylist(youtubeLink);

                if (isAdded) {
                    Toast.makeText(this, "Added to Playlist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to add to Playlist", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter a YouTube link", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for "Play" button
        btnPlay.setOnClickListener(v -> {
            String youtubeLink = etYouTubeLink.getText().toString().trim();

            if (!youtubeLink.isEmpty()) {
                String videoId = extractVideoIdFromUrl(youtubeLink);

                if (videoId != null && !videoId.isEmpty()) {
                    Intent intent = new Intent(MenuActivity.this, VideoPlayActivity.class);
                    intent.putExtra("VIDEO_ID", videoId);
                    startActivity(intent);
                } else {
                    Toast.makeText(MenuActivity.this, "Invalid YouTube Link", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter a YouTube link", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for "My Playlist" button
        btnMyPlaylist.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MyPlaylistActivity.class);
            startActivity(intent);
        });
    }

    private String extractVideoIdFromUrl(String url) {
        String cleanedUrl = removeProtocolAndDomainFromUrl(url);

        final String[] videoIdPatterns = {
                "\\?vi?=([^&]*)",
                "watch\\?.*v=([^&]*)",
                "(?:embed|vi?)/([^/?]*)",
                "^([A-Za-z0-9\\-]*)"
        };

        for (String pattern : videoIdPatterns) {
            if (Pattern.compile(pattern).matcher(cleanedUrl).find()) {
                return matcher.group(1);
            }
        }

        return null;
    }

    private String removeProtocolAndDomainFromUrl(String url) {
        Matcher matcher = Pattern.compile(youtubeUrlRegex).matcher(url);

        if (matcher.find()) {
            return url.replace(matcher.group(), "");
        }

        return url;
    }
}
