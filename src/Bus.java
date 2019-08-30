public class Bus extends Car {

    public Bus() {
        id = "bus_000";
        length = new Car().getLength() * 3;
    }

    public Bus(String id) {
        super(id);
        length = new Car().getLength() * 3;
    }

}
