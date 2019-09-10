public class Car {
    private static final int STOPPED = 0; //car speed is 0m/s
    private static final int NEXT_ROAD_INDEX = 0;
    private static final int START_POSITION = 1;
    String id; // unique identifier
    static float length; // number of segments occupied, 1 for ease of prototype.
    private static float breadth;
    private int speed; //segments moved per turn
    private int position; // position on current road
    private Road currentRoad; // current Road object


    public Car(String id, Road currentRoad) {
        this.id = "car_" + id;
        this.currentRoad = currentRoad;
        length = 1f;
        breadth = length * 0.5f;
        speed = 0;
        position = 1;
    }

    public Car() {
        id = "000";
        length = 1f;
        breadth = length * 0.5f;
        speed = 0;
        position = 1;
    }

    public void move() {
        this.setSpeed(this.currentRoad.getSpeedLimit()); //set speed limit to that of currentRoad
        if (!this.currentRoad.getLightsOnRoad().isEmpty() && this.position == this.currentRoad.getLightsOnRoad().get(0).getPosition() && this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")) {
            this.setSpeed(STOPPED);
        } else {
            this.setSpeed(this.currentRoad.getSpeedLimit());
            if (this.currentRoad.getLength() == this.getPosition() && !this.currentRoad.getConnectedRoads().isEmpty()) {
                this.setCurrentRoad(this.currentRoad.getConnectedRoads().get(NEXT_ROAD_INDEX));
                this.setPosition(START_POSITION);
            } else if (this.currentRoad.getLength() > this.getPosition()) {
                this.setPosition(this.getPosition() + this.getSpeed());
            } else {
                this.setSpeed(STOPPED);
            }
        }
    }

    public void printCarStatus() {
        System.out.printf("%s going:%dm/s on %s at position:%s%n", this.getId(), this.getSpeed(), this.getCurrentRoad().
                getId(), this.getPosition());
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        Car.length = length;
    }

    public float getBreadth() {
        return breadth;
    }

    public void setBreadth(float breadth) {
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

}

