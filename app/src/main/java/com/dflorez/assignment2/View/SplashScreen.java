package com.dflorez.assignment2.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dflorez.assignment2.R;
import com.dflorez.assignment2.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    // View Binding
    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // View Binding
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Handler + Runnable: Wait 5 seconds before moving to the Main Activity
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentObj = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intentObj);
                finish();
            }
        }, 5000);

    }
}