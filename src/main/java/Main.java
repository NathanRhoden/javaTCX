import tcxparser.TCX;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {

        File file = new File("src/main/resources/tteffort.tcx");
        TCX tcxParser = new TCX(file);

        var testParse = tcxParser.generateTrackPoints();
        System.out.println(testParse.toString());
    }
}
