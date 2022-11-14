package com.game.hauntedvillage.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ItemTest {
    Item item = new Item();


    @Test
    public void checkForItemEqual() {
        List<String> itemList = item.checkForItem("images/matches.jpg");
        List<String> expected = List.of("some matches");
        assertEquals(expected, itemList);



    }
    @Test
    public void checkForItemNotEqual() {

        List<String> itemList = item.checkForItem("images/tavern.jpg");
        List<String> expected = List.of("some matches");
        assertNotEquals(expected, itemList);

    }



    @Test
    public void checkStationaryItemLocation() {
        boolean result = item.checkStationaryItemLocation("Well", "blue stone");
        boolean expected = false;
        assertEquals(expected, result);
    }
}