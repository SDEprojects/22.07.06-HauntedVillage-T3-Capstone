package com.game.hauntedvillage.model;

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
    public String splashScreen() {
        String splashText = "";
        try {
            // instantiate mapper object
            ObjectMapper mapper = new ObjectMapper();

            JsonNode rootArray = mapper.readTree(new File(splashFilePath));
            for(JsonNode root : rootArray) {
                splashText = root.asText();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return splashText;
    }
}