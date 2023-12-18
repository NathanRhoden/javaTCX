package tcxparser.interfaces;

import tcxparser.entity.TrackPoint;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public interface TCXParser {

    HashMap<Integer, TrackPoint> generateTrackPoints() throws Exception;

    boolean validateFile(File file);


}
