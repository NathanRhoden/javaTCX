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
        return 0;
    }

    @Override
    public int getAveragePower() {
        return 0;
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
        return 0;
    }

    @Override
    public int getMaxCadence() {
        return 0;
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
