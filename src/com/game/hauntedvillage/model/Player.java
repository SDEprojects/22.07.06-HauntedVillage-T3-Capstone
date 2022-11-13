package com.game.hauntedvillage.model;

import com.apps.util.Console;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.lang.Integer.parseInt;

public class Player implements Serializable {
    private String location = "Home";
    private ArrayList<String> inventory = new ArrayList<>(0);
    private ArrayList<String> npcInventory = new ArrayList<>(0);
    private int healthLevel = 10;
    private String locationFile = "resources/location.json";
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode locationRootArray = mapper.readTree(new File(locationFile));
    private String infoFile = "resources/info.json";
    public  Sound sound = new Sound();
    private boolean wellActivation = false;
    private boolean endGame;
    private com.game.hauntedvillage.model.NPC npc = new com.game.hauntedvillage.model.NPC();
    private HelpMenu helpMenu = new HelpMenu();
    private com.game.hauntedvillage.model.Item item = new com.game.hauntedvillage.model.Item();
    private SavePlayer saveGame = new SavePlayer();
    private RestorePlayer restorePlayer = new RestorePlayer();
    private com.game.hauntedvillage.model.Map map = new com.game.hauntedvillage.model.Map();
    private MapImage mapImage = new MapImage();
    private Art art = new Art();
    public String backgroundImages = "images/cabinedited.jpg";

    public Player() throws IOException, UnsupportedAudioFileException {
    }

