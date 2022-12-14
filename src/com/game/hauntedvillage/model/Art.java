package com.game.hauntedvillage.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Art {

    public ArrayList artGenerator(String artItem) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> art = new ArrayList<>();

        try{
            JsonNode rootArray = mapper.readTree(new File("resources/art.json"));
            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(artItem);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing
                    for (JsonNode node : nameNode){
                        art.add(node.asText());
                    }
                    result.add(art);
                } else {
                    System.out.println("No art to display");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String showArt(String artItem){
        ArrayList<ArrayList<String>> result;
        result = artGenerator(artItem);
        String art = result.get(0).get(0);

        return art;
//        System.out.println(art);
    }
}