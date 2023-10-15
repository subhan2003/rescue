package com.example.rescue;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class ReportIncidentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_incident);

        ImageButton pictureImageView = findViewById(R.id.pictureImageView);
        pictureImageView.setOnClickListener(v -> {
            Intent intent = new Intent(ReportIncidentActivity.this, PublicSafety.class);
            startActivity(intent);
        });


    }
}
