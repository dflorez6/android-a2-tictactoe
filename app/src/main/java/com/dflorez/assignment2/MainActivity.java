package com.dflorez.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dflorez.assignment2.databinding.ActivityMainBinding; // Data Binding (Add this after updating Gradle File)

public class MainActivity extends AppCompatActivity {

    // View Binding (Add this after updating Gradle File)
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}