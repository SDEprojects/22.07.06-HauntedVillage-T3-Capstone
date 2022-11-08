package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

class SavePlayer {

    private String filename = "savePlayer.json";

    void savePlayer(String location, ArrayList inventory, int healthLevel, ArrayList<String> deadNPCs) throws IOException {

        Player player = new Player(location, inventory, healthLevel, deadNPCs);

        try {
            FileOutputStream fos = new FileOutputStream(this.filename);
            ObjectMapper mapper = new ObjectMapper();

            String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(player);
            fos.write(jsonStr.getBytes());
            fos.close();
            System.out.println("Game saved...\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
