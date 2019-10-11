import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Road> roads = new ArrayList<>();
        int roadSpawns = 2;
        for (int i = 0; i < roadSpawns; i++) {
//            int lengthInput = Integer.parseInt(JOptionPane.showInputDialog("Please input length for road_" + i));
            int lengthInput = 50;
            int speedLimitInput = 1; // force speed limit to be 1 for prototype.
            roads.add(new Road(Integer.toString(i), speedLimitInput, lengthInput, new int[]{0, 0}));
        }

        ArrayList<Car> cars = new ArrayList<>();
//        int carSpawns = Integer.parseInt(JOptionPane.showInputDialog("Number of vehicles to spawn?"));
        int carSpawns = 1;
        for (int i = 0; i < carSpawns; i++) {
            cars.add(new Car(Integer.toString(i), roads.get(0))); // all created cars will begin on road_0.
            cars.get(i).printCarStatus();
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

        // Simulation Window
        JFrame mainWindow = new JFrame("Traffic Simulator");
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setBounds(0, 0, 1600, 1024);

        JMenuBar menuBar = new JMenuBar();
        mainWindow.add(menuBar, BorderLayout.NORTH);

        JMenu optionsMenu = new JMenu("Options");
        menuBar.add(optionsMenu);

        SimulationPanel simulationPanel = new SimulationPanel();
        simulationPanel.setRoads(roads);
        simulationPanel.setCars(cars);
        simulationPanel.setLights(lights);
        mainWindow.add(simulationPanel, BorderLayout.CENTER);

        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
        simulationPanel.simulate(Integer.parseInt(JOptionPane.showInputDialog("Time Scale?")));


        //Simulation loop:
        System.out.println("Simulation:");

    }
}
