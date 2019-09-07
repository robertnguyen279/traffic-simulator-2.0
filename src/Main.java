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
            roads.add(new Road(Integer.toString(i), 1, 3, new int[]{0, 0}));
            System.out.printf("%s limit of:%dm/s at location:%s to %s%n", roads.get(i).getId(), roads.get(i).getSpeedLimit(), roads.get(i).printStartLocation(), roads.get(i).printEndLocation());
        }
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < carSpawns; i++) {
            cars.add(new Car(Integer.toString(i), roads.get(0)));
            cars.get(i).printCar();
        }

        ArrayList<TrafficLight> lights = new ArrayList<>();
        for (int i = 0; i < lightSpawns; i++) {
            lights.add(new TrafficLight(Integer.toString(i)));
            System.out.printf("%s is:%s at location:%s%n", lights.get(i).getId(), lights.get(i).getState(), lights.get(i).getLocation());
        }
        System.out.println();


        // set locations:
        System.out.println("Settings:");
        roads.get(1).setStartLocation(new int[]{4, 0});
        System.out.printf("%s limit of:%dm/s set to location:%s to %s%n", roads.get(1).getId(), roads.get(1).getSpeedLimit(), roads.get(1).printStartLocation(), roads.get(1).printEndLocation());
        System.out.println();
        cars.get(0).setRoad(roads.get(0)); // set car 0s road
        roads.get(0).getConnectedRoads().add(roads.get(1));
        System.out.println(roads.get(0).getConnectedRoads().get(0).getId());


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
                    currentCar.move();
                    currentCar.printCar();

                }


                System.out.println("Sim turn = " + turn + "\nContinue Sim?(y)");
                answer = simController.nextLine();
                answer = answer.toLowerCase();
            } while (answer.equals("y"));
        }
    }
}
