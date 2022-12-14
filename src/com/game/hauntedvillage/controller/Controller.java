package com.game.hauntedvillage.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.hauntedvillage.model.MapImage;
import com.game.hauntedvillage.model.Player;
import com.game.hauntedvillage.model.Sound;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
    private ArrayList<String> verbNoun = new ArrayList<>(List.of("verb", "noun"));
    private com.game.hauntedvillage.model.Player player = new Player();
    private List<String> playerUpdate = new ArrayList<>();
    private MapImage mapImage = new MapImage();
    private Sound sound = new Sound();
    private static Controller instance;

    public static Controller getInstance() throws UnsupportedAudioFileException, IOException {
        if(instance != null) {
            return instance;
        }
        return instance = new Controller();
    }

    public void musicAccessOn() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        sound.musicOn();
    }

    public void musicAccessOff() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        sound.musicOff();
    }

    public void SFXAccessOn() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        sound.SFXOn();
    }

    public void SFXAccessOff() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        sound.SFXOff();
    }

    public Controller() throws IOException, UnsupportedAudioFileException {
    }

    public String showAreaDescription() {
        return player.areaDescription();
    }

    public String playerLocation() {
        return player.getLocation();
    }

    public List<String> gameMap() {
        return mapImage.display(playerLocation());
    }
    public String backgroundJpeg(){
        return player.getCurrentLocationJpeg();
    }

    public List<String> gameInventory() {
        return player.getInventory();
    }

    public Map<String, String> areaItems() {
        return player.foundItems();
    }

    public Map<String, String> areaNPCs() {
        return player.seenNPCs();
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
//    private void userPromptInput(String location) {
    public void userPrompt(String userInput) throws IOException, UnsupportedAudioFileException {
        String location = player.getLocation();
        List<String> userFeedback = new ArrayList<>();
        System.out.println(userInput);
//            String userInput = scanner.nextLine().trim().toLowerCase();
        com.game.hauntedvillage.model.TextParser parser = new com.game.hauntedvillage.model.TextParser();
        //verb-noun pair array using text parser
        ArrayList<String> result = parser.textParser(userInput);
        String userVerb = result.get(0);
        //checks verbs and nouns for validity
        if (!"verb".equals(userVerb)) {
            if (actionChecker(location, userVerb)) {
                setVerbNoun(result);
                setPlayerUpdate(player.updatePlayer(getVerbNoun()));
                if(userVerb.equals("light")) {

                }
            }
            else {
//                userFeedback.add(String.format("Can not %s %s.%n", userVerb.toUpperCase(), userNoun.toUpperCase()));
                userFeedback.add("You can't do that...");
                setPlayerUpdate(userFeedback);
            }
        }
        else {
            userFeedback.add("Please enter a valid command");
            setPlayerUpdate(userFeedback);
        }
    }

    public List<String> getPlayerUpdate() {
        return playerUpdate;
    }

    public void setPlayerUpdate(List<String> playerUpdate) {
        this.playerUpdate = playerUpdate;
    }

    private ArrayList<String> getVerbNoun() {
        return verbNoun;
    }

    private void setVerbNoun(ArrayList<String> verbNoun) {
        this.verbNoun = verbNoun;
    }

//    private String getPlayerChoice() {
//        return playerChoice;
//    }
//
//    private void setPlayerChoice(String playerChoice) {
//        this.playerChoice = playerChoice;
//    }
//
//    private boolean isStartCondition() {
//        return startCondition;
//    }
//
//    private void setStartCondition(boolean condition) {
//        this.startCondition = condition;
//    }
//
//    private String getStartOrQuit() {
//        return startOrQuit;
//    }
//
//    private void setStartOrQuit(String startOrQuit) {
//        this.startOrQuit = startOrQuit;
//    }
}