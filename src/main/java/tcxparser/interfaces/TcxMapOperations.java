package tcxparser.interfaces;

import tcxparser.entity.TrackPoint;

public interface TcxMapOperations {

    int getAverageHeartRate();

    int getMaxHeartRate();

    int getAveragePower();

    int getMaxPower();

    int getNormalisedPower();

    int getAverageWeightedPower();

    int getAverageCadence();

    int getMaxCadence();

    float getAverageSpeed();

    double getAverageSpeed(int secondsFrom, int secondsTo);

    double getWattsPerKilo(float weight);

    float getTotalDistance();

    int getMapSize();

    TrackPoint getTrackPointBySecond(int second);

    String printBreakdown();


}
