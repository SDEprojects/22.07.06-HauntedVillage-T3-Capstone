package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NPC {
    ObjectMapper mapper = new ObjectMapper();
    private String fileName = "resources/npc.json";
    private JsonNode rootArray = mapper.readTree(new File(this.fileName));

    public NPC() throws IOException {
    }

    public String npcConversation(String character) {
        String result = "";
        ArrayList<String> convoList = new ArrayList<>(0);

        for (JsonNode root : getRootArray()) {
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
        //Generate random int value based on size of convoList
        int num = (int)Math.floor(Math.random()*(convoList.size()));
        //random conversation
        result = convoList.get(num);

        return result;
    }

    public boolean npcLocation(String location, String character) {
        boolean result = false;

        for (JsonNode root : getRootArray()) {
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
        return result;
    }

    private JsonNode getRootArray() {
        return rootArray;
    }
}
