package Satellite;

import java.util.Random;

public class SatelliteAPI {

    public enum Status {
        OK,
        BATTERY_LOW,
        PROPULSION_ERROR,
        NAVIGATION_ERROR
    }

    public static Status getStatus(int satelliteIndex){

        Random rand = new Random();
        try {
            Thread.sleep(100 + rand.nextInt(400));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double p = rand.nextDouble();

        if (p < 0.8) {
            return Status.OK;
        }
        else if (p < 0.9) {
            return Status.BATTERY_LOW;
        }
        else if (p < 0.95) {
            return Status.NAVIGATION_ERROR;
        }
        return Status.PROPULSION_ERROR;
    }
}
