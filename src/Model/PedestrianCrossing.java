package Model;

import java.awt.*;

public class PedestrianCrossing {
    private String id;
    private int[] location;
    private int walkedCycleTime;
    private int countdown;
    private final Road roadAttachedTo;
    private String status;

    public PedestrianCrossing(String id, int walkedCycleTime, Road roadAttachedTo) {
        this.id = "pedestrian_crossing_" + id;
        this.roadAttachedTo = roadAttachedTo;
        this.walkedCycleTime = walkedCycleTime;
        countdown = walkedCycleTime;
        this.location = new int[]{(roadAttachedTo.getEndLocation()[0] - roadAttachedTo.getStartLocation()[0]) / 2, (roadAttachedTo.getEndLocation()[1] - roadAttachedTo.getStartLocation()[1]) / 2}; // Always place pedestrian crossing at middle of the road.
        status = "free";
        this.roadAttachedTo.setPedestrianCrossing(this);
    }

    public void launch() {
        if (countdown == 0) {
            setStatus(status.equals("free") ? "walked" : "free");
            countdown = walkedCycleTime;
        }
        countdown--;
    }

    public void printStatus() {
        System.out.printf("%s is %s left in %s on %s at position (%s, %s).%n", this.getId(), this.countdown ,this.status, this.getRoadAttachedTo().getId(), this.location[0], this.location[1]);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public int getWalkedCycleTime() {
        return walkedCycleTime;
    }

    public void setWalkedCycleTime(int walkedCycleTime) {
        this.walkedCycleTime = walkedCycleTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Road getRoadAttachedTo() {
        return roadAttachedTo;
    }

    public void draw(Graphics g, int scale) {
        if (roadAttachedTo.getOrientation() == Road.Orientation.HORIZONTAL) {
            switch (status) {
                case "walked":
                    g.setColor(Color.red);
                    break;
                case "free":
                    g.setColor(Color.green);
            }
            int[] startLocation = getRoadAttachedTo().getStartLocation();
            int x = (getLocation()[0] + startLocation[0]) * scale;
            int y = startLocation[1] * scale;
            int height = (getRoadAttachedTo().getWidth() / 2) * scale;
            g.fillRect(x, y, scale, height);
        }
        if (roadAttachedTo.getOrientation() == Road.Orientation.VERTICAL) {
            switch (status) {
                case "walked":
                    g.setColor(Color.red);
                    break;
                case "free":
                    g.setColor(Color.green);
            }
            int[] startLocation = getRoadAttachedTo().getStartLocation();
            int x = (startLocation[0] + (getRoadAttachedTo().getWidth() / 2)) * scale;
            int y = (getLocation()[0] + startLocation[1]) * scale;
            int width = (getRoadAttachedTo().getWidth() / 2) * scale;
            g.fillRect(x, y, width, scale);
        }
    }
}

