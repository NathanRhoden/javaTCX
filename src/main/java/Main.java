import tcxparser.Parser;
import tcxparser.Tcx;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        File endurance = new File("src/main/resources/constant.tcx");

        Parser parser = new Parser(endurance);

        Tcx tcx = new Tcx(parser.generateTrackPoints());

        tcx.getMaxPower();




    }
}
