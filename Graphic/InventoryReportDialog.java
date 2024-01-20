package Graphic;


import Vehicles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class InventoryReportDialog extends JDialog implements MouseListener {
    private List<Vehicle> vehicles;
    private JTextArea vehicleDetailsTextArea;

    public InventoryReportDialog(Frame parent, List<Vehicle> vehicles) {
        super(parent, "Current Inventory Report", true);
        this.vehicles = vehicles;

        setLayout(new BorderLayout());

        JPanel vehiclePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        vehicleDetailsTextArea = new JTextArea();
        vehicleDetailsTextArea.setEditable(false);
        vehicleDetailsTextArea.setLineWrap(true);
        vehicleDetailsTextArea.setWrapStyleWord(true);
        vehiclePanel.add(vehicleDetailsTextArea);

        JScrollPane scrollPane = new JScrollPane(vehiclePanel);
        add(scrollPane, BorderLayout.CENTER);

        for (int i=0;i<vehicles.size();i++) {
            ImageIcon icon = new ImageIcon(getVehicleImage(vehicles.get(i).get_type()));
            Image image = icon.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);

            JLabel vehicleLabel = new JLabel(new ImageIcon(image));
            vehicleLabel.addMouseListener(this);
            vehicleLabel.setToolTipText(vehicles.get(i).toString());

            String vehicleColor = vehicles.get(i).get_color();
            Color frameColor;

            switch (vehicleColor) {
                case "BLACK":
                    frameColor = Color.BLACK;
                    break;
                case "BLUE":
                    frameColor = Color.BLUE;
                    break;
                case "GRAY":
                    frameColor = Color.GRAY;
                    break;
                case "GREEN":
                    frameColor = Color.GREEN;
                    break;
                case "PINK":
                    frameColor = Color.PINK;
                    break;
                case "ORANGE":
                    frameColor = Color.ORANGE;
                    break;
                case "YELLOW":
                    frameColor = Color.YELLOW;
                    break;
                case "RED":
                    frameColor = Color.RED;
                    break;
                case "WHITE":
                    frameColor=Color.WHITE;
                default:
                    frameColor = Color.WHITE;
                    break;
            }
            vehicleLabel.setBorder(BorderFactory.createLineBorder(frameColor,2));
            vehiclePanel.add(vehicleLabel);
        }

        pack();
        setLocationRelativeTo(parent);
    }

    private String getVehicleImage(String type) {
        switch (type) {
            case "Jeep":
                return "Jeep.png";
            case "Frigate":
                return "Frigate.jpg";
            case "Spy Glider":
                return "SpyGlider.jpg";
            case "Game Glider":
                return "GameGlider.jpg";
            case "Bike":
                return "Bike.jpg";
            case "Cruise Ship":
                return "CruiseShip.jpg";
            case "Amphibious":
                return "Amphibious.jpg";
            case "Electric Bike":
                return "ElectricBike.png";
            case "Hybrid Plane":
                return "HybridPlane.jpg";
            default:
                return "";
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel sourceLabel = (JLabel) e.getSource();
        Vehicle vehicle = getVehicleByToolTipText(sourceLabel.getToolTipText());
        if (vehicle != null) {
            vehicleDetailsTextArea.setText(vehicle.toString());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        vehicleDetailsTextArea.setText("");
    }

    private Vehicle getVehicleByToolTipText(String toolTipText) {
        for (int i=0;i<vehicles.size();i++) {
            if (vehicles.get(i).toString().equals(toolTipText)) {
                return vehicles.get(i);
            }
        }
        return null;
    }
}