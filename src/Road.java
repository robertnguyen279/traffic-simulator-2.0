
public class Road {
    private String id;
    private int speedLimit;
    private int length;
    private int[] startLocation;
    private int[] endLocation;

    public Road(String id, int speedLimit, int length, int[] startLocation) {
        this.id = id;
        this.speedLimit = 1;
        this.length = length;
        this.startLocation = startLocation;
        endLocation = new int[]{this.length + this.startLocation[0], 0}; //only works for horizontal roads
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

    public int[] getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(int[] startLocation) {
        this.startLocation = startLocation;
    }

    public int[] getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(int[] endLocation) {
        this.endLocation = endLocation;
    }
}
