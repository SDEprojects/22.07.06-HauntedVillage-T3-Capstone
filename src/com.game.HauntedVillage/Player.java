package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private String location = "Home";
    private ArrayList<String> inventory = new ArrayList<>(0);
    private ArrayList<String> npcInventory = new ArrayList<>(0);
    private int healthLevel = 10;

    public Player() {
    }

    public Player(String location, ArrayList<String> inventory, int healthLevel) {
        this.location = location;
        this.inventory = inventory;
        this.healthLevel = healthLevel;
    }

    public Player(String location, ArrayList<String> inventory, int healthLevel, ArrayList<String> characters) {
        this.location = location;
        this.inventory = inventory;
        this.healthLevel = healthLevel;
        this.npcInventory = characters;
    }

    private static void healthModifier(String item) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootArray = objectMapper.readTree(new File("resources/items.json"));

            for (JsonNode root : rootArray) {
                JsonNode nameNode = root.path(item);

                if (!nameNode.isMissingNode()) {
                    for (JsonNode node : nameNode) {
                        JsonNode healthReductionNode = nameNode.path("health_reduction");
                        int healthReduction = healthReductionNode.asInt();
                        if (healthReductionNode.equals(node)) {
                            System.out.println("\n\n");
                            System.out.println(healthReduction);

                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void prompt() {
        ObjectMapper mapper = new ObjectMapper();

        try {

            JsonNode rootArray = mapper.readTree(new File("resources/location.json"));

            for (JsonNode root : rootArray) {

                String location = getLocation();
                // Get Name
                JsonNode nameNode = root.path(location);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode) {
                        // Get Description
                        JsonNode descriptionNode = nameNode.path("description");

                        if (descriptionNode.equals(node)) {
                            System.out.println("\n\n");
                            System.out.println(node.asText());
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean end() {
        boolean endCondition = false;
        return endCondition;
    }

    void playerCurrentInfo() {
        ObjectMapper mapper = new ObjectMapper();
        List<String> characters = new ArrayList<>();

        try {
            JsonNode rootArray = mapper.readTree(new File("resources/location.json"));

            for (JsonNode root : rootArray) {

                String location = getLocation();
                // Get Name
                JsonNode nameNode = root.path(location);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode) {
                        // Get node names
                        JsonNode locationNode = nameNode.path("name");
                        JsonNode itemsNode = nameNode.path("items");
                        JsonNode directionsNode = nameNode.path("directions");
                        JsonNode npcNode = nameNode.path("characters");

                        //location
                        if (locationNode.equals(node)) {
                            System.out.println("Location: " + node.asText());
                        }

                        //characters
                        if(npcNode.equals(node)) {
//                            for(JsonNode character : npcNode) {
//                                characters.add(character.asText());
//                            }
                            System.out.println("Characters you see: " + seenNPCs());
                        }

                        //items
                        if(itemsNode.equals(node)) {

                            System.out.println("Items you see: " + foundItems());
                        }

                        //direction
                        if (directionsNode.equals(node)) {
                            ArrayList<String> directionList = new ArrayList<>(0);
                            for (JsonNode direction : directionsNode) {
                                JsonNode northNode = node.path("north");
                                JsonNode eastNode = node.path("east");
                                JsonNode southNode = node.path("south");
                                JsonNode westNode = node.path("west");

                                if (northNode.equals(direction)) {
                                    directionList.add("north");
                                }
                                if (eastNode.equals(direction)) {
                                    directionList.add("east");
                                }
                                if (southNode.equals(direction)) {
                                    directionList.add("south");
                                }
                                if (westNode.equals(direction)) {
                                    directionList.add("west");
                                }
                            }
                            System.out.println("Directions: " + directionList);
                        }
                    }
                }
            }
            //Inventory
            System.out.println("Inventory: " + getInventory());

            //Health bar
            ArrayList<String> healthIconList = new ArrayList<>(0);
            for (int i = 0; i < getHealthLevel(); i++) {
                healthIconList.add("♥");
            }
            String healthBar = healthIconList.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "");
            System.out.println("Health: " + healthBar);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //returns location specific items
    ArrayList<String> foundItems() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<String> itemsList = new ArrayList<>(0);

        try {
            JsonNode rootArray = mapper.readTree(new File("resources/location.json"));

            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(getLocation());

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing
                    for (JsonNode node : nameNode) {
                        // Get node names
                        JsonNode itemsNode = nameNode.path("items");
                        if (itemsNode.equals(node)) {
                            for (JsonNode item : itemsNode) {
                                itemsList.add(item.asText());
                            }
                        }
                    }
                }
            }
            itemsList.removeIf(getInventory()::contains);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemsList;
    }

    //remove an item from player's inventory
    public void removeItem(String item){
        ArrayList<String> itemToBeRemoved = getInventory();
        itemToBeRemoved.remove(item);
        setInventory(itemToBeRemoved);
    }

    //returns location specific items
    ArrayList<String> seenNPCs() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<String> npcList = new ArrayList<>(0);

        try {
            JsonNode rootArray = mapper.readTree(new File("resources/location.json"));

            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(getLocation());

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing
                    for (JsonNode node : nameNode) {
                        // Get node names
                        JsonNode itemsNode = nameNode.path("characters");
                        if (itemsNode.equals(node)) {
                            for (JsonNode npc : itemsNode) {
                                npcList.add(npc.asText());
                            }
                        }
                    }
                }
            }
            npcList.removeIf(getNpcInventory()::contains);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return npcList;
    }

    //remove a npc from player's vision
    public void removeNPC(String npc){
        ArrayList<String> npcToBeRemoved = getNpcInventory();
        npcToBeRemoved.remove(npc);
        setInventory(npcToBeRemoved);
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void addInventory(String item) {
        inventory.add(item);
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    public ArrayList<String> getNpcInventory() {
        return npcInventory;
    }

    public void addNpcInventory(String npc) {
        npcInventory.add(npc);
    }

    public void setNpcInventory(ArrayList<String> npcInventory) {
        this.npcInventory = npcInventory;
    }
}