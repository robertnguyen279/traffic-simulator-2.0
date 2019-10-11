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
        timer = new Timer(speed / 60, e -> {
            if (carsFinished == cars.size()) return;
            Random random = new Random();
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
            car.draw(g, SCALE);
        }

        for (TrafficLight light : lights
        ) {
            light.draw(g, SCALE);
        }
    }
}
