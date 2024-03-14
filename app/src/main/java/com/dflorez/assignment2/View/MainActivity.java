package com.dflorez.assignment2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dflorez.assignment2.Model.Game;
import com.dflorez.assignment2.ViewModel.GameViewModel;
import com.dflorez.assignment2.databinding.ActivityMainBinding; // Data Binding (Add this after updating Gradle File)

public class MainActivity extends AppCompatActivity {

    // View Binding (Add this after updating Gradle File)
    private ActivityMainBinding binding;

    // ViewModel
    GameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Instantiate the ViewModel with a reference to the ViewModel Class
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);

        // Handler
        binding.btnPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tag", "Play Button Clicked");

                // Input Data
                String player1 = binding.inputPlayer1.getText().toString();
                String player2 = binding.inputPlayer2.getText().toString();

                // Set player names in ViewModel
                // TODO: Commented for testing
                // viewModel.setPlayerNames(new String[] {player1, player2});

                // Start PlayActivity
                playGame(player1, player2);

            }
        });



    }

    // Methods
    public void playGame(String player1, String player2) {
        Intent intentObj = new Intent(this, PlayActivity.class);
        intentObj.putExtra("PLAYER_NAMES", new String[] {player1, player2});
        startActivity(intentObj);
    }

}