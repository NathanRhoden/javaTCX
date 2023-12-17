package tcxparser.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@RequiredArgsConstructor
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
