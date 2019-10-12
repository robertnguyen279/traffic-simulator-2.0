public class Bus extends Vehicle {

    public Bus(String id) {
        this.id = ("bus_" + id);
        length = super.getLength() * 3;
    }

}
