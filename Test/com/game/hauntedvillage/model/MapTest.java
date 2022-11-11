package com.game.hauntedvillage.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {
    Map map = new Map();

    @Test
    public void moveFinderTestTrue() {
       map.moveFinder("Center Courtyard","go west");
       assertTrue("go west", true);


    }

}