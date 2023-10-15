package com.example.rescue;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HealthHistoryActivity extends AppCompatActivity {

    private EditText editMedicalConditions;
    private EditText editMedications;
    private EditText editDisabilities;
    private Button btnSaveHealthHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_history);

        editMedicalConditions = findViewById(R.id.editMedicalConditions);
        editMedications = findViewById(R.id.editMedications);
        editDisabilities = findViewById(R.id.editDisabilities);
        btnSaveHealthHistory = findViewById(R.id.btnSaveHealthHistory);

        btnSaveHealthHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the user input to a database or shared preferences (implementation depends on requirements)

                Toast.makeText(HealthHistoryActivity.this, "Health history saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
