package Graphic;

import Vehicles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChangeFlagDialog extends JDialog implements ActionListener {
    private List<Vehicle> vehicles;
    private JButton[] flagButtons;
    private ImageIcon[] flagIcons = {
            new ImageIcon("Israel_Flag.png"),
            new ImageIcon("USA_Flag.png"),
            new ImageIcon("Germany_Flag.png"),
            new ImageIcon("Italy_Flag.jpg"),
            new ImageIcon("Greece_Flag.jpg"),
            new ImageIcon("Somalia_Flag.png"),
            new ImageIcon("Pirate_Flag.png")
    };

    public ChangeFlagDialog(Frame parent, List<Vehicle> vehicles) {
        super(parent, "Change Flag", true);
        this.vehicles = vehicles;

        setLayout(new FlowLayout());

        flagButtons = new JButton[flagIcons.length];
        for (int i = 0; i < flagIcons.length; i++) {
            Image image=flagIcons[i].getImage();
            Image scaledImage = image.getScaledInstance(120,80, Image.SCALE_SMOOTH);
            flagIcons[i].setImage(scaledImage);
            flagButtons[i] = new JButton(flagIcons[i]);
            flagButtons[i].setPreferredSize(new Dimension(100, 100));
            flagButtons[i].addActionListener(this);
            add(flagButtons[i]);
        }

        pack();
        setLocationRelativeTo(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        for (int i=0;i<vehicles.size();i++) {
            if(vehicles.get(i) instanceof MarineVehicle){
                ((MarineVehicle)vehicles.get(i)).set_state_flag(getSelectedFlag(sourceButton.getIcon()));
            }
            if(vehicles.get(i) instanceof Amphibious){
                ((Amphibious)vehicles.get(i)).set_state_flag(getSelectedFlag(sourceButton.getIcon()));
            }
        }
        JOptionPane.showMessageDialog(this, "Flag change was made successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private String getSelectedFlag(Icon icon) {
        if (icon.equals(flagIcons[0]))
            return "Israel";
        else if (icon.equals(flagIcons[1]))
            return "USA";
        else if (icon.equals(flagIcons[2]))
            return "Germany";
        else if (icon.equals(flagIcons[3]))
            return "Italy";
        else if (icon.equals(flagIcons[4]))
            return "Greece";
        else if (icon.equals(flagIcons[5]))
            return "Somalia";
        else if (icon.equals(flagIcons[6]))
            return "Pirate Flag";
        else
            return "";
    }
}