package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NPC {

    public static String npcConversation(String character) {
        String result = "";
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode rootArray = mapper.readTree(new File("resources/npc.json"));
            ArrayList<String> convoList = new ArrayList<>(0);
            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(character);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode){
                        // Get node names
                        JsonNode conversationsNode = nameNode.path("conversations");

                        if(conversationsNode.equals(node)){
                            for (JsonNode item: conversationsNode){
                                convoList.add(item.asText());
                            }
                        }
                    }
                }
            }
            //Generate random int value from 0 to 2
            int num = (int)(Math.random()*(3));
            //random conversation
            result = convoList.get(num);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean npcLocation(String location, String character) {
        boolean result = false;
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode rootArray = mapper.readTree(new File("resources/npc.json"));

            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(character);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode){
                        // Get node names
                        JsonNode actionsNode = nameNode.path("location");

                        if(actionsNode.equals(node)){
                            String characterLocation = node.asText();
                            if (location.equals(characterLocation)){
                                result = true;
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static List<String> areaNPCs (String location, List<String> removedNPCs) {
//        List<String> result = new ArrayList<>();
//        List<String> locations = new ArrayList<>();
//        List<String> characters = new ArrayList<>();
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            JsonNode rootArray = mapper.readTree(new File("resources/npc.json"));
//
//            for (JsonNode root : rootArray) {
//
//                for (JsonNode node : root) {
//                    // Get node names
//                    locations.add(node.path("location").toPrettyString());
//                    characters.add(node.path("name").toPrettyString());
//
//                    if (actionsNode.equals(node)) {
//                        String characterLocation = node.asText();
//                        if (location.equals(characterLocation)) {
//
//                        }
//                    }
//                }
//            }
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//        return result;
//    }
}
