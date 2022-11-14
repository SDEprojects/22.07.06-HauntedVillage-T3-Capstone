package com.game.hauntedvillage.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RestorePlayer {

    public RestorePlayer() throws IOException {
    }

    public ArrayList restorePlayer() throws IOException, UnsupportedAudioFileException {
        ArrayList<ArrayList<String>> playerInfoList = new ArrayList<>();
        ArrayList<String> playerLocation = new ArrayList<>();
        ArrayList<String> playerHealthLevel = new ArrayList<>();
        com.game.hauntedvillage.model.Player player = new com.game.hauntedvillage.model.Player();
        String filename = "savePlayer.json";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(filename);
            player = objectMapper.readValue(file, Player.class);
            System.out.println("---> Game restored...\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        playerLocation.add(player.getLocation());
        playerInfoList.add(playerLocation);

        playerInfoList.add(player.getInventory());

        playerHealthLevel.add(String.valueOf(player.getHealthLevel()));
        playerInfoList.add(playerHealthLevel);

        playerInfoList.add(player.getNpcInventory());

        return playerInfoList;
    }
}