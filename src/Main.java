import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final int REAL_TIME = 1000;
    public static final int XAXIS = 0; //Array location for obj x axis location (x,y)
    public static final int YAXIS = 1; //Array location for obj y axis location (x,y)
    private static int carSpawns;
    private static int roadSpawns;
    private static int lightSpawns;

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
//      int turn = 0; //Simulation ticks (seconds)

        //Get info needed to start sim:
        Scanner simController = new Scanner(System.in);
//        System.out.println("How many roads?");
//        main.setRoadSpawns(simController.nextInt());
//        System.out.println("How many cars?");
//        main.setCarSpawns(simController.nextInt());
//        System.out.println("How many traffic lights?");
//        main.setLightSpawns(simController.nextInt());

        // set values for user inputs for prototype.
        main.setCarSpawns(1);
        main.setLightSpawns(1);
        main.setRoadSpawns(2);

        //Create objects:
        System.out.println("Object Creation:\n---------------------");
        System.out.println("Roads:");
        ArrayList<Road> roads = new ArrayList<>();
        for (int i = 0; i < roadSpawns; i++) {
            System.out.println("Please input parameters for road_" + i + "...");
            System.out.print("Length:");
            int lengthInput = simController.nextInt();
            System.out.print("Speed limit:");
            int speedLimitInput = simController.nextInt();
            speedLimitInput = 1; // force speed limit to be 1 for prototype.
            System.out.print("Location:");
            //int speedLimitInput = simController.nextInt();
            roads.add(new Road(Integer.toString(i), speedLimitInput, lengthInput, new int[]{0, 0}));
        }
        System.out.println("\nRoads;");
        for (Road road : roads
        ) {
            road.printRoadInfo();
        }
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < carSpawns; i++) {
            cars.add(new Car(Integer.toString(i), roads.get(0))); // all created cars will begin on road_0.
            cars.get(i).printCarStatus();
        }

        ArrayList<TrafficLight> lights = new ArrayList<>();
        for (int i = 0; i < lightSpawns; i++) {
            lights.add(new TrafficLight(Integer.toString(i), roads.get(0))); // all created lights will begin on road_0.
            roads.get(0).getLightsOnRoad().add(lights.get(i)); // add light_(i) to road_0s traffic light array.
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
        int speedOfSim = simController.nextInt();
        for (Car car : cars
        ) {
            do {
                for (TrafficLight light : lights
                ) {
                    light.operate(random.nextInt());
                    light.printLightStatus();
                }
                car.move();
                car.printCarStatus();
                time = time + 1;
                System.out.println(time + " Seconds have passed.\n");
                Thread.sleep(speedOfSim); // set speed of simulation.
            } while (!car.getCurrentRoad().getConnectedRoads().isEmpty() || (car.getSpeed() > 0));
        }


    }

    public int getCarSpawns() {
        return carSpawns;
    }

    public void setCarSpawns(int carSpawns) {
        Main.carSpawns = carSpawns;
    }

    public int getRoadSpawns() {
        return roadSpawns;
    }

    public void setRoadSpawns(int roadSpawns) {
        Main.roadSpawns = roadSpawns;
    }

    public int getLightSpawns() {
        return lightSpawns;
    }

    public void setLightSpawns(int lightSpawns) {
        Main.lightSpawns = lightSpawns;
    }
}
