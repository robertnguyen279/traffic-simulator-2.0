public class Bus extends Vehicle {

    public Bus(String id, Road currentRoad) {
        super(currentRoad);
        this.id = ("bus_" + id);
        length = super.getLength() * 3;
    }

}
