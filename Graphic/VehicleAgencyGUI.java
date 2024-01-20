package Graphic;
import javax.swing.*;

public class VehicleAgencyGUI extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenuFrame mainMenuFrame = MainMenuFrame.getInstance();
            mainMenuFrame.start();
        });
    }
}
