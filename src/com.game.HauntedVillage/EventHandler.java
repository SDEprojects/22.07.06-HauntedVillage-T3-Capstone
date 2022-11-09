//package com.game.HauntedVillage;
//
//import com.apps.util.Console;
//import java.util.ArrayList;
//
//class EventHandler {
//
//    public static void eventHandler(String input, String location) {
//        TextParser parser= new TextParser();
//        ArrayList<String> result =  parser.textParser(input);
//
//        if ("help".equals(result.get(0))){
//            Console.clear();
//            HelpMenu.help();
//        }
//        if ("quit".equals(result.get(0))){
//            HelpMenu.quit();
//        }
//        if ("look".equals(result.get(0))){
//            Console.clear();
//            Item.checkForItem(result.get(1));
//        }
//
//        if ("restore".equals(result.get(0))){
//            Engine.restoreGame();
//        }
//
//        if ("save".equals(result.get(0))){
//            Engine.saveGame();
//        }
//
//        if ("map".equals(result.get(0))){
//            Console.clear();
//            MapImage.map(location);
//        }
//    }
//}