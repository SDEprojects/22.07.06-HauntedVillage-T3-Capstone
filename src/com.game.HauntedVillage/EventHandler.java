package com.game.HauntedVillage;

import com.apps.util.Console;
import java.util.ArrayList;

class EventHandler {

    public static void eventHandler(String input, String location) {
        TextParser parser= new TextParser();
        ArrayList<String> result =  parser.textParser(input);
        Menu menu = new Menu();

        if ("help".equals(result.get(0))){
            Console.clear();
            menu.help();
        }
        if ("quit".equals(result.get(0))){
            menu.quit();
        }
        if ("look".equals(result.get(0))){
            Console.clear();
            Item.checkForItem(result.get(1));
        }

        if ("restore".equals(result.get(0))){
            Engine.restoreGame();
        }

        if ("save".equals(result.get(0))){
            Engine.saveGame();
        }

        if ("map".equals(result.get(0))){
            Console.clear();
            MapImage.map(location);
        }
    }
}