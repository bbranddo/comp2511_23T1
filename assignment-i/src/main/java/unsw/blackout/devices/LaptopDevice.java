package unsw.blackout.devices;

import unsw.utils.Angle;
import unsw.utils.MathsHelper;
import static unsw.utils.MathsHelper.RADIUS_OF_JUPITER;

public class LaptopDevice extends device {
    private double range = 100000;
    private boolean inRange = false;

    public LaptopDevice(String id, String type, Angle position) {
        super(id, type, position);
    }

    @Override
    public double getRange() {
        return range;
    }

    @Override
    public boolean SatellitesInRange(Angle satelliteAngle, double satelliteHeight, double satelliteRange) {
        boolean visible = MathsHelper.isVisible(RADIUS_OF_JUPITER, getPosition(), satelliteHeight, satelliteAngle);
        if (visible) {
            return true;
        }
        return inRange;
    }
}
