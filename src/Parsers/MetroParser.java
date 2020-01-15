package Parsers;

import Metro.Metro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.FileWriter;
import java.io.IOException;

public class MetroParser {

    public static void getJsonMetroMap (Metro metro) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject map = new JsonObject();

        map.add("lines", gson.toJsonTree(metro.lines.getLines()));
        map.add("stations", gson.toJsonTree(metro.linesAndStations.getLinesWithStations()));
        map.add("connections", gson.toJsonTree(metro.connections.getConnections()));

        String json = gson.toJson(map);


        try (
                FileWriter file = new FileWriter("output/metro.json")) {

            file.write(json);
            file.flush();}
        catch (
                IOException e) { e.printStackTrace();}


    }





}
