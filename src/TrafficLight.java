public class TrafficLight {
    private static final double CHANGE_GREEN = 0.5;
    private static final String GREEN = "green";
    private static final String RED = "red";
    private String id;
    private String state;
    private int position;
    private Road road;

    public TrafficLight(String id, Road road) {
        this.id = "light_" + id;
        state = "green";
        this.road = road;
        position = this.road.getLength(); // always places the traffic light at the end of the road.
    }

    public void operate() {
        double probability = Math.random();
        if (probability > CHANGE_GREEN) {
            this.setState(GREEN);
        } else {
            this.setState(RED);
        }
    }

    public void printLight() {
        System.out.printf("%s is:%s on %s at position:%s%n", this.getId(), this.getState(), this.getRoad().getId(), this.getPosition());
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
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
}
