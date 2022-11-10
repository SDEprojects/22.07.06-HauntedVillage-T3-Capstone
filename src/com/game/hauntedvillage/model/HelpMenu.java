package com.game.hauntedvillage.model;

import java.util.ArrayList;
import java.util.List;

class HelpMenu {

    private List<String> helpList = new ArrayList<>();

    List<String> help() {
        helpList.add("----------------------- Help Menu -----------------------\n");

        helpList.add("To move, when prompted enter: \n     go north \n     go east \n     go south" +
                "\n     go west\n");
        helpList.add("To use an item, when prompted enter: \n     use \"item name\"\n");
        helpList.add("To add an item to your inventory, when prompted enter: \n     take \"item name\"\n");
        helpList.add("Enter \"quit\" at any time to end the game");
        helpList.add("-------------------- End Help Menu -----------------------\n");

        return helpList;
    }
}
