package com.game.HauntedVillage.app;

//import com.game.HauntedVillage.Controller;
import com.game.HauntedVillage.controller.Controller;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {

        Controller app = new Controller();
        app.execute();
    }
}