    public Player(String location, ArrayList<String> inventory, int healthLevel, ArrayList<String> characters, String backgroundImages) throws IOException, UnsupportedAudioFileException {
        this.location = location;
        this.inventory = inventory;
        this.healthLevel = healthLevel;
        this.npcInventory = characters;
        this.backgroundImages = backgroundImages;
    }

//    private void healthModifier(String item) {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            JsonNode rootArray = objectMapper.readTree(new File("resources/items.json"));
//
//            for (JsonNode root : rootArray) {
//                JsonNode nameNode = root.path(item);
//
//                if (!nameNode.isMissingNode()) {
//                    for (JsonNode node : nameNode) {
//                        JsonNode healthReductionNode = nameNode.path("health_reduction");
//                        int healthReduction = healthReductionNode.asInt();
//                        if (healthReductionNode.equals(node)) {
//                            System.out.println("\n\n");
//                            System.out.println(healthReduction);
//
//                        }
//                    }
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    //prints game background information before game
    public List<String> showGameIntro() {
        List<String> introList = new ArrayList<>();
        sound.runMusic();
        introList.add(art.showArt("house"));
        try (JsonParser jParser = new JsonFactory()
                .createParser(new File(infoFile))) {

            // loop until token equal to "}"
            while (jParser.nextToken() != JsonToken.END_OBJECT) {

                String fieldName = jParser.getCurrentName();

                if ("story".equals(fieldName)) {
                    jParser.nextToken();
                    introList.add(jParser.getText());
//                    System.out.println(jParser.getText());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return introList;
    }

    public List<String> updatePlayer(ArrayList<String> verbNoun) throws IOException, UnsupportedAudioFileException {
        List<String> printThis = new ArrayList<>();
        //go command, player moves to given direction
        if ("go".equals(verbNoun.get(0))) {
            if(areaDirections().contains(verbNoun.get(1))) {
                //finds new location given cardinal direction
                String newLocation = map.moveFinder(getLocation(), verbNoun.get(1));
                //if new location is not blank the location is updated
                if (!Objects.equals(newLocation, "")) {
                    setLocation(newLocation);
                }
            }
            else {
                printThis.add(String.format("Can not %s %s in %s.%n", verbNoun.get(0).toUpperCase(), verbNoun.get(1).toUpperCase(), getLocation().toUpperCase()));
            }
        }
        //found items retrieves locations item list
        if("take".equals(verbNoun.get(0))) {
            Map<String, String> items = foundItems();
            String noun = verbNoun.get(1);
            for(String item : items.values()) {
                if(item.equals(noun)) {
                    sound.runFX();
                    addInventory(noun);
                }
            }
        }
        if ("search".equals(verbNoun.get(0))) {

            if("Well".equals(getLocation())){
                printThis.add("You see a small hole that looks as if something was removed from it.");
                printThis.add("'... For those who are worthy of obtaining the sacred AMULET, place it here, and the power to vanquish shadow and flame shall be yours'.");
                if(getInventory().contains("images/amuletBackg.png")){
                    setWellActivation(true);
                    printThis.add("Try USEing the well now");
                }
            }
            else {
                printThis.add("There is nothing to search in this area.");
            }
        }
        // use command, used to interact with static location items (ex. well)
        if ("use".equals(verbNoun.get(0))) {
            String interactionItem = verbNoun.get(1);
            if (item.checkStationaryItemLocation(getLocation(), interactionItem)) {
                if(amuletWellCondition(interactionItem)){
                    printThis.add("You insert the amulet into the hole. After a moment of silence a dark blue stone shoots out of the depths of the well and lands next to your feet. \nYou retrieve a dark blue stone and put it in your bag.");
                    addInventory("images/blueStoneBack.png");
                }
                else {

                    ArrayList<ArrayList<String>> result;
                    result = item.stationaryItems(interactionItem);
                    String useDesc = result.get(0).get(2);
                    printThis.add(useDesc);

                    // if item heals
                    int healthDelta = parseInt(result.get(0).get(4));
                    if ((getHealthLevel() + healthDelta > 10)) {
                        setHealthLevel(10);
                    }
                    else {
                        setHealthLevel(getHealthLevel() + healthDelta);
                    }
                }
            }
        }
        if ("speak".equals(verbNoun.get(0))) {

            String character = verbNoun.get(1);
            if (npc.npcLocation(getLocation(), character) && !getNpcInventory().contains(character)) {
                printThis.add(String.format(npc.npcConversation(character)));
            }
            else {
                printThis.add(String.format("There is no %s here... Are you seeing ghosts?%n", character));
            }
        }

        //light command, player lights candle for amulet
        if ("light".equals(verbNoun.get(0))) {
            if(!getInventory().contains("images/amuletBackg.png") && getInventory().contains("images/matches.jpg")) {
                //            printThis.add(art.showArt("candle"));
                printThis.add("You used the matches to light the candle.\nThe illumination reveals a triangular amulet that may come in handy. \nAn amulet has been added to your inventory.");
                addInventory("images/amuletBackg.png");
            }
            else if(getInventory().contains("images/amuletBackg.png")){
                printThis.add("You already lit the candle. You can't light it again...");
            }
            else {
                printThis.add("This would light if only you had something...");
            }
        }

        if ("drop".equals(verbNoun.get(0))) {
            String interactionItem = verbNoun.get(1);
            if (getInventory().contains(interactionItem)) {
                removeItem(interactionItem);
            }
            else{
                printThis.add(String.format("%s is not in your inventory.", interactionItem));
            }
        }
        //fight command.
        if ("fight".equals(verbNoun.get(0))) {
            String target = verbNoun.get(1);
            if(getInventory().contains("images/knife.jpg") || getInventory().contains("images/blueStoneBack.png")) {
                if ("Woods".equals(getLocation()) && target.equals("images/catDemon.jpg")){
                    if (getInventory().contains("images/blueStoneBack.png")){
                        addNpcInventory(target);
                        printThis = finalBattle();
                    }
                    else{
                        //NPC.demonDamage();
                        printThis.add("This knife isn’t doing anything");
                    }
                }
                else {
                    addNpcInventory(target);
                    printThis.add("You killed them.\nHeaven have mercy on their soul.");
                }
            }
            else {
                printThis.add("You need a weapon...");
            }
        }

        if ("help".equals(verbNoun.get(0))){
            Console.clear();
            printThis = helpMenu.help();
        }

        if ("look".equals(verbNoun.get(0))){
            Console.clear();
            printThis = item.checkForItem(verbNoun.get(1));
        }

        if ("save".equals(verbNoun.get(0))){
            saveGame();
        }

        if ("restore".equals(verbNoun.get(0))){
            restoreGame();
        }

        if ("map".equals(verbNoun.get(0))){
            Console.clear();
            printThis = mapImage.display(getLocation());
        }
        return printThis;
    }

    public String areaDescription() {
        String printThis = "";

        for (JsonNode root : getLocationRootArray()) {
            // Get Name
            JsonNode nameNode = root.path(getLocation());

            if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                for (JsonNode node : nameNode) {
                    // Get Description
                    JsonNode descriptionNode = nameNode.path("description");
                    JsonNode description2Node = nameNode.path("description2");

                    if (descriptionNode.equals(node)) {
                        if(!"Woods".equals(getLocation()) || ("Woods".equals(getLocation()) && getInventory().contains("images/blueStoneBack.png"))) {
                            printThis = node.asText();
//                            setOldLocation(getLocation());
                        }
                    }
                    else if(description2Node.equals(node)) {
                        if("Woods".equals(getLocation()) && !getInventory().contains("images/blueStoneBack.png")) {
//                            setOldLocation(getLocation());
                            printThis = node.asText();
                        }
                    }
                }
            }
        }

        return printThis;
    }

    private List<String> finalBattle() {
        List<String> finalBattle = new ArrayList<>();
        finalBattle.add("You throw the blue stone at the beast...");
        setEndGame(true);

        return finalBattle;
    }

    public boolean end() {
        boolean endCondition = false;
        return endCondition;
    }

    public String getCurrentLocationJpeg() {
        for (JsonNode root : getLocationRootArray()) {

            String location = getLocation();
            // Get Name
            JsonNode nameNode = root.path(location);

            if (!nameNode.isMissingNode()) {
                return nameNode.path("jpeg").asText();
            }
        }
        return null;
    }


//    public List<String> playerCurrentInfo() {
//        List<String> stats = new ArrayList<>();
//
//        for (JsonNode root : getLocationRootArray()) {
//
//            String location = getLocation();
//            // Get Name
//            JsonNode nameNode = root.path(location);
//
//            if (!nameNode.isMissingNode()) {  // if "name" node is not missing
//
//                for (JsonNode node : nameNode) {
//                    // Get node names
//                    JsonNode locationNode = nameNode.path("name");
//
//                    //location
//                    if (locationNode.equals(node)) {
//                        stats.add(String.format("Location: %s", node.asText()));
//                    }
//                }
//            }
//        }
//        //directions
//        stats.add(String.format("Directions: %s", areaDirections().toString()));
//
//        //characters
//        stats.add(String.format("Characters you see: %s", seenNPCs().toString()));
//
//        //items
//        stats.add(String.format("Items you see: %s", foundItems().toString()));
//
//        //Inventory
//        stats.add(String.format("Inventory: %s", getInventory().toString()));
//
//        //Health bar
//        ArrayList<String> healthIconList = new ArrayList<>(0);
//        for (int i = 0; i < getHealthLevel(); i++) {
//            healthIconList.add("♥");
//        }
//        String healthBar = healthIconList.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "");
//        stats.add(String.format("Health: %s", healthBar));
//        return stats;
//    }

    //returns location specific directions
    private ArrayList<String> areaDirections() {
        ArrayList<String> directionList = new ArrayList<>(0);

        for (JsonNode root : getLocationRootArray()) {
            // Get Name
            JsonNode nameNode = root.path(getLocation());

            if (!nameNode.isMissingNode()) {  // if "name" node is not missing
                for (JsonNode node : nameNode) {
                    // Get node names
                    JsonNode directionsNode = nameNode.path("directions");
                    if (directionsNode.equals(node)) {

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
                    }
                }
            }
        }
        return directionList;
    }

    //returns location specific items
    public Map<String, String> foundItems() {
        Map<String, String> items = new ConcurrentHashMap<>();
        List<String> mapEntry = new ArrayList<>();

        for (JsonNode root : getLocationRootArray()) {
            // Get Name
            JsonNode nameNode = root.path(getLocation());

            if (!nameNode.isMissingNode()) {  // if "name" node is not missing
                for (JsonNode node : nameNode) {
                    // Get node names
                    JsonNode itemsNode = nameNode.path("items");
                    if (itemsNode.equals(node)) {
                        for (int i = 0; i < itemsNode.size(); i++) {
                            items.put(String.format("%s",i), itemsNode.get(i).asText());
                        }
                    }
                }
            }
        }
        for(String item : items.values()) {
            if(getInventory().contains(item)) {
                mapEntry.add(item);
            }
        }
        for(Map.Entry<String, String> entry : items.entrySet()) {
            if(mapEntry.contains(entry.getValue())) {
                items.remove(entry.getKey());
            }
        }
        return items;
    }

    //remove an item from player's inventory
    private void removeItem(String item){
        ArrayList<String> itemToBeRemoved = getInventory();
        itemToBeRemoved.remove(item);
        setInventory(itemToBeRemoved);
    }

    //returns location specific items
    public Map<String, String> seenNPCs() {
        Map<String, String> npcMap = new ConcurrentHashMap<>();
        ArrayList<String> npcList = new ArrayList<>(0);

        for (JsonNode root : getLocationRootArray()) {
            // Get Name
            JsonNode nameNode = root.path(getLocation());

            if (!nameNode.isMissingNode()) {  // if "name" node is not missing
                for (JsonNode node : nameNode) {
                    // Get node names
                    JsonNode charactersNode = nameNode.path("characters");
                    if (charactersNode.equals(node)) {
                        for (int i = 0; i < charactersNode.size(); i++) {
                            npcMap.put(String.format("%s",i), charactersNode.get(i).asText());
                        }
                    }
                }
            }
        }
//        npcList.removeIf(getNpcInventory()::contains);
//        if("Woods".equals(getLocation()) && !getInventory().contains("stone")) {
//            int demonIndex = npcList.indexOf("demon");
//            if(!(demonIndex == -1)) {
//                npcList.remove(demonIndex);
//            }
//        }
//        return npcList;
        for(String character : npcMap.values()) {
            if(!getNpcInventory().contains(character)) {
                npcList.add(character);
            }
        }
        if("Woods".equals(getLocation()) && !getInventory().contains("images/blueStoneBack.png")) {
            int demonIndex = npcList.indexOf("images/catDemon.jpg");
            if(demonIndex != -1) {
                npcList.remove(demonIndex);
            }
        }
        if("Woods".equals(getLocation()) && getInventory().contains("images/blueStoneBack.png")) {
            int catIndex = npcList.indexOf("images/catBack.png");
            if(catIndex != -1) {
                npcList.remove(catIndex);
            }
        }
        for(Map.Entry<String, String> entry : npcMap.entrySet()) {
            if(getNpcInventory().contains(entry.getValue()) || !npcList.contains(entry.getValue())) {
                npcMap.remove(entry.getKey());
            }
        }
        return npcMap;
    }

    private boolean amuletWellCondition(String item) {
        boolean condition = false;
        if("well".equals(item)){
            if(getInventory().contains("images/amuletBackg.png")){
                if (wellActivation) {
                    if (!getInventory().contains("images/blueStoneBack.png")) {
                        condition = true;
                    }
                }
            }
        }
        return condition;
    }

    private void restoreGame() throws IOException, UnsupportedAudioFileException {
        ArrayList<ArrayList<String>> playerInfoList = new ArrayList<>();
        playerInfoList = restorePlayer.restorePlayer();
        String location = playerInfoList.get(0).get(0);
        ArrayList<String> inventory = playerInfoList.get(1);
        int healthLevel = parseInt(playerInfoList.get(2).get(0));
        ArrayList<String> deadNPCs = playerInfoList.get(3);
        setLocation(location);
        setInventory(inventory);
        setHealthLevel(healthLevel);
        setNpcInventory(deadNPCs);
    }

    private void saveGame() throws IOException {
        saveGame.savePlayer(getLocation(), getInventory(), getHealthLevel(), getNpcInventory());
    }

    private void setWellActivation(boolean wellActivation) {
        this.wellActivation = wellActivation;
    }

    public String getBackgroundImages() {
        return backgroundImages;
    }

    public void setBackgroundImages(String backgroundImages) {
        this.backgroundImages = backgroundImages;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    private void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public String getLocation() {
        return location;
    }

    private void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    private void addInventory(String item) {
        inventory.add(item);
    }

    private void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    public ArrayList<String> getNpcInventory() {
        return npcInventory;
    }

    private void setNpcInventory(ArrayList<String> npcInventory) {
        this.npcInventory = npcInventory;
    }

    private void addNpcInventory(String npc) {
        npcInventory.add(npc);
    }

    private JsonNode getLocationRootArray() {
        return locationRootArray;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
}