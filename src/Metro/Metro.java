package Metro;

public class Metro {

    public Lines lines;
    public LinesAndStations linesAndStations;
    public Connections connections;

    public Metro (Lines lines, LinesAndStations linesAndStations, Connections connections ) {

        this.lines = lines;
        this.linesAndStations = linesAndStations;
        this.connections = connections;


    }
}
