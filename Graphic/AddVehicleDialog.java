package Graphic;

import Vehicles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class  AddVehicleDialog extends JDialog implements ActionListener {
    private JComboBox<String> vehicleTypeComboBox;
    private JButton continueButton;
    private JButton cancelButton;

    private List<Vehicle> vehicles;

    public AddVehicleDialog(Frame parent, List<Vehicle> vehicles) {
        super(parent, "Add Vehicle", true);
        this.vehicles = vehicles;
        setLayout(new BorderLayout());

        JPanel AddVehiclePanel = new JPanel();
        AddVehiclePanel.setLayout(new GridLayout(2, 2));

        JLabel vehicleTypeLabel = new JLabel("Vehicle Type:");
        vehicleTypeComboBox = new JComboBox<>(new String[]{"Jeep", "Frigate", "Spy Glider", "Game Glider", "Bike", "Cruise Ship", "Amphibious", "Electric Bike", "Hybrid Plane"});
        AddVehiclePanel.add(vehicleTypeLabel);
        AddVehiclePanel.add(vehicleTypeComboBox);

        continueButton = new JButton("Continue");
        continueButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(continueButton);
        buttonPanel.add(cancelButton);

        add(AddVehiclePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continueButton) {
            String vehicleType = (String) vehicleTypeComboBox.getSelectedItem();
            openDataEntryDialog(vehicleType);
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }

    private void openDataEntryDialog(String vehicleType) {
        if (vehicleType.equals("Jeep")) {
            AddJeepDialog dialog = new AddJeepDialog(this);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                Jeep jeep = dialog.getJeep();
                vehicles.add(jeep);
                System.out.println("Vehicle added: " + jeep);
            }
        } else if (vehicleType.equals("Frigate")) {
            AddFrigateDialog dialog = new AddFrigateDialog(this);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                Frigate frigate = dialog.getFrigate();
                vehicles.add(frigate);
                System.out.println("Vehicle added: " + frigate);
            }
        } else if (vehicleType.equals("Spy Glider")) {
            AddSpyGliderDialog dialog = new AddSpyGliderDialog(this);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                SpyGlider spyGlider = dialog.getSpyGlider();
                vehicles.add(spyGlider);
                System.out.println("Vehicle added: " + spyGlider);
            }
        } else if (vehicleType.equals("Game Glider")) {
            AddGameGliderDialog dialog = new AddGameGliderDialog(this);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                GameGlider gameGlider = dialog.getGameGlider();
                vehicles.add(gameGlider);
                System.out.println("Vehicle added: " + gameGlider);
            }
        } else if (vehicleType.equals("Bike")) {
            AddBikeDialog dialog = new AddBikeDialog(this);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                Bike bike = dialog.getBike();
                vehicles.add(bike);
                System.out.println("Vehicle added: " + bike);
            }
        } else if (vehicleType.equals("Cruise Ship")) {
            AddCruiseShipDialog dialog = new AddCruiseShipDialog(this);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                CruiseShip cruiseShip = dialog.getCruiseShip();
                vehicles.add(cruiseShip);
                System.out.println("Vehicle added: " + cruiseShip);
            }
        } else if (vehicleType.equals("Amphibious")) {
            AddAmphibiousDialog dialog = new AddAmphibiousDialog(this);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                Amphibious amphibious = dialog.getAmphibious();
                vehicles.add(amphibious);
                System.out.println("Vehicle added: " + amphibious);
            }
        } else if (vehicleType.equals("Electric Bike")) {
            AddElectricBikeDialog dialog = new AddElectricBikeDialog(this);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                ElectricBike electricBike = dialog.getElectricBike();
                vehicles.add(electricBike);
                System.out.println("Vehicle added: " + electricBike);
            }
        } else if (vehicleType.equals("Hybrid Plane")) {
            AddHybridPlaneDialog dialog = new AddHybridPlaneDialog(this);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                HybridPlane hybridPlane = dialog.getHybridPlane();
                vehicles.add(hybridPlane);
                System.out.println("Vehicle added: " + hybridPlane);
            }
        }
        dispose();
    }
    private class AddAmphibiousDialog extends JDialog implements ActionListener {
        private JTextField modelField;
        private JTextField speedField;
        private JTextField passengersField;
        private JTextField wheelsField;
        private JComboBox<ImageIcon> flagComboBox;
        private JComboBox<String> colorComboBox;
        private JTextField engineField;
        private JTextField fuelField;
        private JComboBox<String> windDirectionComboBox;
        private JButton confirmButton;
        private JButton cancelButton;
        private boolean confirmed;
        private Amphibious amphibious;

        public AddAmphibiousDialog(Dialog parent) {
            super(parent, "Add Amphibious Vehicle", true);
            setLayout(new BorderLayout());

            JPanel AmphibiousPanel = new JPanel();
            AmphibiousPanel.setLayout(new GridLayout(5, 2, 10, 10));

            ImageIcon[] flagIcons = {
                    new ImageIcon("Israel_Flag.png"),
                    new ImageIcon("USA_Flag.png"),
                    new ImageIcon("Germany_Flag.png"),
                    new ImageIcon("Italy_Flag.jpg"),
                    new ImageIcon("Greece_Flag.jpg"),
                    new ImageIcon("Somalia_Flag.png"),
                    new ImageIcon("Pirate_Flag.png")
            };

            String[] colors = {"BLACK", "BLUE", "GRAY", "GREEN", "PINK", "ORANGE", "YELLOW", "RED", "WHITE"};
            colorComboBox = new JComboBox<>(colors);
            AmphibiousPanel.add(new JLabel("Color:"));
            AmphibiousPanel.add(colorComboBox);


            flagComboBox = new JComboBox<>(flagIcons);
            flagComboBox.setRenderer(new FlagListCellRenderer());

            for (int i = 0; i < flagIcons.length; i++) {
                Image image = flagIcons[i].getImage();
                Image scaledImage = image.getScaledInstance(120, 80, Image.SCALE_SMOOTH);
                flagIcons[i].setImage(scaledImage);
            }

            JLabel modelLabel = new JLabel("Model:");
            modelField = new JTextField();
            AmphibiousPanel.add(modelLabel);
            AmphibiousPanel.add(modelField);

            JLabel speedLabel = new JLabel("Max Speed:");
            speedField = new JTextField();
            AmphibiousPanel.add(speedLabel);
            AmphibiousPanel.add(speedField);

            JLabel passengersLabel = new JLabel("Max Passengers:");
            passengersField = new JTextField();
            AmphibiousPanel.add(passengersLabel);
            AmphibiousPanel.add(passengersField);

            JLabel wheelsLabel = new JLabel("Number of Wheels:");
            wheelsField = new JTextField();
            AmphibiousPanel.add(wheelsLabel);
            AmphibiousPanel.add(wheelsField);

            JLabel flagLabel = new JLabel("Flag:");
            AmphibiousPanel.add(flagLabel);
            AmphibiousPanel.add(flagComboBox);

            JLabel engineLabel = new JLabel("Average Engine Lifetime:");
            engineField = new JTextField();
            AmphibiousPanel.add(engineLabel);
            AmphibiousPanel.add(engineField);

            JLabel fuelLabel = new JLabel("Average Fuel Consumption:");
            fuelField = new JTextField();
            AmphibiousPanel.add(fuelLabel);
            AmphibiousPanel.add(fuelField);

            JLabel windDirectionLabel = new JLabel("Wind Direction:");
            windDirectionComboBox = new JComboBox<>(new String[]{"With the Wind", "Against the Wind"});
            AmphibiousPanel.add(windDirectionLabel);
            AmphibiousPanel.add(windDirectionComboBox);

            confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(this);

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);

            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            contentPanel.add(AmphibiousPanel, BorderLayout.CENTER);
            contentPanel.add(buttonPanel, BorderLayout.SOUTH);

            add(contentPanel);

            pack();
            setLocationRelativeTo(parent);
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public Amphibious getAmphibious() {
            return amphibious;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == confirmButton) {
                String model = modelField.getText();
                int max_speed = Integer.parseInt(speedField.getText());
                int max_pass = Integer.parseInt(passengersField.getText());
                int wheels = Integer.parseInt(wheelsField.getText());
                ImageIcon selectedFlag = (ImageIcon) flagComboBox.getSelectedItem();
                String flag = getFlagFromIcon(selectedFlag);
                int engineLifetime = Integer.parseInt(engineField.getText());
                int fuelConsumption = Integer.parseInt(fuelField.getText());
                String color= colorComboBox.getSelectedItem().toString();
                boolean windDirection = windDirectionComboBox.getSelectedItem().equals("With the Wind");

                amphibious = new Amphibious(model,color, max_speed, max_pass, wheels, flag, windDirection);
                amphibious.Engine_LifeTime(engineLifetime);
                amphibious.set_avg_fuel(fuelConsumption);
                confirmed = true;
                dispose();
            } else if (e.getSource() == cancelButton) {
                dispose();
            }
        }

        private String getFlagFromIcon(ImageIcon icon) {
            if (icon.getDescription().equals("Israel_Flag.png")) {
                return "Israel";
            } else if (icon.getDescription().equals("USA_Flag.png")) {
                return "U.S";
            } else if (icon.getDescription().equals("Germany_Flag.png")) {
                return "Germany";
            } else if (icon.getDescription().equals("Italy_Flag.jpg")) {
                return "Italy";
            } else if (icon.getDescription().equals("Greece_Flag.jpg")) {
                return "Greece";
            } else if (icon.getDescription().equals("Somalia_Flag.png")) {
                return "Somalia";
            } else if (icon.getDescription().equals("Pirate_Flag.png")) {
                return "Pirate Flag";
            } else {
                return "";
            }
        }
        private class FlagListCellRenderer extends DefaultListCellRenderer {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                ImageIcon icon = (ImageIcon) value;
                label.setIcon(icon);
                label.setText("");
                return label;
            }
        }
    }

    private class AddBikeDialog extends JDialog implements ActionListener {
        private JTextField modelField;
        private JTextField speedField;
        private JComboBox<String> roadTypeComboBox;
        private JComboBox<String> colorComboBox;
        private JButton confirmButton;
        private JButton cancelButton;
        private boolean confirmed;
        private Bike bike;

        public AddBikeDialog(Dialog parent) {
            super(parent, "Add Bike", true);
            setLayout(new BorderLayout());

            JPanel BikePanel = new JPanel();
            BikePanel.setLayout(new GridLayout(3, 2, 10, 10));

            roadTypeComboBox = new JComboBox<>(new String[]{"Dirt", "Asphalt"});

            String[] colors = {"BLACK", "BLUE", "GRAY", "GREEN", "PINK", "ORANGE", "YELLOW", "RED", "WHITE"};
            colorComboBox = new JComboBox<>(colors);
            BikePanel.add(new JLabel("Color:"));
            BikePanel.add(colorComboBox);

            JLabel modelLabel = new JLabel("Model:");
            modelField = new JTextField();
            BikePanel.add(modelLabel);
            BikePanel.add(modelField);

            JLabel speedLabel = new JLabel("Maximum Speed:");
            speedField = new JTextField();
            BikePanel.add(speedLabel);
            BikePanel.add(speedField);

            JLabel roadTypeLabel = new JLabel("Road Type:");
            BikePanel.add(roadTypeLabel);
            BikePanel.add(roadTypeComboBox);

            confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(this);

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);

            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            contentPanel.add(BikePanel, BorderLayout.WEST);
            contentPanel.add(buttonPanel, BorderLayout.SOUTH);

            add(contentPanel);

            pack();
            setLocationRelativeTo(parent);
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public Bike getBike() {
            return bike;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == confirmButton) {
                String model = modelField.getText();
                int max_speed = Integer.parseInt(speedField.getText());
                String roadType = roadTypeComboBox.getSelectedItem().toString();
                String color=colorComboBox.getSelectedItem().toString();

                bike = new Bike(model,color, max_speed, roadType);
                bike.EnergyScore('A');
                bike.PowerSource("manual");
                confirmed = true;
                dispose();
            } else if (e.getSource() == cancelButton) {
                dispose();
            }
        }
    }

    private class AddCruiseShipDialog extends JDialog implements ActionListener {
        private JTextField modelField;
        private JTextField speedField;
        private JTextField passengersField;
        private JComboBox<ImageIcon> flagComboBox;
        private JComboBox<String> colorComboBox;
        private JTextField engineField;
        private JTextField fuelField;
        private JButton confirmButton;
        private JButton cancelButton;
        private boolean confirmed;
        private CruiseShip cruiseShip;

        public AddCruiseShipDialog(Dialog parent) {
            super(parent, "Add Cruise Ship", true);
            setLayout(new BorderLayout());

            JPanel CruiseShipPanel = new JPanel();
            CruiseShipPanel.setLayout(new GridLayout(5, 2, 10, 10));

            ImageIcon[] flagIcons = {
                    new ImageIcon("Israel_Flag.png"),
                    new ImageIcon("USA_Flag.png"),
                    new ImageIcon("Germany_Flag.png"),
                    new ImageIcon("Italy_Flag.jpg"),
                    new ImageIcon("Greece_Flag.jpg"),
                    new ImageIcon("Somalia_Flag.png"),
                    new ImageIcon("Pirate_Flag.png")
            };

            flagComboBox = new JComboBox<>(flagIcons);
            flagComboBox.setRenderer(new FlagListCellRenderer());

            String[] colors = {"BLACK", "BLUE", "GRAY", "GREEN", "PINK", "ORANGE", "YELLOW", "RED", "WHITE"};
            colorComboBox = new JComboBox<>(colors);
            CruiseShipPanel.add(new JLabel("Color:"));
            CruiseShipPanel.add(colorComboBox);

            for (int i = 0; i < flagIcons.length; i++) {
                Image image = flagIcons[i].getImage();
                Image scaledImage = image.getScaledInstance(120, 80, Image.SCALE_SMOOTH);
                flagIcons[i].setImage(scaledImage);
            }

            JLabel modelLabel = new JLabel("Model:");
            modelField = new JTextField();
            CruiseShipPanel.add(modelLabel);
            CruiseShipPanel.add(modelField);

            JLabel speedLabel = new JLabel("Max Speed:");
            speedField = new JTextField();
            CruiseShipPanel.add(speedLabel);
            CruiseShipPanel.add(speedField);

            JLabel passengersLabel = new JLabel("Max Passengers:");
            passengersField = new JTextField();
            CruiseShipPanel.add(passengersLabel);
            CruiseShipPanel.add(passengersField);

            JLabel flagLabel = new JLabel("Flag:");
            CruiseShipPanel.add(flagLabel);
            CruiseShipPanel.add(flagComboBox);

            JLabel engineLabel = new JLabel("Average Engine Lifetime:");
            engineField = new JTextField();
            CruiseShipPanel.add(engineLabel);
            CruiseShipPanel.add(engineField);

            JLabel fuelLabel = new JLabel("Average Fuel Consumption:");
            fuelField = new JTextField();
            CruiseShipPanel.add(fuelLabel);
            CruiseShipPanel.add(fuelField);

            confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(this);

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);

            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            contentPanel.add(CruiseShipPanel, BorderLayout.WEST);
            contentPanel.add(buttonPanel, BorderLayout.SOUTH);

            add(contentPanel);

            pack();
            setLocationRelativeTo(parent);
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public CruiseShip getCruiseShip() {
            return cruiseShip;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == confirmButton) {
                String model = modelField.getText();
                int max_speed = Integer.parseInt(speedField.getText());
                int max_passengers = Integer.parseInt(passengersField.getText());
                ImageIcon selectedFlag = (ImageIcon) flagComboBox.getSelectedItem();
                String flag = getFlagFromIcon(selectedFlag);
                String color=colorComboBox.getSelectedItem().toString();
                int engineLifetime = Integer.parseInt(engineField.getText());
                int fuelConsumption = Integer.parseInt(fuelField.getText());

                cruiseShip = new CruiseShip(model,color, max_speed, max_passengers, flag);
                cruiseShip.Engine_LifeTime(engineLifetime);
                cruiseShip.set_avg_fuel(fuelConsumption);
                cruiseShip.LicenseType("Unlimited");
                confirmed = true;
                dispose();
            } else if (e.getSource() == cancelButton) {
                dispose();
            }
        }

        private String getFlagFromIcon(ImageIcon icon) {
            if (icon.getDescription().equals("Israel_Flag.png")) {
                return "Israel";
            } else if (icon.getDescription().equals("USA_Flag.png")) {
                return "U.S";
            } else if (icon.getDescription().equals("Germany_Flag.png")) {
                return "Germany";
            } else if (icon.getDescription().equals("Italy_Flag.jpg")) {
                return "Italy";
            } else if (icon.getDescription().equals("Greece_Flag.jpg")) {
                return "Greece";
            } else if (icon.getDescription().equals("Somalia_Flag.png")) {
                return "Somalia";
            } else if (icon.getDescription().equals("Pirate_Flag.png")) {
                return "Pirate Flag";
            } else {
                return "";
            }
        }

        private class FlagListCellRenderer extends DefaultListCellRenderer {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                ImageIcon icon = (ImageIcon) value;
                label.setIcon(icon);
                label.setText("");
                return label;
            }
        }
    }

    private class AddElectricBikeDialog extends JDialog implements ActionListener {
        private JTextField modelField;
        private JTextField speedField;
        private JTextField engineField;
        private JComboBox<String> roadTypeComboBox;
        private JComboBox<String> colorComboBox;
        private JButton confirmButton;
        private JButton cancelButton;
        private boolean confirmed;
        private ElectricBike electricBike;

        public AddElectricBikeDialog(Dialog parent) {
            super(parent, "Add Electric Bike", true);
            setLayout(new BorderLayout());

            JPanel ElectricBikePanel = new JPanel();
            ElectricBikePanel.setLayout(new GridLayout(5, 2, 10, 10));

            JLabel modelLabel = new JLabel("Model:");
            modelField = new JTextField();
            ElectricBikePanel.add(modelLabel);
            ElectricBikePanel.add(modelField);

            JLabel speedLabel = new JLabel("Max Speed:");
            speedField = new JTextField();
            ElectricBikePanel.add(speedLabel);
            ElectricBikePanel.add(speedField);

            JLabel engineLabel = new JLabel("Average Engine Lifetime:");
            engineField = new JTextField();
            ElectricBikePanel.add(engineLabel);
            ElectricBikePanel.add(engineField);

            JLabel roadTypeLabel = new JLabel("Road Type:");
            roadTypeComboBox = new JComboBox<>(new String[]{"Dirt", "Asphalt"});
            ElectricBikePanel.add(roadTypeLabel);
            ElectricBikePanel.add(roadTypeComboBox);

            String[] colors = {"BLACK", "BLUE", "GRAY", "GREEN", "PINK", "ORANGE", "YELLOW", "RED", "WHITE"};
            colorComboBox = new JComboBox<>(colors);
            ElectricBikePanel.add(new JLabel("Color:"));
            ElectricBikePanel.add(colorComboBox);

            confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(this);

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);

            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            contentPanel.add(ElectricBikePanel, BorderLayout.CENTER);
            contentPanel.add(buttonPanel, BorderLayout.SOUTH);

            add(contentPanel);

            pack();
            setLocationRelativeTo(parent);
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public ElectricBike getElectricBike() {
            return electricBike;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == confirmButton) {
                String model = modelField.getText();
                int max_speed = Integer.parseInt(speedField.getText());
                int engineLifetime = Integer.parseInt(engineField.getText());
                String roadType = roadTypeComboBox.getSelectedItem().toString();
                String color=colorComboBox.getSelectedItem().toString();


                electricBike = new ElectricBike(model,color,max_speed,roadType);
                electricBike.set_avg_fuel(20);
                electricBike.Engine_LifeTime(engineLifetime);
                confirmed = true;
                dispose();
            } else if (e.getSource() == cancelButton) {
                dispose();
            }
        }
    }

    private class AddFrigateDialog extends JDialog implements ActionListener {
        private JTextField modelField;
        private JTextField passengersField;
        private JTextField speedField;
        private JComboBox<String> windDirectionComboBox;
        private JComboBox<String> colorComboBox;
        private JComboBox<ImageIcon> flagComboBox;

        private JButton confirmButton;
        private JButton cancelButton;

        private boolean confirmed;
        private Frigate frigate;

        public AddFrigateDialog(Dialog parent) {
            super(parent, "Add Frigate", true);
            setLayout(new BorderLayout());

            JPanel FrigatePanel = new JPanel();
            FrigatePanel.setLayout(new GridLayout(5,2,10,10));
            windDirectionComboBox = new JComboBox<>(new String[]{"With the Wind", "Against the Wind"});

            ImageIcon[] flagIcons = {
                    new ImageIcon("Israel_Flag.png"),
                    new ImageIcon("USA_Flag.png"),
                    new ImageIcon("Germany_Flag.png"),
                    new ImageIcon("Italy_Flag.jpg"),
                    new ImageIcon("Greece_Flag.jpg"),
                    new ImageIcon("Somalia_Flag.png"),
                    new ImageIcon("Pirate_Flag.png")
            };

            flagComboBox = new JComboBox<>(flagIcons);
            flagComboBox.setRenderer(new FlagListCellRenderer());



            for (int i=0;i<flagIcons.length;i++) {
                Image image=flagIcons[i].getImage();
                Image scaledImage = image.getScaledInstance(120,80, Image.SCALE_SMOOTH);
                flagIcons[i].setImage(scaledImage);
            }

            JLabel modelLabel = new JLabel("Model:");
            modelField = new JTextField();
            FrigatePanel.add(modelLabel);
            FrigatePanel.add(modelField);

            JLabel speedLabel=new JLabel("Max Speed:");
            speedField = new JTextField();
            FrigatePanel.add(speedLabel);
            FrigatePanel.add(speedField);

            JLabel passengersLabel = new JLabel("Max Passengers:");
            passengersField = new JTextField();
            FrigatePanel.add(passengersLabel);
            FrigatePanel.add(passengersField);

            JLabel windDirectionLabel = new JLabel("Wind Direction:");
            FrigatePanel.add(windDirectionLabel);
            FrigatePanel.add(windDirectionComboBox);

            JLabel flagLabel=new JLabel("Flag:");
            FrigatePanel.add(flagLabel);
            FrigatePanel.add(flagComboBox);

            String[] colors = {"BLACK", "BLUE", "GRAY", "GREEN", "PINK", "ORANGE", "YELLOW", "RED", "WHITE"};
            colorComboBox = new JComboBox<>(colors);
            FrigatePanel.add(new JLabel("Color:"));
            FrigatePanel.add(colorComboBox);

            confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(this);

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);

            add(FrigatePanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            pack();
            setLocationRelativeTo(parent);
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public Frigate getFrigate() {
            return frigate;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == confirmButton) {
                String model = modelField.getText();
                int max_speed = Integer.parseInt(speedField.getText());
                int max_pass = Integer.parseInt(passengersField.getText());
                boolean windDirection = windDirectionComboBox.getSelectedItem().equals("With the Wind");
                ImageIcon selectedFlag = (ImageIcon)flagComboBox.getSelectedItem();
                String flag = getFlagFromIcon(selectedFlag);
                String color=colorComboBox.getSelectedItem().toString();

                frigate = new Frigate(model,color,max_speed,max_pass,flag,windDirection);
                frigate.set_avg_fuel(500);
                frigate.Engine_LifeTime(4);
                confirmed = true;
                dispose();
            } else if (e.getSource() == cancelButton) {
                dispose();
            }
        }
        private String getFlagFromIcon(ImageIcon icon) {
            if (icon.getDescription().equals("Israel_Flag.png")) {
                return "Israel";
            } else if (icon.getDescription().equals("USA_Flag.png")) {
                return "U.S";
            } else if (icon.getDescription().equals("Germany_Flag.png")) {
                return "Germany";
            } else if (icon.getDescription().equals("Italy_Flag.jpg")) {
                return "Italy";
            } else if (icon.getDescription().equals("Greece_Flag.jpg")) {
                return "Greece";
            } else if (icon.getDescription().equals("Somalia_Flag.png")) {
                return "Somalia";
            } else if (icon.getDescription().equals("Pirate_Flag.png")) {
                return "Pirate Flag";
            } else {
                return "";
            }
        }
        private class FlagListCellRenderer extends DefaultListCellRenderer {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                ImageIcon icon = (ImageIcon) value;
                label.setIcon(icon);
                label.setText("");
                return label;
            }
        }
    }

    private class AddGameGliderDialog extends JDialog implements ActionListener {
        private JButton confirmButton;
        private JButton cancelButton;

        private boolean confirmed;

        private JButton okButton;
        private JComboBox<String> colorComboBox;
        private GameGlider gameGlider;

        public AddGameGliderDialog(Dialog parent) {
            super(parent, "Add Game Glider", true);
            setLayout(new BorderLayout());

            JPanel GameGliderPanel = new JPanel();
            GameGliderPanel.setLayout(new GridLayout(2, 2));

            String[] colors = {"BLACK", "BLUE", "GRAY", "GREEN", "PINK", "ORANGE", "YELLOW", "RED", "WHITE"};
            colorComboBox = new JComboBox<>(colors);
            GameGliderPanel.add(new JLabel("Color:"));
            GameGliderPanel.add(colorComboBox);

            confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(this);

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);

            okButton = new JButton("OK");
            okButton.addActionListener(this);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);

            add(GameGliderPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            pack();
            setLocationRelativeTo(parent);
        }
        public boolean isConfirmed() {
            return confirmed;
        }
        public GameGlider getGameGlider() {
            return gameGlider;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == confirmButton) {
                String color=colorComboBox.getSelectedItem().toString();

                gameGlider=new GameGlider(color);
                gameGlider.PowerSource("manual");
                gameGlider.EnergyScore('A');

                confirmed=true;
                dispose();
            }
            else if (e.getSource() == cancelButton) {
                dispose();
            }
        }
    }

    private class AddHybridPlaneDialog extends JDialog implements ActionListener {
        private JTextField modelField;
        private JTextField speedField;
        private JTextField passengersField;
        private JTextField wheelsField;
        private JComboBox<ImageIcon> flagComboBox;
        private JComboBox<String> windDirectionComboBox;
        private JComboBox<String> colorComboBox;
        private JTextField engineLifetimeField;
        private JTextField fuelConsumptionField;
        private JButton confirmButton;
        private JButton cancelButton;
        private boolean confirmed;
        private HybridPlane hybridPlane;

        public AddHybridPlaneDialog(Dialog parent) {
            super(parent, "Add Hybrid Plane", true);
            setLayout(new BorderLayout());

            JPanel HybridPlanePanel = new JPanel();
            HybridPlanePanel.setLayout(new GridLayout(8, 2, 10, 10));

            JLabel modelLabel = new JLabel("Model:");
            modelField = new JTextField();
            HybridPlanePanel.add(modelLabel);
            HybridPlanePanel.add(modelField);

            JLabel speedLabel = new JLabel("Max Speed:");
            speedField = new JTextField();
            HybridPlanePanel.add(speedLabel);
            HybridPlanePanel.add(speedField);

            JLabel passengersLabel = new JLabel("Max Passengers:");
            passengersField = new JTextField();
            HybridPlanePanel.add(passengersLabel);
            HybridPlanePanel.add(passengersField);

            JLabel wheelsLabel = new JLabel("Number of Wheels:");
            wheelsField = new JTextField();
            HybridPlanePanel.add(wheelsLabel);
            HybridPlanePanel.add(wheelsField);

            ImageIcon[] flagIcons = {
                    new ImageIcon("Israel_Flag.png"),
                    new ImageIcon("USA_Flag.png"),
                    new ImageIcon("Germany_Flag.png"),
                    new ImageIcon("Italy_Flag.jpg"),
                    new ImageIcon("Greece_Flag.jpg"),
                    new ImageIcon("Somalia_Flag.png"),
                    new ImageIcon("Pirate_Flag.png")
            };

            flagComboBox = new JComboBox<>(flagIcons);
            flagComboBox.setRenderer(new FlagListCellRenderer());

            for (int i = 0; i < flagIcons.length; i++) {
                Image image = flagIcons[i].getImage();
                Image scaledImage = image.getScaledInstance(90, 60, Image.SCALE_SMOOTH);
                flagIcons[i].setImage(scaledImage);
            }

            JLabel flagLabel = new JLabel("Flag:");
            HybridPlanePanel.add(flagLabel);
            HybridPlanePanel.add(flagComboBox);

            String[] colors = {"BLACK", "BLUE", "GRAY", "GREEN", "PINK", "ORANGE", "YELLOW", "RED", "WHITE"};
            colorComboBox = new JComboBox<>(colors);
            HybridPlanePanel.add(new JLabel("Color:"));
            HybridPlanePanel.add(colorComboBox);

            JLabel engineLifetimeLabel = new JLabel("Average Engine Lifetime:");
            engineLifetimeField = new JTextField();
            HybridPlanePanel.add(engineLifetimeLabel);
            HybridPlanePanel.add(engineLifetimeField);

            JLabel fuelConsumptionLabel = new JLabel("Average Fuel Consumption:");
            fuelConsumptionField = new JTextField();
            HybridPlanePanel.add(fuelConsumptionLabel);
            HybridPlanePanel.add(fuelConsumptionField);

            JLabel windDirectionLabel = new JLabel("Wind Direction:");
            windDirectionComboBox = new JComboBox<>(new String[]{"With the Wind", "Against the Wind"});
            HybridPlanePanel.add(windDirectionLabel);
            HybridPlanePanel.add(windDirectionComboBox);

            confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(this);

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);

            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            contentPanel.add(HybridPlanePanel, BorderLayout.CENTER);
            contentPanel.add(buttonPanel, BorderLayout.SOUTH);

            add(contentPanel);

            pack();
            setLocationRelativeTo(parent);
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public HybridPlane getHybridPlane() {
            return hybridPlane;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == confirmButton) {
                String model = modelField.getText();
                int max_speed = Integer.parseInt(speedField.getText());
                int max_pass = Integer.parseInt(passengersField.getText());
                int wheels = Integer.parseInt(wheelsField.getText());
                ImageIcon selectedFlag = (ImageIcon) flagComboBox.getSelectedItem();
                String flag = getFlagFromIcon(selectedFlag);
                String color=colorComboBox.getSelectedItem().toString();
                int engineLifetime = Integer.parseInt(engineLifetimeField.getText());
                int fuelConsumption = Integer.parseInt(fuelConsumptionField.getText());
                boolean windDirection = windDirectionComboBox.getSelectedIndex() == 0;

                hybridPlane = new HybridPlane(model,color ,max_speed, max_pass, wheels, flag, windDirection);
                hybridPlane.Engine_LifeTime(engineLifetime);
                hybridPlane.set_avg_fuel(fuelConsumption);
                confirmed = true;
                dispose();
            } else if (e.getSource() == cancelButton) {
                dispose();
            }
        }

        private String getFlagFromIcon(ImageIcon icon) {
            if (icon != null) {
                if (icon.getDescription().equals("Israel_Flag.png")) {
                    return "Israel";
                } else if (icon.getDescription().equals("USA_Flag.png")) {
                    return "U.S";
                } else if (icon.getDescription().equals("Germany_Flag.png")) {
                    return "Germany";
                } else if (icon.getDescription().equals("Italy_Flag.jpg")) {
                    return "Italy";
                } else if (icon.getDescription().equals("Greece_Flag.jpg")) {
                    return "Greece";
                } else if (icon.getDescription().equals("Somalia_Flag.png")) {
                    return "Somalia";
                } else if (icon.getDescription().equals("Pirate_Flag.png")) {
                    return "Pirate Flag";
                }
            }
            return "";
        }

        private class FlagListCellRenderer extends DefaultListCellRenderer {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                ImageIcon icon = (ImageIcon) value;
                label.setIcon(icon);
                label.setText("");
                return label;
            }
        }
    }

    private class AddJeepDialog extends JDialog implements ActionListener {
        private JTextField modelField;
        private JTextField speedField;
        private JTextField engineField;
        private JTextField fuelField;
        private JComboBox<String> colorComboBox;

        private JButton confirmButton;
        private JButton cancelButton;

        private boolean confirmed;
        private Jeep jeep;

        public AddJeepDialog(Dialog parent) {
            super(parent, "Add Jeep", true);
            setLayout(new BorderLayout());

            JPanel JeepPanel = new JPanel();
            JeepPanel.setLayout(new GridLayout(4, 2));

            JLabel modelLabel = new JLabel("Model:");
            modelField = new JTextField();
            JeepPanel.add(modelLabel);
            JeepPanel.add(modelField);

            JLabel speedLabel = new JLabel("Max Speed:");
            speedField = new JTextField();
            JeepPanel.add(speedLabel);
            JeepPanel.add(speedField);

            JLabel engineLabel= new JLabel("Average engine lifetime:");
            engineField=new JTextField();
            JeepPanel.add(engineLabel);
            JeepPanel.add(engineField);

            JLabel fuelLabel=new JLabel("Average fuel consumption:");
            fuelField=new JTextField();
            JeepPanel.add(fuelLabel);
            JeepPanel.add(fuelField);

            String[] colors = {"BLACK", "BLUE", "GRAY", "GREEN", "PINK", "ORANGE", "YELLOW", "RED", "WHITE"};
            colorComboBox = new JComboBox<>(colors);
            JeepPanel.add(new JLabel("Color:"));
            JeepPanel.add(colorComboBox);

            confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(this);

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);

            add(JeepPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            pack();
            setLocationRelativeTo(parent);
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public Jeep getJeep() {
            return jeep;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == confirmButton) {
                String model = modelField.getText();
                int max_speed = Integer.parseInt(speedField.getText());
                int engine = Integer.parseInt(engineField.getText());
                int fuel=Integer.parseInt(fuelField.getText());
                String color=colorComboBox.getSelectedItem().toString();

                jeep = new Jeep(model,color,max_speed);
                jeep.set_avg_fuel(fuel);
                jeep.Engine_LifeTime(engine);
                jeep.LicenseType("MINI");

                confirmed = true;
                dispose();
            } else if (e.getSource() == cancelButton) {
                dispose();
            }
        }
    }

    private class AddSpyGliderDialog extends JDialog implements ActionListener {
        private JComboBox<String> PowerSourceComboBox;
        private JComboBox<String> colorComboBox;

        private JButton confirmButton;
        private JButton cancelButton;

        private boolean confirmed;
        private SpyGlider spyGlider;

        public AddSpyGliderDialog(Dialog parent) {
            super(parent, "Add Spy Glider", true);
            setLayout(new BorderLayout());

            JPanel SpyGliderPanel = new JPanel();
            SpyGliderPanel.setLayout(new GridLayout(2, 2));

            PowerSourceComboBox = new JComboBox<>(new String[]{"Manual", "Electric"});

            JLabel PowerSourceLabel = new JLabel("Power Source:");
            SpyGliderPanel.add(PowerSourceLabel);
            SpyGliderPanel.add(PowerSourceComboBox);

            String[] colors = {"BLACK", "BLUE", "GRAY", "GREEN", "PINK", "ORANGE", "YELLOW", "RED", "WHITE"};
            colorComboBox = new JComboBox<>(colors);
            SpyGliderPanel.add(new JLabel("Color:"));
            SpyGliderPanel.add(colorComboBox);

            confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(this);

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);

            add(SpyGliderPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            pack();
            setLocationRelativeTo(parent);
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public SpyGlider getSpyGlider() {
            return spyGlider;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == confirmButton) {
                String power_source = PowerSourceComboBox.getSelectedItem().toString();
                String color=colorComboBox.getSelectedItem().toString();

                spyGlider = new SpyGlider(color);
                spyGlider.PowerSource(power_source);
                spyGlider.EnergyScore('C');

                confirmed = true;
                dispose();
            } else if (e.getSource() == cancelButton) {
                dispose();
            }
        }
    }
}
