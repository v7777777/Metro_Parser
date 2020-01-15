package Parsers;

import Metro.LinesAndStations;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.jsoup.select.Elements;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class LinesAndStationsParser {

    public static LinesAndStations parseLinesAndStationsFromHtml (Elements stationsTable) {

        TreeMap<String, List<String>> linesWithStations = new TreeMap<>();
        ArrayList<String> stationsList = new ArrayList<>();
        String previousLine = "";
        String currentStation = "";
        String currentLine = "";

        for (int i = 1; i < stationsTable.size(); i++ ) {

            currentStation = stationsTable.get(i).children().get(1).children().get(0).getElementsByTag("a").text().trim();
            currentLine= stationsTable.get(i).children().get(0).children().get(0).getElementsByTag("span").text().trim();


            if (currentLine.equals(previousLine) || i == 1 )
            {
                stationsList.add(currentStation);


                if (i == (stationsTable.size()-1))
                {   ArrayList <String> stationsListToAdd = new ArrayList<>();
                    stationsListToAdd.addAll(stationsList);
                    linesWithStations.put(currentLine, stationsListToAdd);

                    continue;}


            }

            else if  (!(currentLine.equals(previousLine))) {
                ArrayList <String> stationsListToAdd = new ArrayList<>();
                stationsListToAdd.addAll(stationsList);
                linesWithStations.put(previousLine, stationsListToAdd);
                stationsList.clear();
                stationsList.add(currentStation);


            }

            else if  (!(currentLine.equals(previousLine)) && i == (stationsTable.size()-1))

            {
                ArrayList <String> stationsListToAdd = new ArrayList<>();
                stationsListToAdd.addAll(stationsList);
                linesWithStations.put(previousLine, stationsListToAdd);
                stationsList.clear();
                stationsList.add( currentStation);
                ArrayList <String> stationsListToAddLast = new ArrayList<>();
                stationsListToAddLast.addAll(stationsList);
                linesWithStations.put( currentLine, stationsListToAddLast);
                continue;
            }


            previousLine = currentLine;


        }

        LinesAndStations linesAndStations = new LinesAndStations(linesWithStations);

        return linesAndStations;

    }

    public static LinesAndStations parseFromJsonFile (String path) throws FileNotFoundException {

        TreeMap<String, List<String>> linesWithStations;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        JsonObject map = JsonParser.parseReader(bufferedReader).getAsJsonObject();

        map.get("stations");

        Gson gson = new Gson();
        Type linesWithStationsType = new TypeToken<TreeMap<String, List<String>>>() {}.getType();
        linesWithStations = gson.fromJson( map.get("stations"), linesWithStationsType);

        return new LinesAndStations(linesWithStations);



    }


}
