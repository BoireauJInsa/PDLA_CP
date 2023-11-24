package org.apo.vue;

import org.apo.model.Demande;

import javax.swing.*;
import java.awt.*;

public class DemandPanel extends JPanel {

    private Demande demande;

    public DemandPanel(FrameView frameView, DemandsPanel demandsPanel) {

        this.setLayout(null);
        this.setPreferredSize(new Dimension(frameView.getWidth(), frameView.getHeight()/5));
        JLabel demanderNameLabel = new JLabel();

    }

}
