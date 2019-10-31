import java.awt.*;
import java.util.Random;

public class TrafficLight {
    private static final double CHANGE_GREEN = 0.8; //
    private static final String GREEN = "green";
    private static final String RED = "red";
    private String id;
    private String state;
    private int position;
    private Road roadAttachedTo;

    public TrafficLight(String id, Road road) {
        this.id = "light_" + id;
        state = RED;
        this.roadAttachedTo = road;
        position = this.roadAttachedTo.getLength(); // always places the traffic light at the end of the roadAttachedTo.
        this.roadAttachedTo.getLightsOnRoad().add(this); // adds this light to the road it belongs to.
    }

    public void operate(int seed) {
        Random random = new Random(seed);
        double probability = random.nextDouble();
        if (probability > CHANGE && !getRoadAttachedTo().getVehiclesOnRoad().isEmpty()) {

            setState(RED);
        } else {
            setState(GREEN);
        }
    }

    public void printLightStatus() {
        System.out.printf("%s is:%s on %s at position:%s%n", getId(), getState(), this.getRoadAttachedTo().getId(), this.getPosition());
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Road getRoadAttachedTo() {
        return roadAttachedTo;
    }

    public void setRoadAttachedTo(Road roadAttachedTo) {
        this.roadAttachedTo = roadAttachedTo;
    }

    public int getPosition() {
        return position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void draw(Graphics g, int scale) {
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
        int width = scale;
        int height = (getRoadAttachedTo().getWidth() / 2) * scale;
        g.fillRect(x, y, width, height);
    }
}
