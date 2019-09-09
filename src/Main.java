import java.util.ArrayList;

public class Main {

    //    public static final int XAXIS = 0; //Array location for obj x axis location (x,y)
//    public static final int YAXIS = 1; //Array location for obj y axis location (x,y)
    private static int carSpawns;
    private static int roadSpawns;
    private static int lightSpawns;

    public static void main(String[] args) {
        Main main = new Main();
//      int turn = 0; //Simulation ticks (seconds)

        //Get info needed to start sim:
//        Scanner simController = new Scanner(System.in);
//        System.out.println("How many roads?");
//        main.setRoadSpawns(simController.nextInt());
//        System.out.println("How many cars?");
//        main.setCarSpawns(simController.nextInt());
//        System.out.println("How many traffic lights?");
//        main.setLightSpawns(simController.nextInt());

        // set values for user inputs for prototype testing:
        main.setCarSpawns(1);
        main.setLightSpawns(1);
        main.setRoadSpawns(2);

        //Create objects:
        System.out.println("Object Creation:");
        ArrayList<Road> roads = new ArrayList<>();
        for (int i = 0; i < roadSpawns; i++) {
            roads.add(new Road(Integer.toString(i), 1, 5, new int[]{0, 0}));
            System.out.printf("%s limit of:%dm/s at location:%s to %s%n", roads.get(i).getId(), roads.get(i).getSpeedLimit(), roads.get(i).printStartLocation(), roads.get(i).printEndLocation());
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
        roads.get(1).setStartLocation(new int[]{4, 0}); // move road_1 to a position at the end of road_0.
        roads.get(1).setLength(7); // make road_1 longer.
        System.out.printf("%s limit of:%dm/s set to location:%s to %s%n", roads.get(1).getId(), roads.get(1).getSpeedLimit(), roads.get(1).printStartLocation(), roads.get(1).printEndLocation());
        roads.get(0).getConnectedRoads().add(roads.get(1)); // connect road_0 to road_1
        System.out.println();



        //Simulation loop:
        System.out.println("Simulation:");
        for (Car car : cars
        ) {
            do {
                for (TrafficLight light : lights
                ) {
                    light.operate();
                    light.printLightStatus();
                }
                car.move();
                car.printCarStatus();
                System.out.println();
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
