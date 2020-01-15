package Parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class HtmlTable {

    public static Document getPage (String urlOrPath) throws IOException {

        String path = parseFile(urlOrPath);
        Document doc = Jsoup.parse(path);

       return doc;  }

    public static Elements getMetroTable (Document doc, String selctor1, String selctor2 ) throws IOException {

        Elements stationsTable = doc.select(selctor1);
        stationsTable = stationsTable.select(selctor2);
        return stationsTable;

   }

    private static String parseFile(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        List<String> lines = Files.readAllLines(Paths.get(path));
        lines.forEach(line -> builder.append(line + "\n"));
        return  builder.toString();

    }



}
