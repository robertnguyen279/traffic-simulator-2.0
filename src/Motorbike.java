public class Motorbike extends Car {

    public Motorbike() {
        id = "bike_000";
        length = new Car().getLength() * 0.5f;
    }

    public Motorbike(String id) {
        super(id);
        length = new Car().getLength() * 0.5f;
    }

}
