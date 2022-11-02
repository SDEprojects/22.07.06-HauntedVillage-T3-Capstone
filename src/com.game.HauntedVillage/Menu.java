package com.game.HauntedVillage;

import java.util.Scanner;

public class Menu {


    public static void startNewGame() {
        String playerChoice;
        System.out.println("Would you like to start a new game? start | no ");
        boolean condition = false;
        while (!condition) {
            Scanner scanner = new Scanner(System.in);
            playerChoice = scanner.nextLine().trim().toLowerCase();
            if (playerChoice.equals("start")) {
                condition = true;
//                Controller.playGame();
                Sound.runMusic();
            } else if (playerChoice.equals("no")) {
                System.out.println("Thank you for playing");
                System.exit(0);
            } else {
                System.out.println("Please enter 'start' to start the game  or 'no' to quit the game.");
                startNewGame();
            }
        }
    }

    public static void help() {
        System.out.println("----------------------- Help Menu -----------------------\n");
        System.out.println("                                     enter (back) to exit\n\n");

        System.out.println("To move, when prompted enter: \n     go north \n     go east \n     go south" +
                "\n     go west\n");
        System.out.println("To use an item, when prompted enter: \n     use \"item name\"\n");
        System.out.println("To add an item to your inventory, when prompted enter: \n     take \"item name\"\n");
        System.out.println("Enter \"quit\" at any time to end the game");

        boolean condition = false;
        while (!condition) {
            Scanner scanner = new Scanner(System.in);
            String playerChoice = scanner.nextLine().trim().toLowerCase();
            if (playerChoice.equals("back")) {
                condition = true;
            }
        }
    }

    public static void quit() {
        String quit;

        System.out.println("Are you sure you want to 'quit'? yes| no");
        Scanner scanner = new Scanner(System.in);
        quit = scanner.nextLine().trim().toLowerCase();
        if (quit.equals("yes")) {
            System.out.println("Thank you for playing");
            System.exit(0);
        }
    }
}
