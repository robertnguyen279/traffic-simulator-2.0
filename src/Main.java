import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //Simulation Window
        JFrame simulationWindow = new JFrame("Traffic Simulator");
        simulationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simulationWindow.setBounds(0, 0, 1600, 1024);


        simulationWindow.setLocationRelativeTo(null);
        simulationWindow.setVisible(true);

        // set values for user inputs for prototype.
        int roadSpawns = 2;
        int lightSpawns = 1;


        //Create objects:
        ArrayList<Road> roads = new ArrayList<>();
        for (int i = 0; i < roadSpawns; i++) {
            int lengthInput = Integer.parseInt(JOptionPane.showInputDialog("Please input length for road_" + i));
            int speedLimitInput = 1; // force speed limit to be 1 for prototype.
            roads.add(new Road(Integer.toString(i), speedLimitInput, lengthInput, new int[]{0, 0}));
        }

        int carSpawns = Integer.parseInt(JOptionPane.showInputDialog("Number of vehicles to spawn?"));
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < carSpawns; i++) {
            cars.add(new Car(Integer.toString(i), roads.get(0))); // all created cars will begin on road_0.
            cars.get(i).printCarStatus();
        }

        System.out.println("\nTraffic Lights;");
        ArrayList<TrafficLight> lights = new ArrayList<>();
        for (int i = 0; i < lightSpawns; i++) {
            lights.add(new TrafficLight(Integer.toString(i), roads.get(0))); // all created lights will begin on road_0.
            lights.get(i).printLightStatus();
        }
        System.out.println();


        // set locations and connections:
        System.out.println("Settings:");
        roads.get(1).setStartLocation(new int[]{roads.get(0).getLength() + 1, 0}); // place road_1 to a position at the end of road_0.
        roads.get(1).printRoadInfo();
        roads.get(0).getConnectedRoads().add(roads.get(1)); // connect road_0 to road_1
        System.out.println();


        //Simulation loop:
        System.out.println("Simulation:");
        Random random = new Random();
        int time = 0;
        System.out.print("Set time scale in milliseconds:");
        int speedOfSim = Integer.parseInt(JOptionPane.showInputDialog("Speed of simulation"));
        int carsFinished = 0;
        while (carsFinished < cars.size()) {
            for (TrafficLight light : lights) {
                for (Car car : cars
                ) {
                    car.printCarStatus();
                }
                light.operate(random.nextInt());
                light.printLightStatus();
            }
            for (Car car : cars) {
                car.move();
                car.printCarStatus();
                if (car.getCurrentRoad().getConnectedRoads().isEmpty() && (car.getSpeed() == 0)) {
                    carsFinished = carsFinished + 1;
                }
            }
            time = time + 1;
            System.out.println(time + " Seconds have passed.\n");
            try {
                Thread.sleep(speedOfSim); // set speed of simulation.
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
