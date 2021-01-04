package Model;

import java.awt.*;

public class TrafficLight {
    private static final double CHANGE = 0.4; // more often red
    private static final String GREEN = "green";
    private static final String RED = "red";
    private String id;
    private String state;
    private int position;
    private Road roadAttachedTo;
    private int signalCycleTime;
    private int countDown;

    public TrafficLight(String id, Road road, int signalCycleTime) {
        this.id = "light_" + id;
        state = RED;
        this.roadAttachedTo = road;
        position = this.roadAttachedTo.getLength(); // always places the traffic light at the end of the roadAttachedTo.
        this.roadAttachedTo.getLightsOnRoad().add(this); // adds this light to the road it belongs to.
    }

    public void operate() {
        if (countDown == 0) {
            countDown = signalCycleTime;
            setState(state.equals(RED) ? GREEN : RED);
        }
        countDown--;
    }

    public void printLightStatus() {
        System.out.printf("%s is:%s on %s at position:%s%n", getId(), getState(), this.getRoadAttachedTo().getId(), this.getPosition());
    }

    public String getState() {
        return state;
    }

    private void setState(String state) {
        this.state = state;
    }

    public Road getRoadAttachedTo() {
        return roadAttachedTo;
    }

    public int getPosition() {
        return position;
    }

    public String getId() {
        return id;
    }

    public void draw(Graphics g, int scale) {
        if (roadAttachedTo.getOrientation() == Road.Orientation.HORIZONTAL) {
            switch (state) {
                case "red":
                    g.setColor(Color.red);
                    break;
                case "green":
                    g.setColor(Color.green);
            }
            int[] startLocation = getRoadAttachedTo().getStartLocation();
            int x = (getPosition() + startLocation[0]) * scale;
            int y = startLocation[1] * scale;
            int height = (getRoadAttachedTo().getWidth() / 2) * scale;
            g.fillRect(x, y, scale, height);
        }
        if (roadAttachedTo.getOrientation() == Road.Orientation.VERTICAL) {
            switch (state) {
                case "red":
                    g.setColor(Color.red);
                    break;
                case "green":
                    g.setColor(Color.green);
            }
            int[] startLocation = getRoadAttachedTo().getStartLocation();
            int x = (startLocation[0] + (getRoadAttachedTo().getWidth() / 2)) * scale;
            int y = (getPosition() + startLocation[1]) * scale;
            int width = (getRoadAttachedTo().getWidth() / 2) * scale;
            g.fillRect(x, y, width, scale);
        }
    }
}
