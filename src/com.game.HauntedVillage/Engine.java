package com.game.HauntedVillage;

import com.apps.util.Console;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Engine {

    // initialize scanner. takes system input
    Scanner scanner = new Scanner(System.in);
    private ArrayList<String> verbNoun = new ArrayList<>(List.of("verb", "noun"));
    private String npcResponse;
    static Player player = new Player();
    private boolean wellActivation = false;
    private boolean endGame = false;
    private Splash splash = new Splash();
    private Menu menu = new Menu();

    public Engine() {
    }

    public static void restoreGame() {
        ArrayList<ArrayList<String>> playerInfoList = new ArrayList<>();
        playerInfoList = RestorePlayer.restorePlayer();
        String location = playerInfoList.get(0).get(0);
        ArrayList<String> inventory = playerInfoList.get(1);
        int healthLevel = parseInt(playerInfoList.get(2).get(0));
        ArrayList<String> deadNPCs = playerInfoList.get(1);
        player.setLocation(location);
        player.setInventory(inventory);
        player.setHealthLevel(healthLevel);
        player.setNpcInventory(deadNPCs);
    }

    public static void saveGame() {
        SavePlayer.savePlayer(player.getLocation(), player.getInventory(), player.getHealthLevel(), player.getNpcInventory());
    }

    public void execute() throws IOException {
        Console.clear();
        splash.splashScreen();
        menu.startNewGame();
        Sound.runMusic();
        Console.clear();
        presentInfo();
        Console.clear();
        gameLoop();
    }

    //game loop
    public void gameLoop() throws IOException {

        String oldLocation = "";
        //game continues if player is alive
        while (!endGame) {

            //returns player information at top of screen
            player.playerCurrentInfo();

            //returns location description and player prompt
            if(!oldLocation.equals(player.getLocation())) {
                player.prompt();
                oldLocation = player.getLocation();
            }

            //takes user input, specific to players location
            userPromptInput(player.getLocation());

            //go command, player moves to given direction
            if ("go".equals(getVerbNoun().get(0))) {
                if(player.areaDirections().contains(getVerbNoun().get(1))) {
                    //finds new location given cardinal direction
                    String newLocation = Map.moveFinder(player.getLocation(), getVerbNoun().get(1));
                    //if new location is not blank the location is updated
                    if (!Objects.equals(newLocation, "")) {
                        player.setLocation(newLocation);
                    }
                }
                else {
                    System.out.printf("Can not %s %s in %s.%n Provide valid input.%n", getVerbNoun().get(0).toUpperCase(), getVerbNoun().get(1).toUpperCase(), player.getLocation().toUpperCase());
                }
            }

            //found items retrieves locations item list
            if("take".equals(getVerbNoun().get(0))) {
                ArrayList<String> items = player.foundItems();
                String noun = getVerbNoun().get(1);
                if (items.contains(noun)) {
                    //take command, player adds item to inventory
                    for (String item : items) {
                        if (item.equals(noun)) {
                            Sound.runFX();
                            player.addInventory(noun);
                        }
                    }
                }
                else {
                    System.out.printf("There is no %s here to take.%n Try again.%n", noun);
                }
            }

            //search command, player looks for items
            if ("search".equals(getVerbNoun().get(0))) {

                if("Well".equals(player.getLocation())){
                    System.out.println("There is a triangular indentation in the stone.");
                    if(player.getInventory().contains("amulet")){
                        setWellActivation(true);
                        System.out.println("You insert the triangular amulet. The ground rumbles and a grown comes from within the well.");
                    }
                    else {
                        System.out.println("Something must fit here.");
                    }
                }
                else {
                    System.out.println("There is nothing to search in this area.");
                }
            }

            //speak command, player speaks to NPCs
            if ("speak".equals(getVerbNoun().get(0))) {

                String character = getVerbNoun().get(1);
                if (NPC.npcLocation(player.getLocation(), character) && !player.getNpcInventory().contains(character)) {
                    System.out.println(NPC.npcConversation(character));
                }
                else {
                    System.out.printf("There is no %s here... Are you seeing ghosts?%n", character);
                }
            }

            //light command, player lights candle for amulet
            if ("light".equals(getVerbNoun().get(0))) {
                Art.showArt("candle");
                System.out.println("The illumination reveals a triangular amulet, this may come in handy.  (amulet added to inventory)");
                player.addInventory("amulet");
            }


            // use command, used to interact with static location items (ex. well)
            if ("use".equals(getVerbNoun().get(0))) {
                String interactionItem = getVerbNoun().get(1);
                if (Item.checkStationaryItemLocation(player.getLocation(), interactionItem)) {
                    if(amuletWellCondition(interactionItem)){
                        System.out.println("You retrieve a dark blue stone");
                        player.addInventory("stone");
                    }
                    else {

                        ArrayList<ArrayList<String>> result;
                        result = Item.stationaryItems(interactionItem);
                        String useDesc = result.get(0).get(2);
                        System.out.println(useDesc);

                        // if item heals
                        int healthDelta = parseInt(result.get(0).get(4));
                        if ((player.getHealthLevel() + healthDelta > 10)) {
                            player.setHealthLevel(10);
                        }
                        else {
                            player.setHealthLevel(player.getHealthLevel() + healthDelta);
                        }
                    }
                }
            }

            //use drop command, player can drop inventory.
            if ("drop".equals(getVerbNoun().get(0))) {
                String interactionItem = getVerbNoun().get(1);
                if (player.getInventory().contains(interactionItem)) {
                    player.removeItem(interactionItem);
                }
                else{
                    System.out.println(interactionItem + " is not in your inventory. ");
                }
            }

            //fight command.
            if ("fight".equals(getVerbNoun().get(0))) {
                String target = getVerbNoun().get(1);
                if(player.seenNPCs().contains(target)) {
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Choose a weapon from your inventory.");
                    String weapon = scanner1.nextLine().trim().toLowerCase();
                    if (player.getInventory().contains(weapon)) {
                        if ("Woods".equals(player.getLocation())){
                            if ("stone".equals(weapon)){
                                finalBattle();
                            }
                            else{
                                //NPC.demonDamage();
                                System.out.println("This weapon isn’t doing anything");
                            }
                        }
                        else {
                            player.addNpcInventory(target);
                            System.out.printf("You killed %s with the %s.%n", target, weapon);
                        }
                    }
                    else{
                        System.out.println(weapon + " is not in your inventory. ");
                    }
                }
                else {
                    System.out.printf("There is no %s here... You must be seeing ghosts.%n", target);
                }
            }


            //clears console before update
            Console.clear();

            //if player is dead, end game
            if (Player.end() == true) {
                endGame = true;
            }
        }
    }

    private void finalBattle() {
        Art.showArt("demon");
        System.out.println("You throw the blue stone at the beast. \nIt locks into space in the flame and radiates in bright blue light! \n\n“No!!!”, he roars");
        Console.clear();
        System.out.println("The demon is destroyed in a burst of white light. \n\nYou can finally rest now that the curse has lifted.");
        setEndGame(true);
    }

    private boolean amuletWellCondition(String item) {
        boolean condition = false;
        if("well".equals(item)){
            if(player.getInventory().contains("amulet")){
                if (wellActivation) {
                    if (!player.getInventory().contains("stone")) {
                        condition = true;
                    }
                }
            }
        }
        return condition;
    }

    //checks if action is allowed for given location
    private boolean actionChecker(String location, String inputAction) {
        boolean result = false;
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode rootArray = mapper.readTree(new File("resources/location.json"));
            //Always-allowed actions are hard coded
            ArrayList<String> actionsList = new ArrayList<>(List.of("help", "quit", "look", "restore", "save", "drop", "map"));
            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(location);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode) {
                        // Get node names
                        JsonNode actionsNode = nameNode.path("actions");

                        if (actionsNode.equals(node)) {
                            for (JsonNode item : actionsNode) {
                                actionsList.add(item.asText());
                            }
                        }
                    }
                }
            }
            for (String action : actionsList) {
                if (inputAction.equals(action)) {
                    result = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //user input processor, sets verbNoun attribute array
    private void userPromptInput(String location) {
        boolean validInput = false;
        while (!validInput) {
            String userInput = scanner.nextLine().trim().toLowerCase();
            TextParser parser = new TextParser();
            //verb-noun pair array using text parser
            ArrayList<String> result = parser.textParser(userInput);
            String userVerb = result.get(0);
            String userNoun = "";
            if(result.size() > 1) {
                userNoun = result.get(1);
            }

            //checks verbs and nouns for validity
            if (!"verb".equals(userVerb)) {
                if (actionChecker(location, userVerb)) {
                    validInput = true;
                    //sends to event handler if a global command
                    EventHandler.eventHandler(userInput, location);
                    setVerbNoun(result);
                }
                else {
                    System.out.printf("Can not %s %s in %s.%n Provide valid input.%n", userVerb.toUpperCase(), userNoun.toUpperCase(), location.toUpperCase());
                }
            }
            else {
                System.out.println("Please enter a valid command");
            }
        }
    }

    //prints game background information before game
    private void presentInfo() {
        Art.showArt("house");
        try (JsonParser jParser = new JsonFactory()
                .createParser(new File("resources/info.json"))) {

            // loop until token equal to "}"
            while (jParser.nextToken() != JsonToken.END_OBJECT) {

                String fieldname = jParser.getCurrentName();

                if ("story".equals(fieldname)) {
                    jParser.nextToken();
                    System.out.println(jParser.getText());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void splashScreen() {
//        try {
//            // instantiate mapper object
//            ObjectMapper mapper = new ObjectMapper();
//
//            // convert array to list of items
//            List<Splash> splash = List.of(mapper.readValue(Paths.get("resources/splash.json").toFile(), Splash.class));
//
//            // print
//            System.out.println(splash.get(0).getTitle());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public ArrayList<String> getVerbNoun() {
        return verbNoun;
    }

    public void setVerbNoun(ArrayList<String> verbNoun) {
        this.verbNoun = verbNoun;
    }

    public String getNpcResponse() {
        return npcResponse;
    }

    public void setNpcResponse(String npcResponse) {
        this.npcResponse = npcResponse;
    }

    public boolean isWellActivation() {
        return wellActivation;
    }

    public void setWellActivation(boolean wellActivation) {
        this.wellActivation = wellActivation;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
}
