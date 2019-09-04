import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusTest {
    Car car1 = new Car();
    Bus bus1 = new Bus();
    Motorbike bike1 = new Motorbike();

    @Test
    void testCar() {
        assertEquals("car_000", car1.getId());
        assertEquals(4.5, car1.getLength());
        assertEquals(2.25, car1.getBreadth());
    }

    @Test
    void testBus() {
        assertEquals("bus_000", bus1.getId());
        assertEquals(13.5, bus1.getLength());
        assertEquals(2.25, bus1.getBreadth());
    }

    @Test
    void testMotorbike() {
        assertEquals("bike_000", bike1.getId());
        assertEquals(2.25, bike1.getLength());
        assertEquals(2.25, bike1.getBreadth());
    }


}