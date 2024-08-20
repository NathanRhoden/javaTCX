package tcxparser.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The TrackPoint class represents a point along a trackPoint map with latitude, longitude, altitude, distance,
 * heart rate, cadence, watts, and time information.
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TrackPoint {

    private float latitude;
    private float longitude;
    private float altitude;
    private float distance;
    private int heartRate;
    private int cadence;
    private int watts;
    private LocalDateTime time;

}
