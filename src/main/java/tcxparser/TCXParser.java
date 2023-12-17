package tcxparser;

import tcxparser.entity.TrackPoint;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public interface TCXParser {

    HashMap<Integer , TrackPoint> generateTrackPoints() throws XMLStreamException, FileNotFoundException;

    HashMap<Integer , TrackPoint> generateTrackPoints(String tcxString);

    boolean validateFile(File file);


}
