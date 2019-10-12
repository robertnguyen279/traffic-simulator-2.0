import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class Main {

    public static final int WINDOW_WIDTH = 1600;
    public static final int WINDOW_HEIGHT = 1024;
    private static SimulationPanel simulationPanel = new SimulationPanel();

    public static void main(String[] args) {
        // Simulation Window setup:
        JFrame mainWindow = new JFrame("Traffic Simulator");
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

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
        JPanel placeholder = new JPanel();
        placeholder.setBackground(Color.GRAY);
        mainWindow.add(placeholder);

        //Menu bar:
        JMenuBar menuBar = new JMenuBar();
        mainWindow.add(menuBar, BorderLayout.NORTH);

        JMenu editMenu = new JMenu("City Editor");
        MenuListener cityLis = new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                modeLabel.setText("Mode: Editor");
                mainWindow.add(placeholder);
                mainWindow.repaint();
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                mainWindow.remove(placeholder);
                mainWindow.repaint();
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        };
        editMenu.addMenuListener(cityLis);
        menuBar.add(editMenu);

        JMenu simMenu = new JMenu("Simulation");
        MenuListener simLis = new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                System.out.println("Simulator Selected");
                modeLabel.setText("Mode: Simulation");
                simulationPanel = new SimulationPanel();
                mainWindow.add(simulationPanel);
                simulationPanel.simulate(2000);//Integer.parseInt(JOptionPane.showInputDialog("Time Scale?")));
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                System.out.println("Simulator Deselected");
                mainWindow.remove(simulationPanel);
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        };
        simMenu.addMenuListener(simLis);
        menuBar.add(simMenu);

        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);

    }
}
