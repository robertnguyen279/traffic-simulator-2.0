import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SimulationPanel extends JPanel {
    private static final int SCALE = 10;
    private ArrayList<Road> roads;
    private ArrayList<Car> cars;
    private ArrayList<TrafficLight> lights;

    public void simulate() {
        Random random = new Random();
        int time = 0;
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
            repaint();
        }
    }

    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
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

        for (Car car : cars
        ) {
            int x = car.getPosition() * SCALE;
            int y = (car.getCurrentRoad().getWidth() / 4) * (SCALE / 3);
            int width = car.getLength() * SCALE;
            int height = car.getBreadth() * SCALE;
            g.setColor(Color.red);
            g.fillRect(x, y, width, height);
        }
    }
}
