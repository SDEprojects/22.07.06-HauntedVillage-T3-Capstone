package com.game.HauntedVillage;

import java.util.Scanner;

public class Menu {
    // FIELDS
    private Scanner scanner = new Scanner(System.in);
    private String playerChoice;
    private boolean condition;
    private String startOrQuit;

    void startNewGame() {
        startOrQuit = "s";
        System.out.println("Would you like to start a new game?");
        userChoice();
    }

    void help() {
        System.out.println("----------------------- Help Menu -----------------------\n");

        System.out.println("To move, when prompted enter: \n     go north \n     go east \n     go south" +
                "\n     go west\n");
        System.out.println("To use an item, when prompted enter: \n     use \"item name\"\n");
        System.out.println("To add an item to your inventory, when prompted enter: \n     take \"item name\"\n");
        System.out.println("Enter \"quit\" at any time to end the game");
        System.out.println("-------------------- End Help Menu -----------------------\n");
    }

    void quit() {
        startOrQuit = "q";
        System.out.println("Are you sure you want to 'quit'? yes| no");
        userChoice();
    }

    void userChoice() {
        while (!condition) {
            System.out.println(" [Y]es | [N]o ");
            playerChoice = scanner.nextLine().trim().toUpperCase();
            if(playerChoice.matches("Y|N")) {
                if((startOrQuit.equals("s") && "Y".equals(playerChoice)) || (startOrQuit.equals("q") && "N".equals(playerChoice))) {
                    condition = true;
                }
                else {
                    System.out.println("Thank you for playing");
                    System.exit(0);
                }
            }
        }
    }
}
