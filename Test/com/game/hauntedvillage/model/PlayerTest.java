package com.game.hauntedvillage.model;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player = new Player();

    public PlayerTest() throws IOException, UnsupportedAudioFileException {
    }

    @Test
    public void getBackgroundImagesTrue() {

        player.getCurrentLocationJpeg();

        assertTrue("images/cabinedited.jpg", true);
    }

    @Test
    public void getBackgroundImagesFalse() {
        player.getCurrentLocationJpeg();
        assertFalse("images/local.jpg", false);
    }


    @Test
    public void areaDescriptionTrue() {
        player.areaDescription();

        assertTrue("You are at home in your familiar log cabin. You look around and see some items that may be of use. \n\nYou can search the house for some useful items, rest to heal, or go east outside.", true);
    }
    @Test
    public void areaDescriptionEqual() {
        String actual = player.areaDescription();
        String court = "You are at home in your familiar log cabin. You look around and see some items that may be of use. \n\nYou can search the house for some useful items, rest to heal, or go east outside.";


        assertEquals(court,actual);
    }


}