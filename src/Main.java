import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class Main {

    public static final int WINDOW_WIDTH = 1600;
    public static final int WINDOW_HEIGHT = 1024;
    public static int updateRate = 2000;
    private static SimulationPanel simulationPanel;
    private static final int SCALE = 10;

    public static void main(String[] args) {
        // Simulation Window setup:
        JFrame mainWindow = new JFrame("Traffic Simulator");
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        //Status Bar
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 0));
        bottomPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        JLabel modeLabel = new JLabel("Mode: ");
        bottomPanel.add(modeLabel);
        JLabel statusLabel = new JLabel("Status: ");
        bottomPanel.add(statusLabel);
        mainWindow.add(bottomPanel, BorderLayout.SOUTH);

        //Editor Panel
        EditorPanel editorPanel = new EditorPanel();
        statusLabel.setText("Status: New Map");
        editorPanel.newMap();
        editorPanel.setScale(SCALE);
        mainWindow.add(editorPanel);

        //Menu bar:
        JMenuBar menuBar = new JMenuBar();
        mainWindow.add(menuBar, BorderLayout.NORTH);

        //Editor Menu:
        JMenu editMenu = new JMenu("City Editor");
        MenuListener cityLis = new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                modeLabel.setText("Mode: Editor");
                mainWindow.repaint();
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        };
        editMenu.addMenuListener(cityLis);
        menuBar.add(editMenu);

        JMenuItem newMapItem = new JMenuItem("New");
        newMapItem.addActionListener(e -> {
            editorPanel.setVisible(true);
            statusLabel.setText("Status: New Map");
            editorPanel.newMap();
            mainWindow.validate();
            mainWindow.repaint();
        });
        editMenu.add(newMapItem);

        JMenuItem openMapItem = new JMenuItem("Open");
        openMapItem.addActionListener(e -> {
        });
        editMenu.add(openMapItem);

        JMenuItem saveMapItem = new JMenuItem("Save");
        saveMapItem.addActionListener(e -> {
        });
        editMenu.add(saveMapItem);

        JMenuItem exitProgramItem = new JMenuItem("Exit");
        exitProgramItem.addActionListener(e -> System.exit(0));
        editMenu.add(exitProgramItem);

        JMenu simMenu = new JMenu("Simulation");
        MenuListener simLis = new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                modeLabel.setText("Mode: Simulation");
                mainWindow.repaint();
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        };
        simMenu.addMenuListener(simLis);


        JMenuItem loadSimItem = new JMenuItem("Load Map");
        simMenu.add(loadSimItem);

        JMenuItem spawnItem = new JMenuItem("Add Vehicles");
        simMenu.add(spawnItem);

        JMenuItem startSimItem = new JMenuItem("Start");
        startSimItem.setEnabled(false);
        startSimItem.addActionListener(e -> {
            simulationPanel.simulate(updateRate);
            statusLabel.setText("Status: Simulation Started");
            simulationPanel.setStopSim(false);
            mainWindow.validate();
            mainWindow.repaint();
        });
        simMenu.add(startSimItem);

        loadSimItem.addActionListener(e -> {
            editorPanel.setVisible(false);
            simulationPanel = new SimulationPanel();
            simulationPanel.setScale(SCALE);
            simulationPanel.loadMap(editorPanel.getRoads(), editorPanel.getLights());
            mainWindow.add(simulationPanel);
            startSimItem.setEnabled(true);
            menuBar.revalidate();
            mainWindow.repaint();
        });

        spawnItem.addActionListener(e -> {
            String spawnInput = JOptionPane.showInputDialog("Total number of Vehicles to spawn:");
            int spawns = Integer.parseInt(spawnInput);
            simulationPanel.setVehicleSpawn(spawns);
            String spawnRateInput = JOptionPane.showInputDialog("Number of Simulation tics between spawns:");
            int spawnRate = Integer.parseInt(spawnRateInput);
            simulationPanel.setVehicleSpawnRate(spawnRate);
        });

        JMenuItem stopSimItem = new JMenuItem("Stop");
        stopSimItem.addActionListener(e -> {
            simulationPanel.setStopSim(true);
            statusLabel.setText("Status: Simulation Stopped");
            mainWindow.validate();
            mainWindow.repaint();
        });
        simMenu.add(stopSimItem);

        JMenuItem setUpdateRateItem = new JMenuItem("Update Rate");
        setUpdateRateItem.addActionListener(e -> {
            String updateRateInput = JOptionPane.showInputDialog("Enter the Update Rate of the Simulation");
            updateRate = Integer.parseInt(updateRateInput);
            statusLabel.setText("Status: Update Rate set to " + updateRate);
        });
        simMenu.add(setUpdateRateItem);

        menuBar.add(simMenu);

        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);

    }
}
