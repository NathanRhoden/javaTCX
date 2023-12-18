package tcxparser.interfaces;

public interface TcxMapOperations {

    int getAverageHeartRate();

    int getMaxHeartRate();

    int getAveragePower();

    int getNormalisedPower();

    int getAverageWeightedPower();

    int getAverageCadence();

    int getMaxCadence();

    float getAverageSpeed();

    float getAverageSpeed(int secondsFrom, int secondsTo);

    float getWattsPerKilo(float weight);

}
