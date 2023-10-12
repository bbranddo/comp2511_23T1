package unsw.satellites;

import unsw.utils.Angle;
import unsw.utils.MathsHelper;

public class RelaySatellite extends satellites {
    private double velocity = 1500;
    private double range = 300000;
    private boolean isClockwise = true;
    private boolean inRange = false;

    public RelaySatellite(String id, String type, double height, Angle angle) {
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
        Angle position = getPosition();
        int x = (int) position.toDegrees();
        if (isClockwise) {
            position = getPosition().subtract(Angle.fromRadians(velocity / getHeight()));
        } else {
            position = getPosition().add(Angle.fromRadians(velocity / getHeight()));
        }

        if (x < 140) {
            isClockwise = false;
            position = getPosition().add(Angle.fromRadians(velocity / getHeight()));
        }
        if (x > 190) {
            isClockwise = true;
            position = getPosition().subtract(Angle.fromRadians(velocity / getHeight()));
        }
        setPosition(position);
    }

    @Override
    public boolean DevicesInRange(String deviceId, Angle deviceAngle, double diviceRange, String type) {
        boolean visible = MathsHelper.isVisible(getHeight(), getPosition(), deviceAngle);
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
