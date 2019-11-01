import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditorPanel extends JPanel {

    private ArrayList<Road> roads;
    private ArrayList<TrafficLight> lights;
    private int scale;


    public void newMap() {
        setLayout(new BorderLayout());
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(10, 0));
        sidePanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        JButton addRoadButton = new JButton("Add Road");
        sidePanel.add(addRoadButton);
        JButton AddLightButton = new JButton("Add Traffic Light");
        sidePanel.add(AddLightButton);
        add(sidePanel, BorderLayout.WEST);

        roads = new ArrayList<>();
        int lengthInput = 70;
            int speedLimitInput = 1; // force speed limit to be 1 for prototype.
        roads.add(new Road(Integer.toString(1), speedLimitInput, lengthInput, new int[]{0, 0}));
        roads.add(new Road(Integer.toString(2), speedLimitInput, lengthInput, new int[]{0, 0}));


        lights = new ArrayList<>();
        lights.add(new TrafficLight(Integer.toString(1), roads.get(0))); // all created lights will begin on road_0.


        // set locations and connections:
        roads.get(1).setStartLocation(new int[]{roads.get(0).getLength(), 0}); // place road_1 to a position at the end of road_0.
        roads.get(0).getConnectedRoads().add(roads.get(1)); // connect road_0 to road_1
        roads.get(0).setOrientation(Road.Orientation.HORIZONATAL);
        roads.get(1).setOrientation(Road.Orientation.HORIZONATAL);

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

        for (TrafficLight light : lights
        ) {
            light.draw(g, scale);
        }
    }

}
