package com.game.hauntedvillage;

import com.game.hauntedvillage.app.GameFrame;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {

        GameFrame gameFrame = new GameFrame();
        gameFrame.titleScreen();
    }
}