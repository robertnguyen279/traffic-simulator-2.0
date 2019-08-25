import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusTest {
    Car car1 = new Car();
    Bus bus1 = new Bus();
    Motorbike bike1 = new Motorbike();

    @Test
    void testCarLength() {
        assertEquals(4.5, car1.length);
        assertEquals(2.25, car1.breadth);
    }

    @Test
    void testBusLength() {
        assertEquals(13.5, bus1.length);
        assertEquals(2.25, bus1.breadth);
    }

    @Test
    void testMotorbikeLength() {
        assertEquals(2.25, bike1.length);
        assertEquals(2.25, bike1.breadth);
    }

}