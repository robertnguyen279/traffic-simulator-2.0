import javax.swing.*;

public class SimulationWindow extends JFrame {

    public SimulationWindow() {
        this.getContentPane().add(new JLabel("Sim"));
        this.setBounds(0, 0, 1600, 1024);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

