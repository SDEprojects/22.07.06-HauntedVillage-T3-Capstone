package com.game.HauntedVillage.com.game.HauntedVillage.app;

import com.game.HauntedVillage.controller.Controller;
//import com.game.HauntedVillage.Engine;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
//        Controller app = new Controller();
//        app.playGame();

        Controller app = new Controller();
        app.execute();
    }
}