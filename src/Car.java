import java.awt.*;
import java.util.Random;

public class Car {
    private static final int STOPPED = 0; //car speed is 0m/s
    private static final int NEXT_ROAD_INDEX = 0;
    private static final int START_POSITION = 0;
    String id; // unique identifier
    static int length; // number of segments occupied, 1 for ease of prototype.
    private static int breadth;
    private int speed; //segments moved per turn
    private int position; // position on current road
    private Road currentRoad; // current Road object
    private Color colour;


    public Car(String id, Road currentRoad) {
        this.id = "car_" + id;
        this.currentRoad = currentRoad;
        length = 4;
        breadth = length / 2;
        speed = 0;
        position = 0;
        this.currentRoad.getCarsOnRoad().add(this); //add this car to the road its on.
        colour = randomColour();
    }

    public Car() {
        id = "000";
        length = 4;
        breadth = length / 2;
        speed = 0;
        position = 0;
        colour = randomColour();
    }

    public void move() {
        this.speed = this.currentRoad.getSpeedLimit(); //set speed limit to that of currentRoad
        if (!this.currentRoad.getLightsOnRoad().isEmpty() && position == this.currentRoad.getLightsOnRoad().get(0).getPosition() && this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")) {
            this.speed = STOPPED;
        } else {
            this.speed = this.currentRoad.getSpeedLimit();
            if (this.currentRoad.getLength() == this.getPosition() && !this.currentRoad.getConnectedRoads().isEmpty()) {
                this.currentRoad.getCarsOnRoad().remove(this);
                this.currentRoad = this.currentRoad.getConnectedRoads().get(NEXT_ROAD_INDEX);
                this.currentRoad.getCarsOnRoad().add(this);
                this.position = START_POSITION;
            } else if (currentRoad.getLength() > position) {
                this.position = (this.position + this.speed);
            } else {
                this.speed = STOPPED;
            }
        }
    }

    public void printCarStatus() {
        System.out.printf("%s going:%dm/s on %s at position:%s%n", this.getId(), this.getSpeed(), this.getCurrentRoad().
                getId(), this.getPosition());
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        Car.length = length;
    }

    public int getBreadth() {
        return breadth;
    }

    public void setBreadth(int breadth) {
        Car.breadth = breadth;
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

    public void draw(Graphics g, int scale) {
        int[] startLocation = getCurrentRoad().getStartLocation();
        int x = (getPosition() + startLocation[0]) * scale;
        int y = (startLocation[1] * scale) + scale;
        int width = getLength() * scale;
        int height = getBreadth() * scale;
        g.setColor(colour);
        g.fillRect(x, y, -width, height);
    }

    public Color randomColour() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, g);
    }
}

