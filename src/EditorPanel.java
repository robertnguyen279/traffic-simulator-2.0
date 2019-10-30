import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditorPanel extends JPanel {

    private ArrayList<Road> roads;
    private ArrayList<TrafficLight> lights;
    private int scale;


    public void newMap() {
        roads = new ArrayList<>();
        int roadSpawns = 2;
        for (int i = 0; i < roadSpawns; i++) {
//            int lengthInput = Integer.parseInt(JOptionPane.showInputDialog("Please input length for road_" + i));
            int lengthInput = 10;
            int speedLimitInput = 1; // force speed limit to be 1 for prototype.
            roads.add(new Road(Integer.toString(i), speedLimitInput, lengthInput, new int[]{0, 0}));
        }

        lights = new ArrayList<>();
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

    }


    public ArrayList<Road> getRoads() {
        return roads;
    }

    public ArrayList<TrafficLight> getLights() {
        return lights;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Road road : roads
        ) {
            road.draw(g, scale);
        }
    }

}
