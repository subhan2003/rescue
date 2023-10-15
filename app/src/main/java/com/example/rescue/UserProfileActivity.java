package com.example.rescue;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserProfileActivity extends Activity {

    private static final int SELECT_USER_PHOTO = 1;
    private static final int SELECT_MEDICAL_PDF = 2;
    private static final int SELECT_DOCTOR_NOTES_PDF = 3;

    private ImageView userPhoto;
    private Spinner bloodTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userPhoto = findViewById(R.id.userPhoto);
        bloodTypeSpinner = findViewById(R.id.bloodTypeSpinner);

        // Populating spinner with blood types
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.blood_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodTypeSpinner.setAdapter(adapter);
    }

    public void selectUserPhoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_USER_PHOTO);
    }

    public void selectMedicalConditionsPdf(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, SELECT_MEDICAL_PDF);
    }

    public void selectDoctorsNotesPdf(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, SELECT_DOCTOR_NOTES_PDF);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri selectedUri = data.getData();

            switch (requestCode) {
                case SELECT_USER_PHOTO:
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedUri);
                        userPhoto.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Toast.makeText(this, "Failed to load the image.", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case SELECT_MEDICAL_PDF:
                    handlePDF(selectedUri, "Medical Conditions");
                    break;

                case SELECT_DOCTOR_NOTES_PDF:
                    handlePDF(selectedUri, "Doctor's Notes");
                    break;
            }
        }
    }

    private void handlePDF(Uri pdfUri, String docType) {
        // Sample handling: Display a toast with the path to the selected PDF
        Toast.makeText(this, docType + " PDF selected: " + pdfUri.getPath(), Toast.LENGTH_LONG).show();

        // If you want to do more with the PDF, like uploading it to a server or displaying its content,
        // you'd expand on this method.
    }
    public void saveUserDetails(View view) {
        // Fetching data from EditText and other views

        // Name Entry
        EditText nameEditText = findViewById(R.id.nameEntryEditText);
        String name = nameEditText.getText().toString();

        // Phone Entry
        EditText phoneEditText = findViewById(R.id.phoneEntryEditText);
        String phone = phoneEditText.getText().toString();

        // Address Entry
        EditText addressEditText = findViewById(R.id.addressEntryEditText);
        String address = addressEditText.getText().toString();

        // Language Entry
        EditText languageEditText = findViewById(R.id.languageEntryEditText);
        String language = languageEditText.getText().toString();

        // Blood Type Spinner
        Spinner bloodTypeSpinner = findViewById(R.id.bloodTypeSpinner);
        String bloodType = bloodTypeSpinner.getSelectedItem().toString();


        // Date of Birth Entry
        EditText dobEditText = findViewById(R.id.dobEntryEditText);
        String dateOfBirth = dobEditText.getText().toString();

        // Weight Entry
        EditText weightEditText = findViewById(R.id.weightEntryEditText);
        String weight = weightEditText.getText().toString();

        // Here, you can process or store the above extracted information as per your needs
        // E.g., Saving to a local database, sending to a server, etc.

        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("phone", phone);
        user.put("address", address);
        user.put("language", language);
        user.put("bloodType", bloodType);
        user.put("dateOfBirth", dateOfBirth);
        user.put("weight", weight);

        // Access Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(UserProfileActivity.this, "User details saved successfully!",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserProfileActivity.this, "Error saving user details!",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        Toast.makeText(this, "User details saved!", Toast.LENGTH_SHORT).show();
    }



}