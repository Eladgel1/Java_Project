package Graphic;

import Vehicles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuyVehicleDialog extends JDialog implements ActionListener {
    private List<Vehicle> vehicles;
    private JButton[] vehicleButtons;
    private JTextArea vehicleDetailsTextArea;
    private JTextField modelField;
    private JTextField speedField;
    private JTextField passengersField;
    private JButton confirmButton;
    private boolean confirmed;
    private Vehicle selectedVehicle;

    public BuyVehicleDialog(Frame parent, List<Vehicle> Vehicles) {
        super(parent, "Buy Vehicle", true);
        this.vehicles = Vehicles;

        setLayout(new BorderLayout());

        JPanel BuyVehiclePanel = new JPanel();
        BuyVehiclePanel.setLayout(new BorderLayout(10, 10));

        JPanel vehicleListPanel = new JPanel();
        vehicleListPanel.setLayout(new GridLayout(0, 1, 10, 10));

        vehicleButtons = new JButton[vehicles.size()];
        for (int i = 0; i < vehicles.size(); i++) {
            ImageIcon icon = new ImageIcon(getVehicleImage(vehicles.get(i).get_type()));
            Image image = icon.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
            vehicleButtons[i] = new JButton(new ImageIcon(image));
            vehicleButtons[i].setPreferredSize(new Dimension(150, 100));
            vehicleButtons[i].addActionListener(this);
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

            vehicleButtons[i].setBorder(BorderFactory.createLineBorder(frameColor,2));
            vehicleListPanel.add(vehicleButtons[i]);
        }

        JScrollPane vehicleListScrollPane = new JScrollPane(vehicleListPanel);
        BuyVehiclePanel.add(vehicleListScrollPane, BorderLayout.WEST);

        vehicleDetailsTextArea = new JTextArea();
        vehicleDetailsTextArea.setEditable(false);
        vehicleDetailsTextArea.setLineWrap(true);
        vehicleDetailsTextArea.setWrapStyleWord(true);

        JScrollPane detailsScrollPane = new JScrollPane(vehicleDetailsTextArea);
        BuyVehiclePanel.add(detailsScrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel modelLabel = new JLabel("Model:");
        modelField = new JTextField();
        inputPanel.add(modelLabel);
        inputPanel.add(modelField);

        JLabel speedLabel = new JLabel("Maximum Speed:");
        speedField = new JTextField();
        inputPanel.add(speedLabel);
        inputPanel.add(speedField);

        JLabel passengersLabel = new JLabel("Maximum Passengers:");
        passengersField = new JTextField();
        inputPanel.add(passengersLabel);
        inputPanel.add(passengersField);

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);
        inputPanel.add(confirmButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(inputPanel);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(BuyVehiclePanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel);

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

    public boolean isConfirmed() {
        return confirmed;
    }

    public Vehicle getSelectedVehicle() {
        return selectedVehicle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) e.getSource();
            for (int i = 0; i < vehicleButtons.length; i++) {
                if (sourceButton == vehicleButtons[i]) {
                    selectedVehicle = vehicles.get(i);
                    vehicleDetailsTextArea.setText(selectedVehicle.toString());
                    break;
                }
            }
        }if (e.getSource() == confirmButton) {
            String model = modelField.getText();
            int max_speed = Integer.parseInt(speedField.getText());
            int max_pass = Integer.parseInt(passengersField.getText());

            if (selectedVehicle != null &&
                    selectedVehicle.get_model().equals(model) &&
                    selectedVehicle.get_MaxSpeed() == max_speed &&
                    selectedVehicle.get_MaxPass() == max_pass) {
                confirmed = true;
                dispose();
                JOptionPane.showMessageDialog(this, "Press 'OK' and please wait...", "Waiting", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect details, please try again.");
                modelField.setText("");
                speedField.setText("");
                passengersField.setText("");
            }
        }
    }
    public boolean isPurchaseApproved() {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to proceed with the purchase?", "Confirm Purchase", JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION ;
    }
}

