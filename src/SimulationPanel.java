import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SimulationPanel extends JPanel {

    public enum State {
        STOPPED, RUNNING, FINISHED
    }

    static int cycle = 0;
    private State state = State.STOPPED;
    private int scale;
    private ArrayList<Road> roads;
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<TrafficLight> lights;
    private Timer timer;
    private Boolean stop = true;
    private Random random = new Random();
    private int spawns = 2;
    private int numberOfCycles = 20;


    public void loadMap(ArrayList<Road> roads, ArrayList<TrafficLight> lights) {
        this.roads = roads;
        this.lights = lights;
    }

    public void setVehicleSpawn(int spawns) {
        this.spawns = spawns - 1;
        createVehicle();
    }

    public void setVehicleSpawnRate(int rate) {
        this.numberOfCycles = rate;
    }

    public void createVehicle() {
        int randomVehicle = random.nextInt(3);
        switch (randomVehicle) {
            case 0:
                Car newCar = new Car(Integer.toString(1), roads.get(0));
                vehicles.add(newCar);
                newCar.setPosition(-newCar.getLength());
                break;
            case 1:
                Bus newBus = new Bus(Integer.toString(1), roads.get(0));
                vehicles.add(newBus);
                newBus.setPosition(-newBus.getLength());
                break;
            case 2:
                Motorbike newBike = new Motorbike(Integer.toString(1), roads.get(0));
                vehicles.add(newBike);
                newBike.setPosition(-newBike.getLength());
                break;
        }
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void simulate(int updateRate) {
        setLayout(new BorderLayout());
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 0));
        infoPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        JLabel vehicleLabel = new JLabel("Vehicles: ");
        infoPanel.add(vehicleLabel);
        JLabel averageSpeedLabel = new JLabel("Average Speed: ");
        infoPanel.add(averageSpeedLabel);
        JLabel stateLabel = new JLabel("State: " + state);
        infoPanel.add(stateLabel);
        add(infoPanel, BorderLayout.SOUTH);

        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(updateRate / 60, e -> {
            cycle++;
            if (vehicles.size() == 0) {
                state = State.FINISHED;
            } else if (stop) {
                state = State.STOPPED;
            } else {
                state = State.RUNNING;
            }
            stateLabel.setText("State: " + state);
            vehicleLabel.setText("Vehicles: " + vehicles.size());
            averageSpeedLabel.setText("Average Speed:" + getAverageSpeed());
            if (vehicles.size() == 0 || stop)
                return;
            for (TrafficLight light : lights) {
                for (Vehicle vehicle : vehicles
                ) {
                    vehicle.printStatus();
                }
                light.operate(random.nextInt());
                light.printLightStatus();
            }

            for (Iterator<Vehicle> iterator = vehicles.iterator(); iterator.hasNext(); ) {
                Vehicle vehicle = iterator.next();
                vehicle.move();
                vehicle.printStatus();
                if (vehicle.getCurrentRoad().getConnectedRoads().isEmpty() && (vehicle.getSpeed() == 0)) {
                    iterator.remove();
                }
            }

            System.out.println(cycle);
            if (cycle % numberOfCycles == 0 && vehicles.size() < spawns) {
                createVehicle();
                System.out.println(vehicles.size());
            }

            repaint();
        });
        timer.start();

    }

    public Boolean getStopSim() {
        return stop;
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
            road.draw(g, scale);
        }

        if (!vehicles.isEmpty()) {
            for (Vehicle vehicle : vehicles
            ) {
                vehicle.draw(g, scale);
            }
        }

        for (TrafficLight light : lights
        ) {
            light.draw(g, scale);
        }
    }

    public String getAverageSpeed() {
        int totalSpeed = 0;
        for (Vehicle vehicle : vehicles) {
            totalSpeed = totalSpeed + vehicle.getSpeed();
        }
        if (!vehicles.isEmpty()) {
            int average = totalSpeed / vehicles.size();
            return average * 3.6 + "km/h";
        } else {
            return "0";
        }
    }

    public void setStopSim(Boolean stop) {
        this.stop = stop;
    }


}
