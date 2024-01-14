package tcxparser;

import tcxparser.entity.TrackPoint;
import tcxparser.interfaces.TcxMapOperations;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Tcx implements TcxMapOperations {

    private final Set<Entry<Integer, TrackPoint>> trackPointEntrySet;
    private final HashMap<Integer, TrackPoint> trackPointMap;
    private final int mapSize;

    private final float toHours = 3600f;
    private final float metresToKm = 1000f;


    public Tcx(HashMap<Integer, TrackPoint> trackPointMap) {
        this.trackPointMap = trackPointMap;
        this.trackPointEntrySet = trackPointMap.entrySet();
        this.mapSize = trackPointMap.size();
    }

    @Override
    public int getAverageHeartRate() {
        int currentHeartRate = 0;

        for (Entry<Integer, TrackPoint> entry : trackPointEntrySet) {
            currentHeartRate += entry.getValue().getHeartRate();
        }

        return currentHeartRate / trackPointEntrySet.size();
    }

    @Override
    public int getMaxHeartRate() {
        int max = Integer.MIN_VALUE;

        for (Entry<Integer, TrackPoint> entry : trackPointEntrySet) {
            int hr = entry.getValue().getHeartRate();
            if (hr > max) {
                max = hr;
            }
        }
        return max;
    }

    @Override
    public int getAveragePower() {
        int power = 0;

        for (Entry<Integer, TrackPoint> entry : trackPointEntrySet) {
            int currentPower = entry.getValue().getWatts();
            power += currentPower;

        }

        return power / trackPointEntrySet.size();

    }

    @Override
    public double getNormalisedPower() {

        if(mapSize < 30){
            throw new RuntimeException("There must be atleast 30 trackpoints");
        }

        //REMOVE THE 30 ENDPOINTS FROM THE DATA
        double[] powerData = new double[mapSize];
        double window = 30;
        int reducedDataSetSize = (int) (mapSize - (window - 1));
        double[] movingAverage = new double[reducedDataSetSize];

        for (int i = 0; i < mapSize; i++) {
            powerData[i] = getTrackPoint(i).getWatts();
        }

        for(int k = 0 ;  k < movingAverage.length ; k++){
            int counter = 0 ;
            int average = 0 ;

            while(counter < window){
                average += powerData[k + counter];
                counter++;
            }
            movingAverage[k] = Math.round(average / window);
        }

        for (int j = 0; j < movingAverage.length; j++) {
            movingAverage[j] = Math.pow(movingAverage[j], 4d);
        }

        return Math.round(Math.pow(getAverage(movingAverage), 0.25d));

    }

    @Override
    public int getAverageCadence() {
        int cadence = 0;
        int nonZeroMapSize = 0;

        for (Entry<Integer, TrackPoint> entry : trackPointEntrySet) {

            int currentCadence = entry.getValue().getCadence();

            if (currentCadence > 0) {
                cadence += currentCadence;
                nonZeroMapSize++;
            }
        }

        return cadence / nonZeroMapSize;

    }

    @Override
    public int getMaxCadence() {
        int max = Integer.MIN_VALUE;

        for (Entry<Integer, TrackPoint> entry : trackPointEntrySet) {
            int cadence = entry.getValue().getCadence();
            if (cadence > max) {
                max = cadence;
            }
        }
        return max;
    }

    @Override
    public float getAverageSpeed() {

        float totalDistanceKilometres = getTotalDistance();
        float elapsedTime;


        LocalDateTime startTime = getTrackPoint(0).getTime();
        LocalDateTime totalTime = getTrackPoint(getMapSize() - 1).getTime();

        elapsedTime = startTime.until(totalTime, ChronoUnit.SECONDS) / toHours;


        return Math.round((totalDistanceKilometres / elapsedTime) * 10f) / 10f;

    }

    @Override
    public int getMaxPower() {
        int MAX = Integer.MIN_VALUE;

        for (Entry<Integer, TrackPoint> entry : trackPointEntrySet) {

            if (entry.getValue().getWatts() > MAX) {
                MAX = entry.getValue().getWatts();
            }

        }
        return MAX;
    }

    @Override
    public double getAverageSpeed(int secondsFrom, int secondsTo) {

        float distanceOverInterval;
        float elapsedTime;

        if (secondsTo <= secondsFrom) {
            throw new RuntimeException("Parameter TO must be greater than parameter FROM");
        }

        LocalDateTime startTime = getTrackPoint(secondsFrom).getTime();
        LocalDateTime totalTime = getTrackPoint(secondsTo).getTime();

        if (secondsFrom == 0) {
            elapsedTime = startTime.until(totalTime, ChronoUnit.SECONDS) / toHours;

            distanceOverInterval = getTrackPoint(secondsTo - 1).getDistance() / metresToKm;

        } else {

            elapsedTime = (secondsTo - secondsFrom) / toHours;
            distanceOverInterval = (getTrackPoint(secondsTo - 1).getDistance() - trackPointMap.get(secondsFrom).getDistance()) / metresToKm;

        }
        return Math.round((distanceOverInterval / elapsedTime) * 10.0) / 10.0;

    }

    @Override
    public double getWattsPerKilo(float weight) {

        return Math.floor((((double) getAveragePower() * 100)) / 100) / weight;
    }

    @Override
    public float getTotalDistance() {

        return trackPointMap.get(mapSize - 1).getDistance() / metresToKm;

    }

    @Override
    public int getMapSize() {
        return mapSize;
    }

    @Override
    public String printBreakdown() {

        return "Average HeartRate : " + getAverageHeartRate() + " BPM" + "\n" +
                "Max HeartRate : " + getMaxHeartRate() + " BPM" + "\n" +
                "Average Power : " + getAveragePower() + " W" + "\n" +
                "Average Cadence : " + getAverageCadence() + " RPM" + "\n" +
                "Max Cadence  : " + getMaxCadence() + " RPM" + "\n" +
                "Total Distance : " + getTotalDistance() + " KM" + "\n" +
                "Average Speed : " + getAverageSpeed() + " K/PH";

    }

    @Override
    public TrackPoint getTrackPointBySecond(int second) {
        return trackPointMap.get(second);
    }

    private TrackPoint getTrackPoint(int point) {
        return trackPointMap.get(point);
    }

    double getAverage(double[] array) {
        double sum = 0;
        for (double i : array) {
            sum += i;
        }

        return sum / array.length;
    }
}
