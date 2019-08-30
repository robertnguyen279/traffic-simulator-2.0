public class TrafficLight {
    private String id;
    private String state;
    private int[] location;
    private String road;

    public TrafficLight(String id, int[] location, String road) {
        this.id = id;
        this.location = location;
        this.road = road;
        state = "green";
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
