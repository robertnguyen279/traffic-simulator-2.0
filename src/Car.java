public class Car {
    String id; // unique identifier
    float length = 1f; // number of segments occupied, more for graphical representation, 1 for ease of prototype.
    private int speed = 0; //segments moved per turn
    private int[] location; // current segment
    private int road; // current road object
    private float breadth = length * 0.5f;

    public Car() {
        id = "car_000";
        location = new int[2];
    }

    public Car(String id) {
        this.id = id;
        location = new int[2];
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

    public String printLocation() {
        return location[0] + "," + location[1];
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public int[] getLocation() {
        return location;
    }

    public int getRoad() {
        return road;
    }

    public void setRoad(int road) {
        this.road = road;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

