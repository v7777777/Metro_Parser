package Metro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Connections {

    public Connections (ArrayList <ArrayList<HashMap<String, String>>> allConnections) {

        this.allConnections = allConnections;
    }

    ArrayList <ArrayList<HashMap<String, String>>> allConnections;

    public ArrayList<ArrayList<HashMap<String, String>>> getConnections() {
        return allConnections;
    }

    public void print () {

        for (int h = 0; h < allConnections.size(); h++) {
          System.out.println(allConnections.get(h));


    } }


}
