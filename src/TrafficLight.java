public class TrafficLight {
    private String id;
    private String state;
    private int[] location;
    private int road;

    public TrafficLight(String id) {
        this.id = id;
        state = "green";
        location = new int[2];
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String printLocation() {
        return location[0] + "," + location[1];
    }

    public void setLocation(int[] location) {
        this.location = location;
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

    public int[] getLocation() {
        return location;
    }
}
