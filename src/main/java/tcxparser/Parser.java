package tcxparser;

import tcxparser.entity.TrackPoint;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Parser implements TCXParser {

    private File tcxFile;
    private String tcxString;
    private TrackPoint trackPoint;
    private HashMap<Integer, TrackPoint> trackPointMap;
    private int index = 0;

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Parser(File tcxFile) {
        this.tcxFile = tcxFile;
    }

    @Override
    public HashMap<Integer, TrackPoint> generateTrackPoints() throws Exception {

        if (!validateFile(tcxFile)) {
            throw new RuntimeException("Invalid file type");
        }

        trackPointMap = new HashMap<>();

        XMLInputFactory factory = XMLInputFactory.newInstance();

        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(tcxFile));

        while (eventReader.hasNext()) {

            XMLEvent event = eventReader.nextEvent();

            if (event.isStartElement()) {

                StartElement startElement = event.asStartElement();

                switch (startElement.getName().getLocalPart()) {
                    case "Trackpoint":
                        trackPoint = new TrackPoint();
                        break;

                    case "AltitudeMeters":
                        event = eventReader.nextEvent();
                        trackPoint.setAltitude(Float.parseFloat(event.asCharacters().getData()));
                        break;

                    case "LatitudeDegrees":
                        event = eventReader.nextEvent();
                        trackPoint.setLatitude(Float.parseFloat(event.asCharacters().getData()));
                        break;

                    case "LongitudeDegrees":
                        event = eventReader.nextEvent();
                        trackPoint.setLongitude(Float.parseFloat(event.asCharacters().getData()));
                        break;

                    case "HeartRateBpm":
                        event = eventReader.nextEvent();
                        if (event.isStartElement() && event.asStartElement().getName().getLocalPart().equals("Value")) {
                            event = eventReader.nextEvent();
                            trackPoint.setHeartRate(Integer.parseInt(event.asCharacters().getData()));
                        }
                        break;

                    case "Cadence":
                        event = eventReader.nextEvent();
                        trackPoint.setCadence(Integer.parseInt(event.asCharacters().getData()));
                        break;

                    case "Watts":
                        event = eventReader.nextEvent();
                        trackPoint.setWatts(Integer.parseInt(event.asCharacters().getData()));
                        break;

                    case "DistanceMeters":
                        if (trackPoint != null) {
                            event = eventReader.nextEvent();
                            trackPoint.setDistance(Float.parseFloat(event.asCharacters().getData()));
                        }
                        break;

                    case "Time":
                        event = eventReader.nextEvent();
                        String dateTime = event.asCharacters().getData();

                        LocalDateTime time = LocalDateTime.parse(dateTime.substring(0, dateTime.length() - 1));

                        trackPoint.setTime(time);
                        break;
                }
            }

            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                if (endElement.getName().getLocalPart().equals("Trackpoint")) {
                    trackPointMap.put(index, trackPoint);
                    index++;
                }
            }

        }

        return trackPointMap;
    }

    @Override
    public boolean validateFile(File file) {
        return file.getName().endsWith(".tcx");
    }
}
