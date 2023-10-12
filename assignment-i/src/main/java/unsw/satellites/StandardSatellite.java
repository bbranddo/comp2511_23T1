package unsw.satellites;

import unsw.utils.Angle;
import unsw.utils.MathsHelper;

public class StandardSatellite extends satellites {
    private double velocity = 2500;
    private double range = 150000;
    private boolean inRange = false;

    public StandardSatellite(String id, String type, double height, Angle angle) {
        super(id, type, height, angle);
    }

    public double getVelocity() {
        return velocity;
    }

    public double getRange() {
        return range;
    }

    @Override
    public void move() {
        Angle position = getPosition().subtract(Angle.fromRadians(velocity / getHeight()));
        setPosition(position);
    }

    @Override
    public boolean DevicesInRange(String deviceId, Angle deviceAngle, double diviceRange, String type) {
        boolean visible = MathsHelper.isVisible(getHeight(), getPosition(), deviceAngle);
        if (type.equals("DesktopDevice")) {
            return false;
        }
        if (visible) {
            return true;
        }
        return inRange;
    }

    @Override
    public boolean SatellitesInRange(Angle satelliteAngle, double satelliteHeight, double satelliteRange, String id) {
        boolean visible = MathsHelper.isVisible(getHeight(), getPosition(), satelliteHeight, satelliteAngle);
        if (getId().equals(id)) {
            return false;
        } else if (visible) {
            return true;
        }
        return inRange;
    }
}
