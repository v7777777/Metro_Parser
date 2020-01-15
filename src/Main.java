import Metro.Connections;
import Metro.Lines;
import Metro.LinesAndStations;
import Metro.Metro;
import Parsers.*;
import org.jsoup.select.Elements;
import java.io.IOException;
public class Main {



    public static void main(String[] args) throws IOException {

     Elements tableToParse =  HtmlTable.getMetroTable(HtmlTable.getPage("sources/wikipedia.html"),
                                                                        "#mw-content-text > div > table:nth-child(7) > tbody",
                                                                        "tr");

        Lines lines = LinesParser.parseLines(tableToParse);
        LinesAndStations linesAndStations = LinesAndStationsParser.parseLinesAndStationsFromHtml(tableToParse);
        Connections connections = ConnectionsParser.parseConnections(tableToParse);
        // lines.print();
        // linesAndStations.print();
        //connections.print();

        MetroParser.getJsonMetroMap(new Metro(lines, linesAndStations, connections));


       LinesAndStations testPrintingLinesSummary = LinesAndStationsParser.parseFromJsonFile("output/metro.json");
       testPrintingLinesSummary.getEachLineStationsSummary();

    }


}
