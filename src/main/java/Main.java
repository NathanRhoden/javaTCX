import tcxparser.Parser;
import tcxparser.entity.TrackPoint;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        File file = new File("src/main/resources/tteffort.tcx");
        File invalid = new File("src/main/resources/invalid.gpx");

        Parser parser = new Parser(file);

        var trackMap = parser.generateTrackPoints();

        int AVGCadence = 0;
        int AvgHeartRate = 0;
        int nonZeroValues = 0;

        for (Map.Entry<Integer, TrackPoint> entry : trackMap.entrySet()) {

            if (entry.getValue().getCadence() > 0) {
                AVGCadence += entry.getValue().getCadence();
                nonZeroValues++;
            }

        }
        for (Map.Entry<Integer, TrackPoint> entry : trackMap.entrySet()) {

            AvgHeartRate += entry.getValue().getHeartRate();

        }

        for (int i = 1; i < 180; i++) {
            System.out.println(trackMap.get(i));
            // METRES / SECONDS
            float speed = trackMap.get(i).getDistance() / i;

            System.out.println("SPEED : " + speed);
        }





    }
}
