package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class Splash {

    // FIELDS
    private static final String splashFilePath = "resources/splash.json";

    // CONSTRUCTORS
    public Splash() {

    }

    // BUSINESS METHODS
    public void splashScreen() {
        try {
            // instantiate mapper object
            ObjectMapper mapper = new ObjectMapper();

            JsonNode rootArray = mapper.readTree(new File(splashFilePath));
            for(JsonNode root : rootArray) {
                System.out.println(root.asText());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}