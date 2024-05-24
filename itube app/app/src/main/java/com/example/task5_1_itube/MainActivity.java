package com.example.task5_1_itube;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

public class LoginActivity extends AppCompatActivity {

    // Declare UI elements and database helper
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;
    private UserDatabaseHelper userDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display for the activity
        EdgeToEdge.enable(this);

        // Set the layout resource to activity_main.xml
        setContentView(R.layout.activity_main);

        // Ensure proper display in full-screen mode by setting padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements by finding them from the layout
        etUsername = findViewById(R.id.usernameInput); // Username input field
        etPassword = findViewById(R.id.passwordInput); // Password input field
        btnLogin = findViewById(R.id.loginButton);     // Login button
        btnSignUp = findViewById(R.id.signupButton);   // Signup button

        // Initialize the database helper to manage user data
        userDbHelper = new UserDatabaseHelper(this);

        // Set a click listener for the login button
        btnLogin.setOnClickListener(v -> {
            // Retrieve the text entered by the user in the username and password fields
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            // Validate the entered username and password against the database
            if (userDbHelper.validateUser(username, password)) {
                // Display a success message using a toast
                Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                // Create an intent to start the DashboardActivity
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent); // Start the DashboardActivity

                // Close the current activity to prevent the user from returning to the login screen
                finish();
            } else {
                // Display an error message if the username or password is invalid
                Toast.makeText(LoginActivity.this, "Invalid username or password.", Toast.LENGTH_SHORT).show();
            }
        });

        // Set a click listener for the signup button
        btnSignUp.setOnClickListener(v -> {
            // Create an intent to start the RegisterActivity
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent); // Start the RegisterActivity
        });
    }
}

