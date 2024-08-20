package tcxparser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tcxparser.interfaces.TcxMapOperations;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TcxTest {

    Tcx testTcx = new Tcx(new File("src/main/resources/tteffort.tcx"));

    TcxTest() throws Exception {
    }


    @Test
    void get_MaxPower_should_equal_346() {
        assertEquals(346, testTcx.getMaxPower());
    }

    @Test
    void get_MaxHeartRate_should_equal_190() {
        assertEquals(176, testTcx.getMaxHeartRate());
    }

    @Test
    void get_NormalisedPower_should_equal_261() {
        assertEquals(261, testTcx.getNormalisedPower());
    }

    @Test
    void get_MaxCadence_should_equal_97() {
        assertEquals(97, testTcx.getMaxCadence());
    }

    @Test
    void get_AverageSpeed_should_equal_36_point_one(){
        assertEquals(36.1 , testTcx.getAverageSpeed());
    }

    @Test
    void get_AverageSpeed_should_equal_36_point_one_over_ActivePeriod(){
        assertEquals(36.1 , testTcx.getAverageSpeed(0 , testTcx.getMapSize() -1));
    }

}