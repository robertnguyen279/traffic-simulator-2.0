import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static final int XAXIS = 0; //Array location for obj x axis location (x,y)
    public static final int YAXIS = 1; //Array location for obj y axis location (x,y)

    public static void main(String[] args) {
        // set values for user inputs for prototype testing:
        int carSpawns = 1;
        int roadSpawns = 2;
        int lightSpawns = 1;

        int turn = 0; //Simulation ticks (seconds)

        //Get info needed to start sim:
        Scanner simController = new Scanner(System.in);
//        System.out.println("How many roads?");
//        int roadSpawns = simController.nextInt();
//        System.out.println("How many cars?");
//        int carSpawns = simController.nextInt();

        //Create objects:
        System.out.println("Object Creation:");
        ArrayList<Road> roads = new ArrayList<>();
        for (int i = 0; i < roadSpawns; i++) {
            roads.add(new Road("road_" + (i + 1), 1, 3, new int[]{0, 0}));
            System.out.printf("%s limit of:%dm/s at location:%s to %s%n", roads.get(i).getId(), roads.get(i).getSpeedLimit(), roads.get(i).printStartLocation(), roads.get(i).printEndLocation());
        }
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < carSpawns; i++) {
            cars.add(new Car("car_" + (i + 1)));
            System.out.printf("%s going:%dm/s at location:%s%n", cars.get(i).getId(), cars.get(i).getSpeed(), cars.get(i).printLocation());
        }
        ArrayList<TrafficLight> lights = new ArrayList<>();
        for (int i = 0; i < lightSpawns; i++) {
            lights.add(new TrafficLight("light_" + (i + 1)));
            System.out.printf("%s is:%s at location:%s%n", lights.get(i).getId(), lights.get(i).getState(), lights.get(i).printLocation());
        }
        System.out.println();


        // set locations:
        System.out.println("Settings:");
        roads.get(1).setStartLocation(new int[]{4, 0});
        System.out.printf("%s limit of:%dm/s set to location:%s to %s%n", roads.get(1).getId(), roads.get(1).getSpeedLimit(), roads.get(1).printStartLocation(), roads.get(1).printEndLocation());
        lights.get(0).setRoad(0);
        lights.get(0).setLocation(roads.get(lights.get(0).getRoad()).getEndLocation());
        System.out.printf("%s is:%s set to location:%s%n", lights.get(0).getId(), lights.get(0).getState(), lights.get(0).printLocation());
        cars.get(0).setRoad(0);
        cars.get(0).setSpeed(roads.get(cars.get(0).getRoad()).getSpeedLimit());
        System.out.println();

        //Simulation loop:
        System.out.println("Start Simulation?(y)");
        String answer = simController.nextLine();
        answer = answer.toLowerCase();
        System.out.println(answer);
        if (answer.equals("y")) {
            do {
                turn = turn + 1;
                for (int i = 0; i < carSpawns; i++) {
                    Car currentCar = cars.get(i);
                    if (currentCar.getLocation()[0] < roads.get(currentCar.getRoad()).getEndLocation()[0]) {
                        currentCar.setLocation(new int[]{currentCar.getLocation()[0] + currentCar.getSpeed(), 0});
                    } else if (currentCar.getLocation()[0] == roads.get(currentCar.getRoad()).getEndLocation()[0]) {
                        for (int x = 0; x < roadSpawns; x++) {
                            int roadStart = roads.get(x).getStartLocation()[0];
                            if (currentCar.getLocation()[0] == roadStart - 1) {
                                currentCar.setRoad(x);
                                currentCar.setLocation(new int[]{currentCar.getLocation()[0] + currentCar.getSpeed(), 0});
                            }
                        }

                    }
                    System.out.printf("%s going:%dm/s on road_%s at location:%s%n", currentCar.getId(), currentCar.getSpeed(), currentCar.getRoad(), currentCar.printLocation());

                }


                System.out.println("Sim turn = " + turn + "\nContinue Sim?(y)");
                answer = simController.nextLine();
                answer = answer.toLowerCase();
            } while (answer.equals("y"));
        }
    }
}
