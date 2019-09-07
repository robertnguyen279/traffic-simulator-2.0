import java.util.ArrayList;

public class Road {
    private String id;
    private int speedLimit;
    private int length;
    private int[] startLocation;
    private int[] endLocation;
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<TrafficLight> lights = new ArrayList<>();
    private ArrayList<Road> connectedRoads = new ArrayList<>();

    public Road(String id, int speedLimit, int length, int[] startLocation) {
        this.id = "road_" + id;
        this.speedLimit = 1;
        this.length = length;
        this.startLocation = startLocation;
        endLocation = new int[]{this.length + this.startLocation[0], 0}; //only works for horizontal roads;
    }

    public void createCars(int carSpawns) {
        for (int i = 0; i < carSpawns; i++)
            cars.add(new Car(Integer.toString(i), this));
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String printStartLocation() {
        return startLocation[0] + "," + startLocation[1];
    }

    public void setStartLocation(int[] startLocation) {
        this.startLocation = startLocation;
    }

    public String printEndLocation() {
        endLocation = new int[]{this.length + this.startLocation[0], 0}; //only works for horizontal roads;
        return endLocation[0] + "," + endLocation[1];
    }

    public void setEndLocation(int[] endLocation) {
        this.endLocation = endLocation;
    }

    public int[] getStartLocation() {
        return startLocation;
    }

    public int[] getEndLocation() {
        return endLocation;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public ArrayList<TrafficLight> getLights() {
        return lights;
    }

    public void setLights(ArrayList<TrafficLight> lights) {
        this.lights = lights;
    }

    public ArrayList<Road> getConnectedRoads() {
        return connectedRoads;
    }

    public void setConnectedRoads(ArrayList<Road> connectedRoads) {
        this.connectedRoads = connectedRoads;
    }
}
