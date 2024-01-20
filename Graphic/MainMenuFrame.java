package Graphic;

import Vehicles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class MainMenuFrame extends JFrame implements Runnable, Observer {
    private static volatile MainMenuFrame SingleMenu=null;
    private JPanel mainPanel;
    private JButton addVehicleButton;
    private JButton buyVehicleButton;
    private JButton inventoryReportButton;
    private JButton testDriveButton;
    private JButton resetButton;
    private JButton changeFlagButton;
    private JButton saveStateButton;
    private JButton loadStateButton;
    private JButton exitButton;
    private JLabel totalDistanceLabel;
    private List<Vehicle> vehicles = new ArrayList<>();
    private TestDriveDialog testDriveDialog;
    private Originator origin;
    private Memento memento;
    private Caretaker ct;
    private int totalDistance=0;

    private MainMenuFrame() {
        setTitle("Vehicle Agency");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 10));

        totalDistance = TotalDistanceFileManager.loadTotalDistance();

        addVehicleButton = new JButton("Add Vehicle");
        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddVehicleDialog addVehicleDialog = new AddVehicleDialog(MainMenuFrame.this, vehicles);
                addVehicleDialog.setVisible(true);
                addVehicleDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        mainPanel.add(addVehicleButton);

        buyVehicleButton = new JButton("Buy Vehicle");
        buyVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vehicles.isEmpty()) {
                    JOptionPane.showMessageDialog(MainMenuFrame.this, "The list of vehicles is empty. Please add vehicles to the agency.", "Empty List", JOptionPane.WARNING_MESSAGE);
                } else {
                    BuyVehicleDialog buyVehicleDialog = new BuyVehicleDialog(MainMenuFrame.this, vehicles);
                    buyVehicleDialog.setVisible(true);
                    buyVehicleDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    if (buyVehicleDialog.isConfirmed()) {
                        try {
                            Thread.sleep((int) (Math.random() * 6000 + 5000));
                        } catch (InterruptedException ex) {
                            //pass
                        }
                        if (buyVehicleDialog.isPurchaseApproved()) {
                            JOptionPane.showMessageDialog(MainMenuFrame.this, "The vehicle was purchased successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            Vehicle vehicle = buyVehicleDialog.getSelectedVehicle();
                            vehicles.remove(vehicle);
                            displayUpdateWindow();
                        }
                    }
                }
            }
        });
        mainPanel.add(buyVehicleButton);

        inventoryReportButton = new JButton("Inventory Report");
        inventoryReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vehicles.isEmpty()) {
                    JOptionPane.showMessageDialog(MainMenuFrame.this, "The list of vehicles is empty. Please add vehicles to the agency.", "Empty List", JOptionPane.WARNING_MESSAGE);
                } else {
                    InventoryReportDialog reportDialog = new InventoryReportDialog(MainMenuFrame.this, vehicles);
                    reportDialog.setVisible(true);
                    reportDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }
        });
        mainPanel.add(inventoryReportButton);

        testDriveButton = new JButton("Test Drive");
        testDriveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vehicles.isEmpty()) {
                    JOptionPane.showMessageDialog(MainMenuFrame.this, "The list of vehicles is empty. Please add vehicles to the agency.", "Empty List", JOptionPane.WARNING_MESSAGE);
                } else {
                    testDriveDialog = new TestDriveDialog(MainMenuFrame.this, vehicles);
                    testDriveDialog.getExecutorService().execute (() -> {
                        testDriveDialog.registerObserver(MainMenuFrame.getInstance());
                        if (testDriveDialog.isConfirmed()) {
                            testDriveDialog.start();
                        } else {
                            testDriveDialog.stop();
                        }
                    });
                    testDriveDialog.setVisible(true);
                    testDriveDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    if (testDriveDialog.isConfirmed()) {
                        int distance = testDriveDialog.get_distance();
                        long sleepTime = distance * 100;

                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException ex) {
                            //pass
                        }
                        JOptionPane.showMessageDialog(MainMenuFrame.this, "The test drive was made successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        testDriveDialog.endTestDrive();
                        testDriveDialog.removeObserver(MainMenuFrame.getInstance());
                    }
                }
            }
        });
        mainPanel.add(testDriveButton);

        resetButton = new JButton("Reset Km");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vehicles.isEmpty()) {
                    JOptionPane.showMessageDialog(MainMenuFrame.this, "The list of vehicles is empty. Please add vehicles to the agency.", "Empty List", JOptionPane.WARNING_MESSAGE);
                } else {
                    for (Vehicle vehicle : vehicles) {
                        vehicle.set_km(0);
                    }
                    JOptionPane.showMessageDialog(MainMenuFrame.this, "The distance was successfully reset in all vehicles.", "Reset Km", JOptionPane.INFORMATION_MESSAGE);
                    displayUpdateWindow();
                }
            }
        });
        mainPanel.add(resetButton);

        changeFlagButton = new JButton("Change Flag");
        changeFlagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vehicles.isEmpty()) {
                    JOptionPane.showMessageDialog(MainMenuFrame.this, "The list of vehicles is empty. Please add vehicles to the agency.", "Empty List", JOptionPane.WARNING_MESSAGE);
                } else {
                    ChangeFlagDialog changeFlagDialog = new ChangeFlagDialog(MainMenuFrame.this, vehicles);
                    changeFlagDialog.setVisible(true);
                    changeFlagDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    displayUpdateWindow();
                }
            }
        });
        mainPanel.add(changeFlagButton);

        saveStateButton = new JButton("Save State");
        saveStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vehicles.isEmpty()) {
                    JOptionPane.showMessageDialog(MainMenuFrame.this, "The list of vehicles is empty. Please add vehicles to the agency.", "Empty List", JOptionPane.WARNING_MESSAGE);
                } else {
                    origin = new Originator();
                    origin.setState(MainMenuFrame.getInstance());
                    memento = origin.createMemento();
                    ct = new Caretaker();
                    if(ct.get_list_size()>=3){
                        ct.removeMemento();
                    }
                    ct.addMemento(memento);
                    JOptionPane.showMessageDialog(MainMenuFrame.this, "State saved successfully", "Save State", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        mainPanel.add(saveStateButton);


        loadStateButton = new JButton("Load State");
        loadStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ct==null) {
                    JOptionPane.showMessageDialog(MainMenuFrame.this, "No saved states available.", "Load State", JOptionPane.WARNING_MESSAGE);
                } else {
                    ct.getMemento(ct.get_list_size()-1);
                    origin.setMemento(memento);
                    JOptionPane.showMessageDialog(MainMenuFrame.this, "State loaded successfully", "Load State", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        mainPanel.add(loadStateButton);


        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean testDrivesInProgress = false;
                for (int i=0;i<vehicles.size();i++) {
                    if (testDriveDialog !=null && isTestDriveInProgress()) {
                        testDrivesInProgress = true;
                        break;
                    }
                }

                if (testDrivesInProgress) {
                    JOptionPane.showMessageDialog(MainMenuFrame.this, "Cannot exit. Test drives are in progress.", "Exit Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    dispose();
                    System.exit(0);
                }
            }
        });

        totalDistanceLabel = new JLabel("Total Distance: " + totalDistance);

        mainPanel.add(totalDistanceLabel);
        mainPanel.add(exitButton);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void displayUpdateWindow() {
        JFrame updateFrame = new JFrame("Updating Database");
        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel updatingLabel = new JLabel("Updating database... Please wait", SwingConstants.CENTER);
        updateFrame.getContentPane().add(updatingLabel);
        updateFrame.setSize(300, 100);
        updateFrame.setLocationRelativeTo(this);
        updateFrame.setVisible(true);

        int delay = (int) (Math.random() * 5000 + 3000);
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFrame.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private boolean isTestDriveInProgress() {
        for (int i=0;i<vehicles.size();i++) {
            if (testDriveDialog.isAlive()) {
                return true;
            }
        }
        return false;
    }
    public void start() {
        setVisible(true);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        //pass
    }

    @Override
    public void update(int distance) {
        totalDistance += distance;
        totalDistanceLabel.setText("Total Distance: " + totalDistance);
        TotalDistanceFileManager.saveTotalDistance(totalDistance);
    }

    public static MainMenuFrame getInstance(){
        if(SingleMenu==null){
            synchronized (MainMenuFrame.class){
                if(SingleMenu==null){
                    SingleMenu=new MainMenuFrame();
                }
            }
        }
        return SingleMenu;
    }
    private class TotalDistanceFileManager {
        private static final String FILE_PATH = "total_distance.txt";

        public static int loadTotalDistance() {
            int totalDistance = 0;

            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line = reader.readLine();
                if (line != null) {
                    totalDistance = Integer.parseInt(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return totalDistance;
        }

        public static void saveTotalDistance(int totalDistance) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                writer.write(String.valueOf(totalDistance));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
