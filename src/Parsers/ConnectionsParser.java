package Parsers;

import Metro.Connections;
import org.jsoup.select.Elements;

import java.util.*;

public class ConnectionsParser {

    public static final  String KOLCEVOY = "Большой кольцевой линии";
    public static final  String DOUBLE_NAME_LINES = "\\s{1}[[А-Я]{1}[а-я]+]{1}[\\-{1}[А-Я]{1}[а-я]+]*\\s{1}линии{1}$";
    public static final  String FILEVSKAYA = "Филёвской линии";
    public static final  String PEREXOD = "Переход на станцию";
    public static final  String PERESADKA = "Кросс-платформенная пересадка на станцию";
    public static final  String MONORELS = "Московского монорельса";
    public static final  String KOLTCO = "Московского центрального кольца";


    public static Connections parseConnections (Elements stationsTable) {

    ArrayList <ArrayList<HashMap<String, String>>> allConnections = new ArrayList<>();




        for (int x = 1; x <stationsTable.size() ; x++ ) {

            ArrayList <HashMap<String,String>> oneFullConnection = new ArrayList <>();

            if (stationsTable.get(x).children().get(3).children().size() > 0)

            {
                String stationToConnect = stationsTable.get(x).children().get(1).children().get(0).getElementsByTag("a").text().trim();
                String  stationToConnectLine= stationsTable.get(x).children().get(0).children().get(0).getElementsByTag("span").text().trim();

                HashMap<String,String> stationConnected = new HashMap<>();
                stationConnected.put("line", stationToConnectLine);
                stationConnected.put("station", stationToConnect);
                oneFullConnection.add(stationConnected);

                String line = "";
                String station = "";

                for (int y = 0; y < stationsTable.get(x).children().get(3).children().size(); y++) {

                    if (y % 2 == 0)  { line = stationsTable.get(x).children().get(3).children().get(y).getElementsByTag("span").text().trim();}


                    else { station = stationsTable.get(x).children().get(3).children().get(y).getElementsByTag("span").attr("title");

                        station = station.replaceAll(KOLCEVOY, "").trim();
                        station = station.replaceAll(DOUBLE_NAME_LINES, "");
                        station = station.replaceAll(FILEVSKAYA, "");
                        station = station.replaceAll(PEREXOD, "").trim();
                        station = station.replaceAll(PERESADKA, "").trim();;
                        station = station.replaceAll(MONORELS, "").trim();;
                        station = station.replaceAll(KOLTCO, "").trim();;



                    }

                    if (y % 2 != 0 && y!=0 ) {



                        HashMap<String,String> oneConnection = new HashMap<>();
                        oneConnection.put("line", line);
                        oneConnection.put("station", station);

                        oneFullConnection.add(oneConnection);
                    } }



                Collections.sort(oneFullConnection, (connection1, connection2) -> {

                    if (connection1.get("station").equals(connection2.get("station"))) {
                        return connection1.get("line").compareTo(connection2.get("line"));
                    }
                    return connection1.get("station").compareTo(connection2.get("station"));

                });

                allConnections.add(oneFullConnection);

            }
        }



        Set set = new HashSet(allConnections);
        allConnections.clear();
        allConnections.addAll(set);

        Connections connections = new Connections (allConnections);

        return connections;


    }
}
