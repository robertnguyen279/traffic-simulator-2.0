public class Car {
    String id; // unique identifier
    float length = 1f; // number of segments occupied, more for graphical representation, 1 for ease of prototype.
    private int speed = 0; //segments moved per turn
    private int position; // current segment
    private Road road; // current road object
    private float breadth = length * 0.5f;

    public Car(String id, Road road) {
        this.id = "car_" + id;
        this.road = road;
        position = 0;
    }

    public void move() {
        this.setSpeed(this.road.getSpeedLimit()); //set speed limit to that of current road
        if (!this.road.getLights().isEmpty() && this.position == this.road.getLights().get(0).getPosition() && this.road.getLights().get(0).getState().equals("red")) {
            this.setSpeed(0);
        } else {
            this.setSpeed(this.road.getSpeedLimit());
            if (this.road.getLength() == this.getPosition() && !this.road.getConnectedRoads().isEmpty()) {
                this.setRoad(this.road.getConnectedRoads().get(0));
                this.setPosition(0);
            } else if (this.road.getLength() > this.getPosition()) {
                this.setPosition(this.getPosition() + this.getSpeed());
            } else {
                this.setSpeed(0);
            }
        }
    }

    public void printCar() {
        System.out.printf("%s going:%dm/s on %s at position:%s%n", this.getId(), this.getSpeed(), this.getRoad().getId(), this.getPosition());
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getBreadth() {
        return breadth;
    }

    public void setBreadth(float breadth) {
        this.breadth = breadth;
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

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

