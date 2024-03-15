package com.dflorez.assignment2.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

        // Reset inputs
        resetInputs();

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

                // Start PlayActivity
                playGame(player1, player2);
            }
        });

    }

    //==========
    // Callbacks
    //==========
    // Resets player name inputs once PlayActivity is finished
    ActivityResultLauncher<Intent> playActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {
                    // Extract Intent Object Extras from PlayActivity
                    int results = activityResult.getResultCode();

                    Log.i("tag", "Results: " + results);

                    // Checks status code
                    if (results == RESULT_CANCELED) {
                        resetInputs();
                    }

                }

            }
    );

    //==========
    // Methods
    //==========
    // Intent to start game
    public void playGame(String player1, String player2) {
        Intent intentObj = new Intent(this, PlayActivity.class);
        intentObj.putExtra("PLAYER_NAMES", new String[] {player1, player2});
        // startActivity(intentObj);
        playActivityLauncher.launch(intentObj);
    }

    // Reset player name inputs
    public void resetInputs() {
        binding.inputPlayer1.setText("");
        binding.inputPlayer2.setText("");
    }

}