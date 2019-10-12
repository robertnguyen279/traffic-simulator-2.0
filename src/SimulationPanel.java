import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SimulationPanel extends JPanel {
    private static final int SCALE = 10;
    private ArrayList<Road> roads;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<TrafficLight> lights;
    private Timer timer;
    private int carsFinished = 0;

    public void simulate(int speed) {
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(speed / 60, e -> {
            if (carsFinished == vehicles.size()) return;
            Random random = new Random();
            for (TrafficLight light : lights) {
                for (Vehicle vehicle : vehicles
                ) {
                    vehicle.printStatus();
                }
                light.operate(random.nextInt());
                light.printLightStatus();
            }
            for (Vehicle vehicle : vehicles) {
                vehicle.move();
                vehicle.printStatus();
                if (vehicle.getCurrentRoad().getConnectedRoads().isEmpty() && (vehicle.getSpeed() == 0)) {
                    carsFinished = carsFinished + 1;
                }
            }
            repaint();
        });
        timer.start();

    }


    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setLights(ArrayList<TrafficLight> lights) {
        this.lights = lights;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Road road : roads
        ) {
            road.draw(g, SCALE);
        }

        for (Vehicle vehicle : vehicles
        ) {
            vehicle.draw(g, SCALE);
        }

        for (TrafficLight light : lights
        ) {
            light.draw(g, SCALE);
        }
    }
}
