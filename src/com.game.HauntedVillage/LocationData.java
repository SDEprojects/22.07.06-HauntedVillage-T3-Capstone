package com.game.HauntedVillage;

import java.util.Map;

public class LocationData {
    String name;
    String description;
    String description2;
    String[] actions;
    String[] items;
    java.util.Map<String, String> directions;
    String[] characters;
    String jpeg;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDescription2() {
        return description2;
    }

    public String[] getActions() {
        return actions;
    }

    public String[] getItems() {
        return items;
    }

    public Map<String, String> getDirections() {
        return directions;
    }

    public String[] getCharacters() {
        return characters;
    }

    public String getJpeg() {
        return jpeg;
    }
}
