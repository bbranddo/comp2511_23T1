package unsw.satellites;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import unsw.utils.Angle;

public abstract class satellites {
    private String id;
    private double height;
    private Angle position;
    private String type;
    private File file;
    private boolean inRange;
    private double range;

    public satellites(String id, String type, double height, Angle positon, File file) {
        this.id = id;
        this.type = type;
        this.height = height;
        this.position = positon;
        this.file = file;
    }

    public satellites(String id, String type, double height, Angle positon) {
        this.id = id;
        this.type = type;
        this.height = height;
        this.position = positon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Angle getPosition() {
        return position;
    }

    public void setPosition(Angle position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return file.getName();
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContent() {
        Path path = file.toPath();
        String content;
        try {
            content = Files.readString(path);
            return content;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void move() {
    }

    public double getRange() {
        return range;
    }

    public boolean DevicesInRange(String deviceId, Angle deviceAngle, double deviceRange, String id) {
        return inRange;
    }

    public boolean SatellitesInRange(Angle satelliteAngle, double satelliteHeight, double satelliteRange, String id) {
        return inRange;
    }
}
