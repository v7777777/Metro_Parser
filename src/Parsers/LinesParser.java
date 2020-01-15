package Parsers;

import Metro.Lines;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.HashMap;


public class LinesParser {


    public static Lines parseLines (Elements stationsTable) {

        ArrayList<HashMap<String,String>> allLinesWithNumbers =  new ArrayList<>();

        String lineNumber = "";
        String lineName = "";
        String previouseLine = "";

        for (int j = 1; j <stationsTable.size() ; j++ ) {

        lineNumber= stationsTable.get(j).children().get(0).children().get(0).getElementsByTag("span").text().trim();

        lineName= stationsTable.get(j).children().get(0).children().get(1).getElementsByTag("span").attr("title");


        if (j == 1 || !(previouseLine.equals(lineNumber))) {

            HashMap<String,String> lineDetails = new HashMap<>();

            lineDetails.put("number", lineNumber);

            lineDetails.put("name", lineName);

            allLinesWithNumbers.add(lineDetails);

        }

            previouseLine = lineNumber; }

         Lines lines = new Lines (allLinesWithNumbers);
         return lines;
    }



}
