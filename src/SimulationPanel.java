import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SimulationPanel extends JPanel {
    private static final int SCALE = 10;
    private ArrayList<Road> roads;
    private ArrayList<Car> cars;
    private ArrayList<TrafficLight> lights;
    private Timer timer;
    private int carsFinished = 0;

    public void simulate(int speed) {
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(1000 / 60, e -> {
            if (carsFinished == cars.size()) return;
            Random random = new Random();
            int speedOfSim = speed;
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
            repaint();
        });
        timer.start();

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
            int[] startLocation = car.getCurrentRoad().getStartLocation();
            int x = (car.getPosition() + startLocation[0]) * SCALE;
            int y = (startLocation[1] * SCALE) + SCALE;
            int width = car.getLength() * SCALE;
            int height = car.getBreadth() * SCALE;
            g.setColor(Color.red);
            g.fillRect(x, y, -width, height);
        }

        for (TrafficLight light : lights
        ) {
            int[] startLocation = light.getRoadAttachedTo().getStartLocation();
            int x = (light.getPosition() + startLocation[0]) * SCALE;
            int y = startLocation[1] * SCALE;
            int width = SCALE;
            int height = (light.getRoadAttachedTo().getWidth() / 2) * SCALE;
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, width, height);
        }
    }
}
