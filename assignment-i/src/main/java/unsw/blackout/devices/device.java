package unsw.blackout.devices;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import unsw.utils.Angle;

public abstract class device {
    private String id;
    private Angle position;
    private String type;
    private File file;
    private ArrayList<File> files = new ArrayList<File>();
    private double range;
    private boolean inRange = false;

    public device(String id, String type, Angle position, ArrayList<File> files) {
        this.id = id;
        this.type = type;
        this.position = position;
        this.files = files;
    }

    public device(String id, String type, Angle position) {
        this.id = id;
        this.type = type;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void addToFiles(File file) {
        files.add(file);
    }

    public double getRange() {
        return range;
    }

    public boolean SatellitesInRange(Angle satelliteAngle, double satelliteHeight, double satelliteRange) {
        return inRange;
    }
}
