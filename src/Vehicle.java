import java.awt.*;
import java.util.Random;

public abstract class Vehicle {
    static final int STOPPED = 0;
    private static final int NEXT_ROAD_INDEX = 0;
    private static final int START_POSITION = 0;
    static int length; // number of segments occupied
    static int breadth;
    String id; // unique identifier
    int speed; //segments moved per turn
    Road currentRoad; // current Road object
    private int position; // position on current road
    private Color colour;
    private Random random = new Random();

    public Vehicle(Road currentRoad) {
        id = "000";
        length = 4;
        breadth = 2;
        speed = 0;
        position = 0;
        this.currentRoad = currentRoad;
        currentRoad.getVehiclesOnRoad().add(this); //add this vehicle to the road its on.
        colour = randomColour();
    }

    public Vehicle() {
        id = "000";
        length = 0;
        breadth = 0;
        speed = 0;
        position = 0;
    }

    public void move() {
        //vehicle in front check:
        for (Vehicle nextVehicle : currentRoad.getVehiclesOnRoad()) {
            if (!nextVehicle.equals(this) && nextVehicle.position > this.position && nextVehicle.position <= this.position + (length * 2)) {
                speed = STOPPED;
            } else {
                speed = currentRoad.getSpeedLimit();
            }
        }
        //red light check:
        if (speed == STOPPED) {

        } else if (!currentRoad.getLightsOnRoad().isEmpty() && position + length + speed >= currentRoad.getLightsOnRoad().get(0).getPosition() && this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")) {
            speed = STOPPED;
        } else {
            speed = currentRoad.getSpeedLimit();
            if (currentRoad.getLength() <= position + length && !currentRoad.getConnectedRoads().isEmpty()) {
                currentRoad.getVehiclesOnRoad().remove(this);
                currentRoad = currentRoad.getConnectedRoads().get(NEXT_ROAD_INDEX);
                currentRoad.getVehiclesOnRoad().add(this);
                position = START_POSITION;
            } else if (currentRoad.getLength() >= position + length + speed) {
                position = (position + speed);
            } else {
                speed = STOPPED;
            }
        }

    }


    public void printStatus() {
        System.out.printf("%s going:%dm/s on %s at position:%s%n", this.getId(), this.getSpeed(), this.getCurrentRoad().
                getId(), this.getPosition());
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        Vehicle.length = length;
    }

    public int getBreadth() {
        return breadth;
    }

    public void setBreadth(int breadth) {
        Vehicle.breadth = breadth;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Road getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Road currentRoad) {
        this.currentRoad = currentRoad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Color getColour() {
        return colour;
    }

    public void draw(Graphics g, int scale) {
        int[] startLocation = getCurrentRoad().getStartLocation();
        int x = (getPosition() + startLocation[0]) * scale;
        int y = (startLocation[1] * scale) + scale;
        int width = getLength() * scale;
        int height = getBreadth() * scale;
        g.setColor(colour);
        g.fillRect(x, y, width, height);
    }

    public Color randomColour() {
        int r = random.nextInt(245 + 1) + 10;
        int g = random.nextInt(245 + 1) + 10;
        int b = random.nextInt(245 + 1) + 10;
        return new Color(r, g, b);
    }
}

