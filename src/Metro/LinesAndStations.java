package Metro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public class LinesAndStations {

    public LinesAndStations ( TreeMap<String, List<String>> linesWithStations) {

        this.linesWithStations = linesWithStations;
    }

    TreeMap<String, List<String>> linesWithStations;

    public TreeMap<String, List<String>> getLinesWithStations() {
        return linesWithStations;
    }

    public void print () {
        for (String key : linesWithStations.keySet())
       { System.out.println(key + " " + linesWithStations.get(key));}

    }

    public void getEachLineStationsSummary () {

    for (String key : linesWithStations.keySet()) {
        System.out.println("линия: " + key + ", количество станций: " +  linesWithStations.get(key).size() ); }


} }
