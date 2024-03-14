package com.dflorez.assignment2.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dflorez.assignment2.Model.Game;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GameViewModel extends ViewModel {

    //==========
    // LiveData
    //==========
    private MutableLiveData<String[]> PlayerNames; //  = new MutableLiveData<>()
    private MutableLiveData<Game> GameData;

    //==========
    // Getter/Setters
    //==========
    // Getter
    public LiveData<String[]> getPlayerNames() {
        return PlayerNames;
    }

    // TODO: Pending for implementation
    public LiveData<Game> getGameData() {
        return GameData;
    }

    // Setter
    public void setPlayerNames(String[] playerNames) {
        PlayerNames.setValue(playerNames);
    }

    // TODO: Pending for implementation
    public LiveData<Game> setGameData() {
        return GameData;
    }

    //==========
    // Constructor
    //==========
    public GameViewModel() {
        PlayerNames = new MutableLiveData<>();
        GameData = new MutableLiveData<>();
    }


}
