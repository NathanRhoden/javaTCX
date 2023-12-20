package tcxparser;

import tcxparser.entity.TrackPoint;
import tcxparser.interfaces.TcxMapOperations;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class Tcx implements TcxMapOperations {

    private Set<Entry<Integer, TrackPoint>> trackPointEntrySet;
    private HashMap<Integer, TrackPoint> trackPointMap;

    private final float toHours = 3600f;

    private final int mapSize;

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
    public int getNormalisedPower() {
        return 0;
    }

    @Override
    public int getAverageWeightedPower() {
        return 0;
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

        float totalDistanceKilometres = getTotalDistance() / 1000f;
        float elapsedTime;


        LocalDateTime startTime = trackPointMap.get(0).getTime();
        LocalDateTime totalTime = trackPointMap.get(trackPointMap.size() - 1).getTime();

        elapsedTime = startTime.until(totalTime, ChronoUnit.SECONDS) / toHours;

        return Math.round(totalDistanceKilometres / elapsedTime);

    }

    @Override
    public float getAverageSpeed(int secondsFrom, int secondsTo) {

        LocalDateTime startTime;
        LocalDateTime endTime;
        float distanceOverInterval;
        float elapsedTime;

        if (secondsTo <= secondsFrom) {
            throw new RuntimeException("Parameter to must be greater than parameter from");
        }

        if (secondsFrom == 0) {
            elapsedTime = secondsTo / toHours;
            distanceOverInterval = trackPointMap.get(secondsTo - 1).getDistance() / 1000f;

            //System.out.printf("%s , %s" , elapsedTime , distanceOverInterval);

            return Math.round(distanceOverInterval / elapsedTime);
        }


        return 10f;
    }

    @Override
    public double getWattsPerKilo(float weight) {

        return Math.floor((((double) getAveragePower() * 100)) / 100) / weight;
    }

    @Override
    public float getTotalDistance() {

        return trackPointMap.get(mapSize - 1).getDistance();

    }

    @Override
    public int getMapSize() {
        return mapSize;
    }

    @Override
    public String printBreakdown() {

        return "Average HeartRate : " + getAverageHeartRate() + "\n" +
                "Max HeartRate : " + getMaxHeartRate() + "\n" +
                "Average Power : " + getAveragePower() + "\n" +
                "Average Cadence : " + getAverageCadence() + "\n" +
                "Max Cadence  : " + getMaxCadence() + "\n" +
                "Total Distance : " + getTotalDistance() + "\n" +
                "Average Speed : " + getAverageSpeed();

    }

    @Override
    public TrackPoint getTrackPointBySecond(int second) {
        return trackPointMap.get(second);
    }
}
