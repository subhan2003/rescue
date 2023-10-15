package com.example.rescue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

public class PublicSafety extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_safety); // make sure you have this layout XML in your resources

        ImageButton cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> {
            finish(); // This will close the current activity and go back to the previous activity
        });

        ImageButton submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(v -> {
            Intent intent = new Intent(PublicSafety.this, MainActivity.class);
            Toast.makeText(this, "Report Submitted", Toast.LENGTH_SHORT).show();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // This flag will clear all activities on top of the MainActivity
            startActivity(intent);
        });

    }
}
