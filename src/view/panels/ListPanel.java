package view.panels;

import javax.swing.*;
import java.awt.*;

public class ListPanel extends JPanel {
    private int y = 0;

    // Add new panel voor een nieuwe item die te zien is in de lijst.
    public void addNewPanel(String panelName) {

        JPanel newPanel = new JPanel(new GridBagLayout());
        newPanel.add(new JLabel(panelName));

        GridBagConstraints container = new GridBagConstraints();
        container.gridy = y++;
        container.weightx = 1;
        container.fill = GridBagConstraints.HORIZONTAL;
        container.gridwidth = GridBagConstraints.REMAINDER;
        container.insets = new Insets(4, 4, 4, 4);

        add(newPanel, container);

        GridBagLayout gbl = ((GridBagLayout)getLayout());
        container = gbl.getConstraints(newPanel);
        container.gridy = y++;
        gbl.setConstraints(newPanel, container);

        revalidate();
        repaint();
    }           
}
