import tcxparser.Parser;
import tcxparser.Tcx;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        File endurance = new File("src/main/resources/Endurance_2.tcx");

        Parser parser = new Parser(endurance);

        Tcx tcx = new Tcx(parser.generateTrackPoints());

        /*
        System.out.println("Average HeartRate : " + tcx.getAverageHeartRate());
        System.out.println("Max HeartRate : " + tcx.getMaxHeartRate());
        System.out.println("Average Power : " + tcx.getAveragePower());
        System.out.println("Average Cadence : " + tcx.getAverageCadence());
        System.out.println("Max Cadence : " + tcx.getMaxCadence());
        System.out.println("Total Distance : " + tcx.getTotalDistance() + " metres");
        System.out.println("Average Speed : " + tcx.getAverageSpeed() + " KP/H");
        System.out.println("Watts per Kilo : " + tcx.getWattsPerKilo(108) + " W/KG");

         */
        System.out.println(tcx.getAverageSpeed(0 , 60));


    }
}
