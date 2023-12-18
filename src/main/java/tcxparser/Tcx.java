package tcxparser;

import tcxparser.entity.TrackPoint;
import tcxparser.interfaces.TcxMapOperations;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Tcx implements TcxMapOperations {

    private Set<Entry<Integer, TrackPoint>> trackPointEntrySet;

    public Tcx(HashMap<Integer, TrackPoint> trackPointMap) {
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

        return cadence / nonZeroMapSize ;

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
        return 0;
    }

    @Override
    public float getAverageSpeed(int secondsFrom, int secondsTo) {
        return 0;
    }

    @Override
    public float getWattsPerKilo(float weight) {
        return 0;
    }
}
