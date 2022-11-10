package com.game.HauntedVillage.controller;

//import com.apps.util.Console;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.HauntedVillage.*;
//import com.game.HauntedVillage.app.Print;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    // initialize scanner. takes system input
//    Scanner scanner = new Scanner(System.in);
    private ArrayList<String> verbNoun = new ArrayList<>(List.of("verb", "noun"));
    private Player player = new Player();
    private List<String> playerUpdate = new ArrayList<>();
    private MapImage mapImage = new MapImage();
//    private Splash splash = new Splash();
//    private String playerChoice;
//    private boolean startCondition;
//    private String startOrQuit;
//    public ObjectHandler objectsActions = new ObjectHandler();
//    public GameFrame gameFrame = new GameFrame();


    public Controller() throws IOException {
    }

    public void execute() throws IOException {
//        Console.clear();
//        showSplash();
//        startNewGame();
//        Console.clear();
//        gameIntro();
//        Console.clear();
//        gameLoop();
//        gameFrame.TitleScreen();
    }

//    private void showSplash() {
//        String screen = splash.splashScreen();
//        Print print4 = new Print(screen);
//        print4.printToConsole();
//    }
//
//    private void startNewGame() {
//        setStartOrQuit("s");
//        System.out.println("Would you like to start a new game?");
//        userChoice();
//    }
//
//    private void quit() {
//        setStartOrQuit("q");
//        System.out.println("Are you sure you want to 'quit'?");
//        userChoice();
//    }

//    private void userChoice() {
//        setStartCondition(false);
//        while (!isStartCondition()) {
//            System.out.println(" [Y]es | [N]o ");
//            setPlayerChoice(scanner.nextLine().trim().toUpperCase());
//
//            if(getPlayerChoice().matches("Y|N")) {
//                if((getStartOrQuit().equals("s") && "Y".equals(getPlayerChoice())) || (getStartOrQuit().equals("q") && "N".equals(getPlayerChoice()))) {
//                    setStartCondition(true);
//                }
//                else {
//                    System.out.println("Thank you for playing");
//                    System.exit(0);
//                }
//            }
//        }
//    }

//    private void gameIntro() {
//        List<String> intro = player.showGameIntro();
//        Print print3 = new Print(intro);
//        print3.printListToConsole();
//    }

//    private void userChoice() {
//        setStartCondition(false);
//        while (!isStartCondition()) {
//            System.out.println(" [Y]es | [N]o ");
//            setPlayerChoice(scanner.nextLine().trim().toUpperCase());
//
//            if(getPlayerChoice().matches("Y|N")) {
//                if((getStartOrQuit().equals("s") && "Y".equals(getPlayerChoice())) || (getStartOrQuit().equals("q") && "N".equals(getPlayerChoice()))) {
//                    setStartCondition(true);
//                }
//                else {
//                    System.out.println("Thank you for playing");
//                    System.exit(0);
//                }
//            }
//        }
//    }

    //game loop
//    private void gameLoop() throws IOException {
//
////        String oldLocation = "";
//        //game continues if player is alive
//        while (!player.isEndGame()) {
//
//            //returns player information at top of screen
//            Print print2 = new Print(player.playerCurrentInfo());
//            print2.printListToConsole();
//
//            //returns location description and player prompt
////            if(!oldLocation.equals(player.getLocation())) {
//            Print print1 = new Print(player.areaDescription());
//            print1.printToConsole();
////                oldLocation = player.getLocation();
////            }
//
//            //takes user input, specific to players location
////            userPromptInput(player.getLocation());
//
//            Print print = new Print(player.updatePlayer(getVerbNoun()));
//            print.printListToConsole();
//
//            //clears console before update
//            Console.clear();
//
//            //if player is dead, end game
//            if (player.end() == true) {
//                player.setEndGame(true);
//            }
//        }
//    }

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

    //game loop
//    private void gameLoop() throws IOException {
//
////        String oldLocation = "";
//        //game continues if player is alive
//        while (!player.isEndGame()) {



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
    public void userPrompt(String userInput) throws IOException {
        String location = player.getLocation();
        List<String> userFeedback = getPlayerUpdate();
        System.out.println(userInput);
//            String userInput = scanner.nextLine().trim().toLowerCase();
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
//                if(userVerb.equals("quit")) {
//                    quit();
//                }
            if (actionChecker(location, userVerb)) {
                setVerbNoun(result);
                setPlayerUpdate(player.updatePlayer(getVerbNoun()));
            }
            else {
                userFeedback.add(String.format("Can not %s %s in %s.%n Provide valid input.%n", userVerb.toUpperCase(), userNoun.toUpperCase(), location.toUpperCase()));
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