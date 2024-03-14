package com.dflorez.assignment2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dflorez.assignment2.R;
import com.dflorez.assignment2.ViewModel.GameViewModel;
import com.dflorez.assignment2.databinding.ActivityPlayBinding; // Data Binding (Add this after updating Gradle File)

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlayActivity extends AppCompatActivity {

    // View Binding (Add this after updating Gradle File)
    private ActivityPlayBinding binding;

    // ViewModel
    GameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // View Binding
        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Instantiate the ViewModel with a reference to the ViewModel Class
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);

        // Retrieve player names from the Intent && sets them into the ViewModel
        String[] playerNames = getIntent().getStringArrayExtra("PLAYER_NAMES");
        viewModel.setPlayerNames(playerNames);

        // Observe Player Names from ViewModel and update UI
        viewModel.getPlayerNames().observe(this, names -> {
            Log.i("tag", "From ViewModel: Player1 -> ");
            Log.i("tag", Arrays.toString(names));
            binding.txtPlayer1.setText(names[0]);
            binding.txtPlayer2.setText(names[1]);
        });




        binding.tileA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImage2();
            }
        });



    }

    ImageView a1;


    public void clickImage2() {
        Log.i("tag", "tileA1 clicked");
        Toast.makeText(this, "Image clicked", Toast.LENGTH_LONG).show();



       a1 =  binding.tileA1;
        a1.setImageResource(R.drawable.x);
        // a1.setImageResource(R.drawable.o);
        a1.setScaleX(0);
        a1.setScaleY(0);
        a1.animate().rotation(360).scaleX(1).scaleY(1).setDuration(500);
    }

}