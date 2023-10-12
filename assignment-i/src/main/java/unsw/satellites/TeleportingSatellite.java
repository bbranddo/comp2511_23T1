package unsw.satellites;

import unsw.utils.Angle;
import unsw.utils.MathsHelper;

public class TeleportingSatellite extends satellites {
    private double velocity = 1000;
    private double range = 200000;
    private boolean isClockwise = false;
    private boolean inRange = false;

    public TeleportingSatellite(String id, String type, double height, Angle position) {
        super(id, type, height, position);
    }

    public double getVelocity() {
        return velocity;
    }

    public double getRange() {
        return range;
    }

    @Override
    public void move() {
        Angle position;
        if (!isClockwise) {
            position = getPosition().add(Angle.fromRadians(velocity / getHeight()));
        } else {
            position = getPosition().subtract(Angle.fromRadians(velocity / getHeight()));
        }
        System.out.println(position);
        System.out.println(isClockwise);
        int x = (int) position.toDegrees();
        System.out.println(x);
        if (x == 180) {
            setPosition(Angle.fromRadians(0));
            isClockwise = true;
        } else {
            setPosition(position);
            isClockwise = false;
        }
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
