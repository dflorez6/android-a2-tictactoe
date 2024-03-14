package com.dflorez.assignment2.Model;

import java.util.HashMap;
import java.util.Map;

public class Game {

    //====================
    // Global Variables / Constants
    //====================
    public final int PLAYER_ONE = 1;
    public final int PLAYER_TWO = 2;

    //====================
    // Attributes or properties
    //====================
    // public Dictionary<String, Integer> GameBoard;
    private Map<String, Integer> GameBoard;
    private int CurrentPlayer;
    private boolean GameFinished;

    //====================
    // Setters/Getters
    //====================
    public Map<String, Integer> getGameBoard() {
        return GameBoard;
    }

    public void setGameBoard(Map<String, Integer> gameBoard) {
        GameBoard = gameBoard;
    }

    public int getCurrentPlayer() {
        return CurrentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        CurrentPlayer = currentPlayer;
    }

    public boolean getGameFinished() {
        return GameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        GameFinished = gameFinished;
    }

    //====================
    // Constructors
    //====================
    // Default
    public Game()
    {
        GameBoard = new HashMap<String, Integer>() {{
            put("tileA1", 0);
            put("tileA2", 0);
            put("tileA3", 0);
            put("tileB1", 0);
            put("tileB2", 0);
            put("tileB3", 0);
            put("tileC1", 0);
            put("tileC2", 0);
            put("tileC3", 0);
        }};

        setCurrentPlayer(PLAYER_ONE);
        setGameFinished(false);
    }

    // Non-default
    public Game(HashMap<String, Integer> gameBoard, int currentPlayer, boolean gameFinished)
    {
        GameBoard = gameBoard;
        setCurrentPlayer(currentPlayer);
        setGameFinished(gameFinished);
    }

    //====================
    // Methods
    //====================

}
