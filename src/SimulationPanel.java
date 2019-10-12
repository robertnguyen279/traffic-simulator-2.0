import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SimulationPanel extends JPanel {
    private static final int SCALE = 6;
    private ArrayList<Road> roads;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<TrafficLight> lights;
    private Timer timer;
    private int carsFinished = 0;

    public void simulate(int speed) {
        ArrayList<Road> roads = new ArrayList<>();
        int roadSpawns = 2;
        for (int i = 0; i < roadSpawns; i++) {
//            int lengthInput = Integer.parseInt(JOptionPane.showInputDialog("Please input length for road_" + i));
            int lengthInput = 50;
            int speedLimitInput = 1; // force speed limit to be 1 for prototype.
            roads.add(new Road(Integer.toString(i), speedLimitInput, lengthInput, new int[]{0, 0}));
        }

        ArrayList<Vehicle> vehicles = new ArrayList<>();
//        int vehicleSpawns = Integer.parseInt(JOptionPane.showInputDialog("Number of vehicles to spawn?"));
        int vehicleSpawns = 1;
        for (int i = 0; i < vehicleSpawns; i++) {
            vehicles.add(new Car(Integer.toString(i), roads.get(0))); // all created vehicles will begin on road_0.
            vehicles.get(i).printStatus();
        }

        ArrayList<TrafficLight> lights = new ArrayList<>();
        int lightSpawns = 1;
        for (int i = 0; i < lightSpawns; i++) {
            lights.add(new TrafficLight(Integer.toString(i), roads.get(0))); // all created lights will begin on road_0.
            lights.get(i).printLightStatus();
        }

        // set locations and connections:
        System.out.println("Settings:");
        roads.get(1).setStartLocation(new int[]{roads.get(0).getLength(), 0}); // place road_1 to a position at the end of road_0.
        roads.get(1).printRoadInfo();
        roads.get(0).getConnectedRoads().add(roads.get(1)); // connect road_0 to road_1
        System.out.println();

        setRoads(roads);
        setVehicles(vehicles);
        setLights(lights);

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
