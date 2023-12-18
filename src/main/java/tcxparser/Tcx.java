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

    public Tcx(HashMap<Integer, TrackPoint> trackPointMap) {
        this.trackPointMap = trackPointMap;
        this.trackPointEntrySet = trackPointMap.entrySet();
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

        return totalDistanceKilometres / elapsedTime;

    }

    @Override
    public float getAverageSpeed(int secondsFrom, int secondsTo) {

        float totalDistance = 0;

        if(secondsFrom == 0){
            totalDistance = trackPointMap.get(secondsTo).getDistance();
        }

        System.out.println(trackPointMap.get(secondsTo).getDistance() + " TOTAL DISTANCE TO ");
        System.out.println(trackPointMap.get(secondsFrom).getDistance() + " TOTAL DISTANCE FROM");

        System.out.println(totalDistance + " TOTAL DISTANCE");

        return 0f;
    }

    @Override
    public double getWattsPerKilo(float weight) {

        return Math.floor((((double) getAveragePower() * 100)) / 100) / weight;
    }

    @Override
    public float getTotalDistance() {

        float totalDistance = 0;

        for (Entry<Integer, TrackPoint> entry : trackPointEntrySet) {
            if (entry.getValue().getDistance() > totalDistance) {
                totalDistance = entry.getValue().getDistance();
            }
        }
        return totalDistance;

    }
}
