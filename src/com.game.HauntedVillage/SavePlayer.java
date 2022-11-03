package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.util.ArrayList;


public class SavePlayer {

    public static void savePlayer(String location, ArrayList inventory, int healthLevel, ArrayList<String> deadNPCs) {

        Player player = new Player(location, inventory, healthLevel, deadNPCs);
        String filename = "savePlayer.json";

        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectMapper mapper = new ObjectMapper();

            String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(player);
            fos.write(jsonStr.getBytes());
            fos.close();
            System.out.println("Game saved...\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
