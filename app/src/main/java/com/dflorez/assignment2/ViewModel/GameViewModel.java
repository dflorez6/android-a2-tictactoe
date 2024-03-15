package com.dflorez.assignment2.ViewModel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dflorez.assignment2.Model.Game;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

public class GameViewModel extends ViewModel {

    //==========
    // Global Variables
    //==========
    private static final int PLAYER_ONE = 1;
    private static final int PLAYER_TWO = 2;

    //==========
    // LiveData
    //==========
    private MutableLiveData<String[]> PlayerNames;
    private MutableLiveData<Game> GameData;
    private Game game = new Game();

    //==========
    // Getter/Setters
    //==========
    // Getter
    public LiveData<String[]> getPlayerNames() {
        return PlayerNames;
    }

    public LiveData<Game> getGameData() {
        return GameData;
    }

    public int getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public boolean getGameFinished() {
        return game.getGameFinished();
    }

    public String getGameOverMessage() {
        return game.getGameOverMessage();
    }

    // Setter
    public void setPlayerNames(String[] playerNames) {
        PlayerNames.setValue(playerNames);
    }

    public void setGameData(Game gameData) {
        GameData.setValue(gameData);
    }

    public void updateGameData(String tileId) {
        LiveData<Game> gameLiveData = getGameData();

        // Player 1 starts playing
        if (game.getCurrentPlayer() == PLAYER_ONE) {
            // Method to update GameBoard
            updateGameBoard(tileId, (HashMap<String, Integer>) game.getGameBoard(), game.getCurrentPlayer());

            // Method to determine if there is a winner
            determineVictory((HashMap<String, Integer>) game.getGameBoard(), game.getCurrentPlayer());

            // Switch turns
            game.setCurrentPlayer(PLAYER_TWO);
        } else if(game.getCurrentPlayer() == PLAYER_TWO) {
            // Method to update GameBoard
            updateGameBoard(tileId, (HashMap<String, Integer>) game.getGameBoard(), game.getCurrentPlayer());

            // Method to determine if there is a winner
            determineVictory((HashMap<String, Integer>) game.getGameBoard(), game.getCurrentPlayer());

            // Switch turns
            game.setCurrentPlayer(PLAYER_ONE);
        }

        // Update GameData (LiveData)
        setGameData(game);
    }

    //==========
    // Constructor
    //==========
    public GameViewModel() {
        PlayerNames = new MutableLiveData<>();
        GameData = new MutableLiveData<>();
        GameData.setValue(game); // Initialize GameData with a Game instance (empty game board)
    }

    //====================
    // Methods
    //====================
    // UpdateGameBoard()
    public static void updateGameBoard(String tileId, HashMap<String, Integer> gameBoard, int currentPlayer) {
        // Updates gameBoard values depending on the gameCell clicked
        gameBoard.put(tileId, currentPlayer);
    }

    public void determineVictory(HashMap<String, Integer> gameBoard, int currentPlayer) {
        {
            // Player Names
            String[] playerNames = PlayerNames.getValue();
            String currentPlayerName = currentPlayer == PLAYER_ONE ? playerNames[0] : playerNames[1];

            // Determine Victory - Tie - Continue Playing
            if (
                // Horizontal possible victories
                (gameBoard.get("tileA1") == currentPlayer && gameBoard.get("tileA2") == currentPlayer && gameBoard.get("tileA3") == currentPlayer) ||
                (gameBoard.get("tileB1") == currentPlayer && gameBoard.get("tileB2") == currentPlayer && gameBoard.get("tileB3") == currentPlayer) ||
                (gameBoard.get("tileC1") == currentPlayer && gameBoard.get("tileC2") == currentPlayer && gameBoard.get("tileC3") == currentPlayer) ||
                // Vertical possible victories
                (gameBoard.get("tileA1") == currentPlayer && gameBoard.get("tileB1") == currentPlayer && gameBoard.get("tileC1") == currentPlayer) ||
                (gameBoard.get("tileA2") == currentPlayer && gameBoard.get("tileB2") == currentPlayer && gameBoard.get("tileC2") == currentPlayer) ||
                (gameBoard.get("tileA3") == currentPlayer && gameBoard.get("tileB3") == currentPlayer && gameBoard.get("tileC3") == currentPlayer) ||
                // Diagonal possible victories
                (gameBoard.get("tileA1") == currentPlayer && gameBoard.get("tileB2") == currentPlayer && gameBoard.get("tileC3") == currentPlayer) ||
                (gameBoard.get("tileA3") == currentPlayer && gameBoard.get("tileB2") == currentPlayer && gameBoard.get("tileC1") == currentPlayer)
            ) {
                game.setGameFinished(true);
                game.setGameOverMessage(currentPlayerName +  " is the winner!");
            } else if (
                // Tie
                (gameBoard.get("tileA1") != 0 && gameBoard.get("tileA2") != 0 && gameBoard.get("tileA3") != 0) &&
                (gameBoard.get("tileB1") != 0 && gameBoard.get("tileB2") != 0 && gameBoard.get("tileB3") != 0) &&
                (gameBoard.get("tileC1") != 0 && gameBoard.get("tileC2") != 0 && gameBoard.get("tileC3") != 0)
            ) {
                game.setGameFinished(true);
                game.setGameOverMessage("No winner. It's a tie!");
            } else {
                // Keep Playing
                game.setGameFinished(false);
            }

        }
    }

    // Reset Game to play again
    public void resetGame() {
        // Reset GameBoard (all values inside the dictionary back to 0)
        HashMap<String, Integer> gameBoard = (HashMap<String, Integer>) game.getGameBoard();
        for (String key : gameBoard.keySet()) {
            gameBoard.put(key, 0);
        }

        // Reset the other values
        game.setGameFinished(false);
        game.setGameOverMessage("");
        game.setCurrentPlayer(PLAYER_ONE);

        // Update GameData (LiveData)
        setGameData(game);
    }

}
