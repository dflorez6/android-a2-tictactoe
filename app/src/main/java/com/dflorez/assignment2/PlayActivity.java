package com.dflorez.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dflorez.assignment2.databinding.ActivityPlayBinding; // Data Binding (Add this after updating Gradle File)

public class PlayActivity extends AppCompatActivity {

    // View Binding (Add this after updating Gradle File)
    private ActivityPlayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // View Binding
        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}