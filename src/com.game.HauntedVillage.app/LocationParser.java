package com.game.HauntedVillage.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.HauntedVillage.LocationData;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

class LocationParser {

    public static void main(String[] args) throws IOException {
        Reader read = Files.newBufferedReader(Paths.get("./resources/location.json"));
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, LocationData>> locationList = mapper.readValue(read, new TypeReference<>(){});

        java.util.Map<String,LocationData> locationMap = locationList.get(0);
        LocationData location = locationMap.get(locationMap.keySet().iterator().next());

        System.out.println("first location is " + location.getName());
        String nextLocation = location.getDirections().get("east");

        for (java.util.Map<String,LocationData> loc : locationList) {
            if (loc.keySet().iterator().next().equals(nextLocation)){
                location = loc.get(nextLocation);

            }
        }
        System.out.println("next location is " + location.getName());
    }
}