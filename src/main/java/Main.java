import tcxparser.Parser;
import tcxparser.Tcx;
import tcxparser.entity.TrackPoint;

import java.io.File;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        File file = new File("src/main/resources/tteffort.tcx");
        File invalid = new File("src/main/resources/invalid.tcx");
        File trackNoHR = new File("src/main/resources/track.tcx");
        File pacer = new File("src/main/resources/PACER.tcx");
        File maiaStickey = new File("src/main/resources/Stickney.tcx");

        Parser parser = new Parser(file);

        var trackMap = parser.generateTrackPoints();

        Tcx tcx = new Tcx(trackMap);

        System.out.println("Average HeartRate : " + tcx.getAverageHeartRate());
        System.out.println("Max HeartRate : " + tcx.getMaxHeartRate());
        System.out.println("Average Power : " + tcx.getAveragePower());
        System.out.println("Average Cadence : " + tcx.getAverageCadence());
        System.out.println("Max Cadence : " + tcx.getMaxCadence());
        System.out.println("Total Distance : " + tcx.getTotalDistance() + " metres");
        System.out.println("Average Speed : " + tcx.getAverageSpeed() + " KP/H");
        System.out.println("Watts per Kilo : " + tcx.getWattsPerKilo(108) + " W/KG");


    }
}
