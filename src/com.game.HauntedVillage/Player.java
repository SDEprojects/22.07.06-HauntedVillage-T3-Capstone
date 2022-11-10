package com.game.HauntedVillage;

import com.apps.util.Console;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
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
    private Sound sound = new Sound();
    private boolean wellActivation = false;
    private boolean endGame;
    private NPC npc = new NPC();
    private HelpMenu helpMenu = new HelpMenu();
    private Item item = new Item();
    private SavePlayer saveGame = new SavePlayer();
    private RestorePlayer restorePlayer = new RestorePlayer();
    private com.game.HauntedVillage.Map map = new com.game.HauntedVillage.Map();
    private MapImage mapImage = new MapImage();
    private Art art = new Art();
    private String oldLocation = "";
    public String backgroundImages = "images/cabinedited.jpg";

    public Player() throws IOException {
    }

    public Player(String location, ArrayList<String> inventory, int healthLevel, ArrayList<String> characters, String backgroundImages) throws IOException {
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

    public List<String> updatePlayer(ArrayList<String> verbNoun) throws IOException {
        List<String> printThis = new ArrayList<>();
        //go command, player moves to given direction
        if ("go".equals(verbNoun.get(0))) {
            if(areaDirections().contains(verbNoun.get(1))) {
                //finds new location given cardinal direction
                String newLocation = map.moveFinder(getLocation(), verbNoun.get(1));
                //if new location is not blank the location is updated
                if (!Objects.equals(newLocation, "")) {
                    setLocation(newLocation);
                    System.out.println(newLocation);
                    getInventory().add("new Stuff");
                }
            }
            else {
                printThis.add(String.format("Can not %s %s in %s.%n Provide valid input.%n", verbNoun.get(0).toUpperCase(), verbNoun.get(1).toUpperCase(), getLocation().toUpperCase()));
            }
        }
        //found items retrieves locations item list
        if("take".equals(verbNoun.get(0))) {
            ArrayList<String> items = foundItems();
            String noun = verbNoun.get(1);
            if (items.contains(noun)) {
                //take command, player adds item to inventory
                for (String item : items) {
                    if (item.equals(noun)) {
                        sound.runFX();
                        addInventory(noun);
                    }
                }
            }
            else {
                printThis.add(String.format("There is no %s here to take.%n Try again.%n", noun));
            }
        }
        if ("search".equals(verbNoun.get(0))) {

            if("Well".equals(getLocation())){
                System.out.println("There is a triangular indentation in the stone.");
                if(getInventory().contains("amulet")){
                    setWellActivation(true);
                    printThis.add("You insert the triangular amulet. The ground rumbles and a grown comes from within the well.");
                }
                else {
                    printThis.add("Something must fit here.");
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
                    printThis.add("You retrieve a dark blue stone");
                    addInventory("stone");
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
            printThis.add(art.showArt("candle"));
            printThis.add("The illumination reveals a triangular amulet, this may come in handy.  (amulet added to inventory)");
            addInventory("amulet");
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
            if(seenNPCs().contains(target)) {
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Choose a weapon from your inventory.");
                String weapon = scanner1.nextLine().trim().toLowerCase();
                if (getInventory().contains(weapon)) {
                    if ("Woods".equals(getLocation())){
                        if ("stone".equals(weapon)){
                            printThis = finalBattle();
                        }
                        else{
                            //NPC.demonDamage();
                            printThis.add("This weapon isn’t doing anything");
                        }
                    }
                    else {
                        addNpcInventory(target);
                        printThis.add(String.format("You killed %s with the %s.%n", target, weapon));
                    }
                }
                else{
                    printThis.add(String.format("%s is not in your inventory.", weapon));
                }
            }
            else {
                printThis.add(String.format("There is no %s here... You must be seeing ghosts.%n", target));
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

        if(!getOldLocation().equals(getLocation())) {
            for (JsonNode root : getLocationRootArray()) {
                // Get Name
                JsonNode nameNode = root.path(getLocation());

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode) {
                        // Get Description
                        JsonNode descriptionNode = nameNode.path("description");
                        JsonNode description2Node = nameNode.path("description2");

                        if (descriptionNode.equals(node)) {
                            if(!"Woods".equals(getLocation()) || ("Woods".equals(getLocation()) && getInventory().contains("stone"))) {
                                printThis = node.asText();
                                setOldLocation(getLocation());
                            }
                        }
                        else if(description2Node.equals(node)) {
                            if("Woods".equals(getLocation()) && !getInventory().contains("stone")) {
                                setOldLocation(getLocation());
                                printThis = node.asText();
                            }
                        }
                    }
                }
            }
        }

        return printThis;
    }

    private List<String> finalBattle() {
        List<String> finalBattle = new ArrayList<>();
        finalBattle.add(art.showArt("demon"));
        finalBattle.add("You throw the blue stone at the beast. \nIt locks into space in the flame and radiates in bright blue light! \n\n“No!!!”, he roars");
        Console.clear();
        finalBattle.add("The demon is destroyed in a burst of white light. \n\nYou can finally rest now that the curse has lifted.");
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


    public List<String> playerCurrentInfo() {
        List<String> stats = new ArrayList<>();

        for (JsonNode root : getLocationRootArray()) {

            String location = getLocation();
            // Get Name
            JsonNode nameNode = root.path(location);

            if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                for (JsonNode node : nameNode) {
                    // Get node names
                    JsonNode locationNode = nameNode.path("name");

                    //location
                    if (locationNode.equals(node)) {
                        stats.add(String.format("Location: %s", node.asText()));
                    }
                }
            }
        }
        //directions
        stats.add(String.format("Directions: %s", areaDirections().toString()));

        //characters
        stats.add(String.format("Characters you see: %s", seenNPCs().toString()));

        //items
        stats.add(String.format("Items you see: %s", foundItems().toString()));

        //Inventory
        stats.add(String.format("Inventory: %s", getInventory().toString()));

        //Health bar
        ArrayList<String> healthIconList = new ArrayList<>(0);
        for (int i = 0; i < getHealthLevel(); i++) {
            healthIconList.add("♥");
        }
        String healthBar = healthIconList.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "");
        stats.add(String.format("Health: %s", healthBar));
        return stats;
    }

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
    private ArrayList<String> foundItems() {
        ArrayList<String> itemsList = new ArrayList<>(0);
        for (JsonNode root : getLocationRootArray()) {
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
        return itemsList;
    }

    //remove an item from player's inventory
    private void removeItem(String item){
        ArrayList<String> itemToBeRemoved = getInventory();
        itemToBeRemoved.remove(item);
        setInventory(itemToBeRemoved);
    }

    //returns location specific items
    private ArrayList<String> seenNPCs() {
        ArrayList<String> npcList = new ArrayList<>(0);

        for (JsonNode root : getLocationRootArray()) {
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
        if("Woods".equals(getLocation()) && !getInventory().contains("stone")) {
            int demonIndex = npcList.indexOf("demon");
            if(!(demonIndex == -1)) {
                npcList.remove(demonIndex);
            }
        }
        return npcList;
    }

    private boolean amuletWellCondition(String item) {
        boolean condition = false;
        if("well".equals(item)){
            if(getInventory().contains("amulet")){
                if (wellActivation) {
                    if (!getInventory().contains("stone")) {
                        condition = true;
                    }
                }
            }
        }
        return condition;
    }

    private void restoreGame() throws IOException {
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

    private String getOldLocation() {
        return oldLocation;
    }

    private void setOldLocation(String oldLocation) {
        this.oldLocation = oldLocation;
    }
}