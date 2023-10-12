package unsw.blackout;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import unsw.blackout.devices.DesktopDevice;
import unsw.blackout.devices.HandheldDevice;
import unsw.blackout.devices.LaptopDevice;
import unsw.blackout.devices.device;
import unsw.response.models.EntityInfoResponse;
import unsw.response.models.FileInfoResponse;
import unsw.satellites.RelaySatellite;
import unsw.satellites.StandardSatellite;
import unsw.satellites.TeleportingSatellite;
import unsw.satellites.satellites;
import unsw.utils.Angle;
import static unsw.utils.MathsHelper.RADIUS_OF_JUPITER;

public class BlackoutController {
    private ArrayList<satellites> satellites = new ArrayList<satellites>();
    private ArrayList<device> devices = new ArrayList<device>();

    public void createDevice(String deviceId, String type, Angle position) {
        // TODO: Task 1a)
        if (type.equals("DesktopDevice")) {
            DesktopDevice newDevice = new DesktopDevice(deviceId, type, position);
            devices.add(newDevice);
        }
        if (type.equals("HandheldDevice")) {
            HandheldDevice newDevice = new HandheldDevice(deviceId, type, position);
            devices.add(newDevice);
        }
        if (type.equals("LaptopDevice")) {
            LaptopDevice newDevice = new LaptopDevice(deviceId, type, position);
            devices.add(newDevice);
        }
    }

    public device getDeviceId(String id) {
        for (device d : devices) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        return null;
    }

    public void removeDevice(String deviceId) {
        // TODO: Task 1b)
        devices.remove(getDeviceId(deviceId));
    }

    public void createSatellite(String satelliteId, String type, double height, Angle position) {
        // TODO: Task 1c)
        if (type.equals("StandardSatellite")) {
            StandardSatellite newDevice = new StandardSatellite(satelliteId, type, height, position);
            satellites.add(newDevice);
        }
        if (type.equals("RelaySatellite")) {
            RelaySatellite newDevice = new RelaySatellite(satelliteId, type, height, position);
            satellites.add(newDevice);
        }
        if (type.equals("TeleportingSatellite")) {
            TeleportingSatellite newDevice = new TeleportingSatellite(satelliteId, type, height, position);
            satellites.add(newDevice);
        }
    }

    public satellites getSatelliteId(String id) {
        for (satellites s : satellites) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public void removeSatellite(String satelliteId) {
        // TODO: Task 1d)
        satellites.remove(getSatelliteId(satelliteId));
    }

    public List<String> listDeviceIds() {
        // TODO: Task 1e)
        List<String> deviceIDs = new ArrayList<String>();
        for (device d : devices) {
            deviceIDs.add(d.getId());
        }
        return deviceIDs;
    }

    public List<String> listSatelliteIds() {
        // TODO: Task 1f)
        List<String> satelliteIDs = new ArrayList<String>();
        for (satellites s : satellites) {
            satelliteIDs.add(s.getId());
        }
        return satelliteIDs;
    }

    public void addFileToDevice(String deviceId, String filename, String content) {
        // TODO: Task 1g)
        File file = new File(filename);

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
            for (device d : devices) {
                if (d.getId().equals(deviceId)) {
                    d.setFile(file);
                    d.addToFiles(file);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public EntityInfoResponse getInfo(String id) {
        // TODO: Task 1h)
        for (device d : devices) {
            if (d.getId().equals(id) && d.getFile() != null) {
                Map<String, FileInfoResponse> map = new HashMap<>();
                map.put(d.getFileName(),
                        new FileInfoResponse(d.getFileName(), d.getFileContent(), d.getFileContent().length(), true));
                return new EntityInfoResponse(d.getId(), d.getPosition(), RADIUS_OF_JUPITER, d.getType(), map);
            } else if (d.getId().equals(id)) {
                return new EntityInfoResponse(d.getId(), d.getPosition(), RADIUS_OF_JUPITER, d.getType());
            }
        }

        for (satellites s : satellites) {
            if (s.getId().equals(id) && s.getFile() != null) {
                Map<String, FileInfoResponse> map = new HashMap<>();
                map.put(s.getFileName(),
                        new FileInfoResponse(s.getFileName(), s.getFileContent(), s.getFileContent().length(), true));
                return new EntityInfoResponse(s.getId(), s.getPosition(), s.getHeight(), s.getType(), map);
            } else if (s.getId().equals(id)) {
                return new EntityInfoResponse(s.getId(), s.getPosition(), s.getHeight(), s.getType());
            }
        }
        return null;
    }

    public void simulate() {
        // TODO: Task 2a)
        for (satellites s : satellites) {
            s.move();
        }
    }

    /**
     * Simulate for the specified number of minutes. You shouldn't need to modify
     * this function.
     */
    public void simulate(int numberOfMinutes) {
        for (int i = 0; i < numberOfMinutes; i++) {
            simulate();
        }
    }

    public List<String> communicableEntitiesInRange(String id) {
        // TODO: Task 2 b)
        ArrayList<String> connected = new ArrayList<>();

        for (satellites s : satellites) {
            if (s.getId().equals(id)) {
                for (device d : devices) {
                    if (s.DevicesInRange(d.getId(), d.getPosition(), d.getRange(), d.getType())) {
                        connected.add(d.getId());
                    }
                }
                for (satellites e : satellites) {
                    if (s.SatellitesInRange(e.getPosition(), e.getHeight(), e.getRange(), e.getId())) {
                        connected.add(e.getId());
                    }
                }
            }
        }
        for (device d : devices) {
            if (d.getId().equals(id)) {
                for (satellites e : satellites) {
                    if (d.SatellitesInRange(e.getPosition(), e.getHeight(), e.getRange())) {
                        connected.add(e.getId());
                    }
                }
            }
        }
        return connected;
    }

    public void sendFile(String fileName, String fromId, String toId) throws FileTransferException {
        // TODO: Task 2 c)
    }

    public void createDevice(String deviceId, String type, Angle position, boolean isMoving) {
        createDevice(deviceId, type, position);
        // TODO: Task 3
    }

    public void createSlope(int startAngle, int endAngle, int gradient) {
        // TODO: Task 3
        // If you are not completing Task 3 you can leave this method blank :)
    }
}
