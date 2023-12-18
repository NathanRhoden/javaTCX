import tcxparser.Parser;
import tcxparser.Tcx;
import tcxparser.entity.TrackPoint;

import java.io.File;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        File file = new File("src/main/resources/tteffort.tcx");
        File invalid = new File("src/main/resources/invalid.gpx");
        File trackNoHR = new File("src/main/resources/track.tcx");

        Parser parser = new Parser(file);

        var trackMap = parser.generateTrackPoints();

        Tcx tcx = new Tcx(trackMap);


        int AVGCadence = 0;
        int AvgHeartRate = 0;
        int nonZeroValues = 0;

        for (Map.Entry<Integer, TrackPoint> entry : trackMap.entrySet()) {

            AvgHeartRate += entry.getValue().getHeartRate();

        }

        System.out.println("Average HeartRate : " + tcx.getAverageHeartRate());
        System.out.println("Max HeartRate : " +  tcx.getMaxHeartRate());
        System.out.println("Average Power : " + tcx.getAveragePower());
        System.out.println("Average Cadence : " + tcx.getAverageCadence());
        System.out.println("Max Cadence : " + tcx.getMaxCadence());


    }
}
