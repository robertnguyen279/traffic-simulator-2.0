public class Motorbike extends Vehicle {

    public Motorbike(String id) {
        this.id = ("bike_" + id);
        length = super.getLength() / 2;
    }

}
