package Metro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Lines {


    public Lines (ArrayList<HashMap<String,String>> lines) {

        this.lines = lines;
    }

    ArrayList<HashMap<String,String>> lines;

    public  ArrayList<HashMap<String,String>> getLines () {
        return lines;
    }

    public void print () {

        for (int f = 0; f < lines.size(); f++ ) {

            for (String key : lines.get(f).keySet())
             { System.out.println(key + " " + lines.get(f).get(key));}
        }
    }


    }


