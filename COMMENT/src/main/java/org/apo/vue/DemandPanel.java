package org.apo.vue;

import org.apo.model.Demande;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DemandPanel extends JPanel {

    private Demande demande;
    private String name;
    private String message;
    private JCheckBox checkBox;

    public DemandPanel(FrameView frameView, Demande demande) {

        this.name = name;
        this.demande=demande;
        this.message = message;

        this.setLayout(new BorderLayout());
        this.setBorder(new LineBorder(Color.black));

        JLabel nameLabel = new JLabel(demande.GetNameDemandeur());
        nameLabel.setMaximumSize(new Dimension(9*getWidth()/10, getHeight()/5));
        nameLabel.setFont(new Font(nameLabel.getFont().getFontName(), Font.BOLD, nameLabel.getFont().getSize()));

        JTextArea messageLabel = new JTextArea(demande.getMessage());
        messageLabel.setEditable(false);
        messageLabel.setLineWrap(true);
        messageLabel.setWrapStyleWord(true);
        this.add(messageLabel, BorderLayout.CENTER);

        checkBox = new JCheckBox();
        this.setVisible(true);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(nameLabel);
        topPanel.add(checkBox);
        this.add(topPanel, BorderLayout.NORTH);

        this.setVisible(true);
    }

    public Demande getDemand() {
        return demande;
    }

    public boolean getSelect() {
        return this.checkBox.isSelected();
    }
}
