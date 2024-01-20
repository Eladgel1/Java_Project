package Graphic;

import Vehicles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDriveDialog extends JDialog implements ActionListener,Runnable, Subject {
    private List<Vehicle> vehicles;
    private ExecutorService executorService;
    private static final int MAX_CONCURRENT_TEST_DRIVES = 7;
    private JButton[] vehicleButtons;
    private JTextArea vehicleDetailsTextArea;
    private JTextField modelField;
    private JTextField speedField;
    private JTextField passengersField;
    private JButton confirmButton;
    private Vector <Observer> ObsList=new Vector<>();
    private boolean confirmed;
    private Vehicle selectedVehicle;
    private int kilometers;
    private Thread thread;
    private boolean isRunning;

    public TestDriveDialog(Frame parent, List<Vehicle> vehicles) {
        super(parent, "Take Vehicle for Test Drive", true);
        this.vehicles = vehicles;

        this.thread=new Thread(this);

        this.executorService= Executors.newFixedThreadPool(MAX_CONCURRENT_TEST_DRIVES);

        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));

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
        mainPanel.add(vehicleListScrollPane, BorderLayout.WEST);

        vehicleDetailsTextArea = new JTextArea();
        vehicleDetailsTextArea.setEditable(false);
        vehicleDetailsTextArea.setLineWrap(true);
        vehicleDetailsTextArea.setWrapStyleWord(true);

        JScrollPane detailsScrollPane = new JScrollPane(vehicleDetailsTextArea);
        mainPanel.add(detailsScrollPane, BorderLayout.CENTER);

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
        contentPanel.add(mainPanel, BorderLayout.CENTER);
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
                String kmStr = JOptionPane.showInputDialog(this, "Enter the number of kilometers for the test drive:");
                if (kmStr != null) {
                        try {
                            kilometers = Integer.parseInt(kmStr);
                            selectedVehicle.set_km(selectedVehicle.get_km() + kilometers);
                            JOptionPane.showMessageDialog(this, "The test drive has begun,\nPress 'OK' and please wait...", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input for kilometers. Test drive canceled.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                } else {
                    JOptionPane.showMessageDialog(this, "Test drive canceled.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect details, Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                modelField.setText("");
                speedField.setText("");
                passengersField.setText("");
            }
        }
    }
    public void run(){}
    public void start() {
        thread.start();
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }

    public boolean isAlive() {
        return thread.isAlive();
    }

    public int get_distance(){
        return kilometers;
    }

    @Override
    public void registerObserver(Observer observer) {
        ObsList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        ObsList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : ObsList) {
            observer.update(get_distance());
        }
    }

    public void endTestDrive() {
        notifyObservers();
    }

    public ExecutorService getExecutorService(){
        return this.executorService;
    }

}
