package com.dflorez.assignment2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dflorez.assignment2.Model.Game;
import com.dflorez.assignment2.R;
import com.dflorez.assignment2.ViewModel.GameViewModel;
import com.dflorez.assignment2.databinding.ActivityPlayBinding; // Data Binding (Add this after updating Gradle File)

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayActivity extends AppCompatActivity {

    // View Binding (Add this after updating Gradle File)
    private ActivityPlayBinding binding;

    // ViewModel
    GameViewModel viewModel;

    // Global Variables
    private List<ImageView> gameTiles = new ArrayList<>();
    private static final int PLAYER_ONE = 1;
    private static final int PLAYER_TWO = 2;

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

        // Add ImageViews to gameTiles List
        gameTiles.add(binding.tileA1);
        gameTiles.add(binding.tileA2);
        gameTiles.add(binding.tileA3);
        gameTiles.add(binding.tileB1);
        gameTiles.add(binding.tileB2);
        gameTiles.add(binding.tileB3);
        gameTiles.add(binding.tileC1);
        gameTiles.add(binding.tileC2);
        gameTiles.add(binding.tileC3);

        // ImageViews Event Listener
        for (ImageView gameTile : gameTiles) {
            gameTile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Variables
                    String gameTileIdName = getResources().getResourceEntryName(gameTile.getId());

                    // Update Image based on player's turn
                    int currentPlayer = viewModel.getCurrentPlayer();
                    if (currentPlayer == PLAYER_ONE) {
                        gameTile.setImageResource(R.drawable.x);
                        animateTile(gameTile);
                    } else if (currentPlayer == PLAYER_TWO) {
                        gameTile.setImageResource(R.drawable.o);
                        animateTile(gameTile);
                    }

                    // Update GameBoard (passing data to the ViewModel)
                    viewModel.updateGameData(gameTileIdName);

                    // Disable clicks after a tile (ImageView) has been clicked
                    gameTile.setClickable(false);
                    gameTile.setFocusable(false);
                }
            });
        }

        // Observe changes in the Game instance
        viewModel.getGameData().observe(this, game -> {
            // If gameFinished disable all ImageViews from being clicked
            if(game.getGameFinished()) {
                // Disable all ImageViews
                for (ImageView gameTile : gameTiles) {
                    gameTile.setClickable(false);
                    gameTile.setFocusable(false);
                }

                // Show Message
                binding.txtResult.setText(viewModel.getGameOverMessage());

                // Show Hidden Widgets
                binding.txtResult.setVisibility(View.VISIBLE);
                binding.btnPlayAgain.setVisibility(View.VISIBLE);
                binding.btnQuit.setVisibility(View.VISIBLE);
            }

        });

        // Button Handlers
        binding.btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish PlayActivity and go back to MainActivity and reset player name inputs
                Intent intentObj = new Intent();
                setResult(Activity.RESULT_CANCELED, intentObj);
                finish();
            }
        });

        binding.btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset GameBoard
                viewModel.resetGame();
                // Hide widgets again
                binding.txtResult.setVisibility(View.INVISIBLE);
                binding.btnPlayAgain.setVisibility(View.INVISIBLE);
                binding.btnQuit.setVisibility(View.INVISIBLE);
                // Reset gameTiles
                for (ImageView gameTile : gameTiles) {
                    gameTile.setImageResource(android.R.color.transparent); // Removes image
                    gameTile.setClickable(true);
                    gameTile.setFocusable(true);
                }
            }
        });


    }

    //==========
    // Auxiliary Methods
    //==========
    // Method that animates the passed gameTile (when clicked)
    public void animateTile(ImageView gameTile) {
        gameTile.setScaleX(0);
        gameTile.setScaleY(0);
        gameTile.animate().rotation(360).scaleX(1).scaleY(1).setDuration(500);
    }

}