package com.example.rescue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.net.Uri;
import android.widget.Toast;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton medButton = findViewById(R.id.medButton);
        medButton.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:786-852-2800"));

            if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
            } else {
                try {
                    startActivity(callIntent);
                } catch (SecurityException e) {
                    Toast.makeText(MainActivity.this, "Call failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ImageButton reportIncidentButton = findViewById(R.id.reportIncidentButton);
        reportIncidentButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReportIncidentActivity.class);
            startActivity(intent);
        });

        ImageButton emergencyButton = findViewById(R.id.emergencyButton);
        emergencyButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SilentEmergencyActivity.class);
            startActivity(intent);
        });

        // Add this code for the medIDProfileButton
        ImageButton medIDProfileButton = findViewById(R.id.medIDProfileButton);
        medIDProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.userProfile:
                Intent userProfileIntent = new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(userProfileIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
