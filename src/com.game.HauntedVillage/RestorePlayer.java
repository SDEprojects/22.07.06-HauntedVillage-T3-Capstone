package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RestorePlayer {

    public RestorePlayer() throws IOException {
    }

    public ArrayList restorePlayer() throws IOException {
        ArrayList<ArrayList<String>> playerInfoList = new ArrayList<>();
        ArrayList<String> playerLocation = new ArrayList<>();
        ArrayList<String> playerHealthLevel = new ArrayList<>();
        Player player = new Player();
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