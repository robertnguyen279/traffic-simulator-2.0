import java.awt.*;
import java.util.ArrayList;

public class Road {

    private enum Orientation {
        HORIZONATAL, VERTICAL
    }

    private Orientation orientation;
    private String id;
    private int speedLimit;
    private int length;
    private int width;
    private int[] startLocation;
    private int[] endLocation;
    private ArrayList<Vehicle> vehiclesOnRoad = new ArrayList<>();
    private ArrayList<TrafficLight> lightsOnRoad = new ArrayList<>();
    private ArrayList<Road> connectedRoads = new ArrayList<>();


    public Road(String id, int speedLimit, int length, int[] startLocation) {
        this.id = "road_" + id;
        this.speedLimit = speedLimit;
        this.length = length;
        width = 8;
        this.startLocation = startLocation;
        this.endLocation = new int[]{this.length + this.startLocation[0], 0}; //only works for horizontal roads;
    }

    public void draw(Graphics g, int scale) {
        int[] startLocation = this.startLocation;
        int x = startLocation[0] * scale;
        int y = startLocation[1] * scale;
        int width = length * scale;
        int height = this.width * scale;
        g.setColor(Color.darkGray);
        g.fillRect(x, y, width, height);
        //Center Lines
        g.setColor(Color.white);
        g.fillRect(x, y + (height / 2) - scale / 6, width, scale / 6);
        g.fillRect(x, y + (height / 2) + scale / 6, width, scale / 6);
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public String printStartLocation() {
        return startLocation[0] + "," + startLocation[1];
    }

    public void setStartLocation(int[] startLocation) {
        this.startLocation = startLocation;
        this.endLocation = new int[]{this.length + this.startLocation[0], 0}; //only works for horizontal roads;
    }

    public String printEndLocation() {
        return endLocation[0] + "," + endLocation[1];
    }

    public void printRoadInfo() {
        System.out.printf("%s limit of:%dm/s is %dm long at location:%s to %s%n", this.getId(), this.getSpeedLimit(), this.getLength(), this.printStartLocation(), this.printEndLocation());
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

    public ArrayList<Vehicle> getVehiclesOnRoad() {
        return vehiclesOnRoad;
    }

    public void setVehiclesOnRoad(ArrayList<Vehicle> carsOnRoad) {
        this.vehiclesOnRoad = carsOnRoad;
    }

    public ArrayList<TrafficLight> getLightsOnRoad() {
        return lightsOnRoad;
    }

    public void setLightsOnRoad(ArrayList<TrafficLight> lightsOnRoad) {
        this.lightsOnRoad = lightsOnRoad;
    }

    public ArrayList<Road> getConnectedRoads() {
        return connectedRoads;
    }

    public void setConnectedRoads(ArrayList<Road> connectedRoads) {
        this.connectedRoads = connectedRoads;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
